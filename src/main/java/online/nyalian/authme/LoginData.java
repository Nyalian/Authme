package online.nyalian.authme;

import java.util.ArrayList;
import java.util.List;

public class LoginData {
    public static final List<String> RESTRICTS = new ArrayList<>();

    public static void addPlayerName(String playerNameIn) {
        String convertedName = playerNameIn.toLowerCase();
        // toLowerCase 返回一个小写的副本，是 String 类的一个成员方法
        if (!RESTRICTS.contains(convertedName)) {
            // contains 方法返回一个逻辑值，! 符号把它变为相反的值，因此这个 if 语句只有在 RESTRICTS 中不含 convertedName 时才会执行里面的部分
            RESTRICTS.add(convertedName);
        }
    }

    public static void removePlayerName(String playerNameIn) {
        String convertedName = playerNameIn.toLowerCase();
        RESTRICTS.remove(convertedName);
    }

    public static boolean hasPlayerName(String playerNameIn) {
        String convertedName = playerNameIn.toLowerCase();
        return RESTRICTS.contains(convertedName);
    }
}
