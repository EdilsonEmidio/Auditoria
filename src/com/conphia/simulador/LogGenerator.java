import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.Random;

public class LogGenerator implements Runnable {
    private volatile boolean running = true;
    private final Random rnd = new Random();
    private final File logDir;

    public LogGenerator() {
        this.logDir = new File(Config.LOG_DIR);
        if (!logDir.exists())
            logDir.mkdirs();
    }

    private void write(File f, String msg) {
        try (FileWriter fw = new FileWriter(f, true)) {
            fw.write(Instant.now().toString() + " " + msg + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void genSsh() {
        write(new File(logDir, "auth.log"), "sshd: Failed password for user from 203.0.113." + (1 + rnd.nextInt(250))
                + " port " + (1000 + rnd.nextInt(60000)) + " ssh2");
    }

    private void genSql() {
        write(new File(logDir, "app_web.log"), "GET /search?q=' OR '1'='1 HTTP/1.1");
    }

    private void genDataLeak() {
        write(new File(logDir, "data_leak.log"),
                "EXPORT_COMPLETED file=customers_2025-10-31.csv rows=1246 dest=ftp://malicious.example");
    }

    private void genRansom() {
        write(new File(logDir, "ransom.log"),
                "FILE_ENCRYPTED path=/home/user/docs/invoice.xls action=changed ext=.crypt");
    }

    private void genPhish() {
        write(new File(logDir, "mail.log"),
                "PHISH_EMAIL to=ana@conphia.local subj='Urgent: Verify your account' from=spoofed@bank.example");
    }

    @Override
    public void run() {
        String[] funcs = { "ssh", "sql", "leak", "ransom", "phish" };
        while (running) {
            int pick = rnd.nextInt(funcs.length);
            switch (funcs[pick]) {
                case "ssh":
                    genSsh();
                    break;
                case "sql":
                    genSql();
                    break;
                case "leak":
                    genDataLeak();
                    break;
                case "ransom":
                    genRansom();
                    break;
                case "phish":
                    genPhish();
                    break;
            }
            try {
                Thread.sleep(200 + rnd.nextInt(1200));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void stop() {
        running = false;
    }
}