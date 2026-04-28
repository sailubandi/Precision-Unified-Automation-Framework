package constants;

public final class FrameworkConstants {

    private FrameworkConstants() {}

    public static final String ROOT_PATH = System.getProperty("user.dir");

    public static final String CONFIG_FILE_PATH =
    		System.getProperty("user.dir") 
            + "/../Common/src/main/resources/config.properties";

    public static final int EXPLICIT_WAIT = 10;
}
