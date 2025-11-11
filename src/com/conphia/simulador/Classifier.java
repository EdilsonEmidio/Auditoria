import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.regex.Pattern;

public class Classifier {
    private final File logDir;

    public Classifier() {
        this.logDir = new File(Config.LOG_DIR);
        if (!logDir.exists())
            logDir.mkdirs();
    }

    public void scanOnce() {
        File[] files = logDir.listFiles((d, name) -> name.endsWith(".log"));
        if (files == null)
            return;
        for (File f : files) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String label = match(line);
                    if (label != null)
                        appendAlert(label, f.getName(), line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String match(String line) {
        if (Pattern.compile("Failed password for").matcher(line).find())
            return "SSH Brute Force";
        if (Pattern.compile("UNION SELECT|DROP TABLE|' OR '1'='1").matcher(line).find())
            return "SQL Injection";
        if (Pattern.compile("EXPORT_COMPLETED.*dest=").matcher(line).find())
            return "Data Exfiltration";
        if (Pattern.compile("FILE_ENCRYPTED").matcher(line).find())
            return "Ransomware (file encryption)";
        if (Pattern.compile("PHISH_EMAIL").matcher(line).find())
            return "Phishing Email";
        return null;
    }

    private synchronized void appendAlert(String label, String source, String evidence) {
        try (FileWriter fw = new FileWriter(Config.ALERTS_FILE, true)) {
            fw.write(Instant.now().toString() + " ALERT: " + label + " detected in " + source + " -> " + evidence
                    + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}