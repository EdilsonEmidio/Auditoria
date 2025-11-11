
import java.io.File;
import java.io.FileWriter;
import java.time.Instant;

public class ReportGenerator {
    public File generate(String incidentId, String incidentType, String impact, String actionsTaken) throws Exception {
        File repDir = new File("reports");
        if (!repDir.exists())
            repDir.mkdirs();
        File out = new File(repDir, "report_" + incidentId + ".md");
        try (FileWriter fw = new FileWriter(out)) {
            fw.write("# Relatório de Incidente - " + incidentId + "\n\n");
            fw.write("**Data:** " + Instant.now().toString() + "\n\n");
            fw.write("**Tipo:** " + incidentType + "\n\n");
            fw.write("**Impacto (breve):** " + impact + "\n\n");
            fw.write("**Ações tomadas:**\n\n" + actionsTaken + "\n\n");
            fw.write("## Evidências preservadas\n\n");
            fw.write("(ver diretório evidence/" + incidentId + ")\n\n");
            fw.write("## Conformidade (resumo)\n\n");
            fw.write(
                    "- ISO/IEC 27035: ciclo de resposta aplicado: identificação, análise, contenção, erradicação, recuperação e lições aprendidas.\n");
            fw.write(
                    "- LGPD (art. 48): comunicada a Autoridade? (simulação) — registrar necessidade de comunicação quando houver vazamento de dados pessoais.\n");
            fw.write("\n## Plano de melhoria\n\n- Recomendações técnicas e administrativas...\n");
        }
        return out;
    }
}