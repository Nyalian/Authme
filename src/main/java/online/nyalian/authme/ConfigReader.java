package online.nyalian.authme;

import org.bukkit.configuration.file.FileConfiguration;

public final class ConfigReader {

    public static FileConfiguration config = Authme.instance.getConfig();

    public static boolean isPlayerRegistered(String playerName) {

        return config.contains(playerName.toLowerCase());
    }

    public static boolean verifyPassword(String playerName, String password) {
        return password.equals(config.getString(playerName.toLowerCase()));
        // 三步合成一行：转换小写，读取字符串，返回是否相等
    }

    public static void addPlayer(String playerName, String password) {
        Authme.instance.getConfig().set(playerName.toLowerCase(), password);
        Authme.instance.saveConfig();
    }
}