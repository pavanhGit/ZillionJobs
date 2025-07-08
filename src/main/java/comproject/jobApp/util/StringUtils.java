package comproject.jobApp.util;

public class StringUtils {

    private StringUtils(){}

    public static boolean isNullorEmpty(String field){
        return field == null || field.trim().isEmpty();
    }
}
