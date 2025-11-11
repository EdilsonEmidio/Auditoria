public class Config {
    public static final String LOG_DIR = System.getProperty("gsi.logdir", "./logs");
    public static final String ALERTS_FILE = LOG_DIR + "/alerts.log";
    public static final String ACTIONS_FILE = LOG_DIR + "/actions.log";
}