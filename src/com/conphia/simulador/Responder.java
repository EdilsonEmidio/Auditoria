import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;

public class Responder {

    public void blockIp(String ip) {
        // Simulação: registra ação em actions.log. Se quiser, pode executar iptables
        // via Runtime
        logAction("BLOCKED IP " + ip + " (simulated)");
    }

    public void killProcess(String target) {
        logAction("KILLED PROCESS " + target + " (simulated)");
    }

    public void isolateHost(String host) {
        logAction("ISOLATED HOST " + host + " (simulated)");
    }

    private synchronized void logAction(String act) {
        try (FileWriter fw = new FileWriter(Config.ACTIONS_FILE, true)) {
            fw.write(Instant.now().toString() + " ACTION: " + act + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}