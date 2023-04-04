package online.nyalian.authme;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

import javax.annotation.ParametersAreNonnullByDefault;

public class CommandHandler implements CommandExecutor {

    @Override
    @ParametersAreNonnullByDefault
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }

        if (!LoginData.hasPlayerName(sender.getName())) {
            sender.sendMessage(ChatColor.GREEN + "你已经登录了！");
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.RED + "必须输入密码！");
            // sendMessage 用于向该终端（玩家或控制台）发送消息
            return false;
            // 参数不完整，拒绝处理
        }

        String pwdConcat = String.join("<space>", args);
        if (ConfigReader.isPlayerRegistered(sender.getName())) {
            // 已经注册
            if (ConfigReader.verifyPassword(sender.getName(), pwdConcat)) {
                // 验证密码
                LoginData.removePlayerName(sender.getName());
                // 解锁玩家
                sender.sendMessage(ChatColor.GREEN + "登录成功，欢迎回来！");
            } else {
                sender.sendMessage(ChatColor.RED + "密码错误！");
            }
            return true;
            // true 代表语法正确，虽然密码错误，但语法正确即可返回 true

        } else {
            // 玩家没注册，准备注册
            ConfigReader.addPlayer(sender.getName(), pwdConcat);
            // 注册玩家
            LoginData.removePlayerName(sender.getName());
            // 解锁玩家
            sender.sendMessage(ChatColor.GREEN + "注册成功！");
            return true;
        }
    }


}
