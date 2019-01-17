package util;

public class StringUtils {

    /**判断String是否为null或""
     * @param str
     * @return
     */
    public static boolean isBlank(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        return false;
    }

}
