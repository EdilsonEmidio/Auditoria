
import java.io.File;
import java.nio.file.Files;
import java.time.Instant;
import java.util.Scanner;

public class ConsoleApp {
    public static void main(String[] args) throws Exception {
        System.out.println("=== GSISim-Java - Console ===");
        LogGenerator gen = new LogGenerator();
        Thread gthread = new Thread(gen);
        Classifier cls = new Classifier();
        Responder rsp = new Responder();
        EvidencePreserver ep = new EvidencePreserver();
        ReportGenerator rg = new ReportGenerator();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println(
                    "\n1) Iniciar gerador de logs\n2) Parar gerador\n3) Rodar scanner/classificador\n4) Ver alerts.log\n5) Responder a um IP (bloquear simulado)\n6) Preservar evidências e gerar relatório\n7) Sair");
            System.out.print("> ");
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1":
                    if (!gthread.isAlive()) {
                        gthread = new Thread(gen);
                        gthread.start();
                        System.out.println("Gerador iniciado");
                    } else
                        System.out.println("Gerador já em execução");
                    break;
                case "2":
                    gen.stop();
                    System.out.println("Pedido de parada enviado");
                    break;
                case "3":
                    cls.scanOnce();
                    System.out.println("Scan concluído");
                    break;
                case "4":
                    File f = new File(Config.ALERTS_FILE);
                    if (!f.exists()) {
                        System.out.println("Nenhum alerta ainda.");
                        break;
                    }
                    Files.lines(f.toPath()).forEach(System.out::println);
                    break;
                case "5":
                    System.out.print("IP para bloquear: ");
                    String ip = sc.nextLine().trim();
                    rsp.blockIp(ip);
                    System.out.println("Ação registrada");
                    break;
                case "6":
                    String id = "INC-" + Instant.now().toEpochMilli();
                    File ev = ep.preserve(id);
                    File rep = rg.generate(id, "(tipo detectado)", "(impacto estimado)",
                            "(ações registradas no arquivo: " + Config.ACTIONS_FILE + ")");
                    System.out.println("Evidências em: " + ev.getAbsolutePath());
                    System.out.println("Relatório gerado em: " + rep.getAbsolutePath());
                    break;
                case "7":
                    System.out.println("Saindo...");
                    gen.stop();
                    sc.close();
                    return;
                default:
                    System.out.println("Opção inválida");
            }
        }
    }
}