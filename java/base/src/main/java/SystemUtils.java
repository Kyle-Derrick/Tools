import java.util.Locale;

public class SystemUtils {
    public static final String OS_NAME = System.getProperty("os.name")
            .toLowerCase(Locale.US);
    public static final OsType OS_TYPE;
    static {
        OsType osType = OsType.UNIX;
        String osName = OS_NAME.toLowerCase();
        switch (osName.charAt(0)) {
            case 'w':
                if (osName.startsWith("win")) {
                    osType = OsType.WINDOWS;
                }
                break;
            case 'm':
                if (osName.startsWith("mac")) {
                    osType = OsType.OSX;
                }
                break;
            case 'o':
                if (osName.startsWith("osx")) {
                    osType = OsType.OSX;
                }
                break;
            default:
        }
        OS_TYPE = osType;
    }


}
