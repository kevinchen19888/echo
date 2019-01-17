package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputDataUtil {
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String getInputStr(String promt) {
        boolean flag = true;
        String output = null;

        while (flag) {
            System.out.println(promt);
            try {
                output = reader.readLine().trim();
                if (StringUtils.isBlank(output)) {
                    System.out.println("输入为空，重新输入");
                } else {
                    flag = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return output;
    }

}
