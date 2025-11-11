import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;

public class EvidencePreserver {
    public File preserve(String incidentId) throws Exception {
        File src = new File(Config.LOG_DIR);
        File destRoot = new File("evidence");
        if (!destRoot.exists())
            destRoot.mkdirs();
        File dest = new File(destRoot, incidentId);
        if (!dest.exists())
            dest.mkdirs();

        for (File f : src.listFiles((d, n) -> n.endsWith(".log"))) {
            File out = new File(dest, f.getName());
            Files.copy(f.toPath(), out.toPath(), StandardCopyOption.REPLACE_EXISTING);
            String sha = sha256(out);
            Files.write(new File(dest, f.getName() + ".sha256").toPath(), sha.getBytes());
        }
        return dest;
    }

    private String sha256(File f) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (FileInputStream in = new FileInputStream(f)) {
            byte[] buf = new byte[8192];
            int r;
            while ((r = in.read(buf)) > 0)
                md.update(buf, 0, r);
        }
        byte[] dig = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : dig)
            sb.append(String.format("%02x", b));
        return sb.toString();
    }
}