package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class GMCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public GMCommand(ToolsPro plugin) {
        super("gm", Message.CMD_GAMEMODE_DESCRIPTION, Message.CMD_GAMEMODE_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.gamemode");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (sender instanceof Player) {
                if (args.length != 0) {
                    switch (args[0]) {
                        case "0":
                            if (((Player) sender).getGamemode() == 0) {
                                Message.CMD_GAMEMODE_YOU_ALREADY_IN_SURVIVAL_MODE.print(sender, "prefix:&7[&aGM&7]", 'c');
                                return false;
                            }
                            ((Player) sender).setGamemode(0);
                            Message.CMD_GAMEMODE_SUCCESSFULLY_CHANGED_TO_SURVIVAL.print(sender, "prefix:&7[&aGM&7]", 'a');
                            this.plugin.info(sender, Message.CMD_GAMEMODE_SUCCESSFULLY_CHANGED_TO_SURVIVAL_INFO.getText("prefix:&7[GM]"));
                            return true;
                        case "1":
                            if (((Player) sender).getGamemode() == 1) {
                                Message.CMD_GAMEMODE_YOU_ALREADY_IN_CREATIVE_MODE.print(sender, "prefix:&7[&aGM&7]", 'c');
                                return false;
                            }
                            ((Player) sender).setGamemode(1);
                            Message.CMD_GAMEMODE_SUCCESSFULLY_CHANGED_TO_CREATIVE.print(sender, "prefix:&7[&aGM&7]", 'a');
                            this.plugin.info(sender, Message.CMD_GAMEMODE_SUCCESSFULLY_CHANGED_TO_CREATIVE_INFO.getText("prefix:&7[GM]"));
                            return true;
                        case "2":
                            if (sender.hasPermission("toolspro.gamemode.other")) {
                                if (((Player) sender).getGamemode() == 2) {
                                    Message.CMD_GAMEMODE_YOU_ALREADY_IN_ADVENTURE_MODE.print(sender, "prefix:&7[&aGM&7]", 'c');
                                    return false;
                                }
                                ((Player) sender).setGamemode(2);
                                Message.CMD_GAMEMODE_SUCCESSFULLY_CHANGED_TO_ADVENTURE.print(sender, "prefix:&7[&aGM&7]", 'a');
                                this.plugin.info(sender, Message.CMD_GAMEMODE_SUCCESSFULLY_CHANGED_TO_ADVENTURE_INFO.getText("prefix:&7[GM]"));
                            } else {
                                sender.sendMessage(this.getPermissionMessage());
                            }
                            return true;
                        case "3":
                            if (sender.hasPermission("toolspro.gamemode.other")) {
                                if (((Player) sender).getGamemode() == 3) {
                                    Message.CMD_GAMEMODE_YOU_ALREADY_IN_SPECTATOR_MODE.print(sender, "prefix:&7[&aGM&7]", 'c');
                                    return false;
                                }
                                ((Player) sender).setGamemode(3);
                                Message.CMD_GAMEMODE_SUCCESSFULLY_CHANGED_TO_SPECTATOR.print(sender, "prefix:&7[&aGM&7]", 'a');
                                this.plugin.info(sender, Message.CMD_GAMEMODE_SUCCESSFULLY_CHANGED_TO_SPECTATOR_INFO.getText("prefix:&7[GM]"));
                            } else {
                                sender.sendMessage(this.getPermissionMessage());
                            }
                            return true;
                        case "help":
                            Message.CMD_GAMEMODE_HELP1.print(sender,"prefix:&7[&aGM&7]",'9');
                            //sender.sendMessage(TextFormat.colorize("&7[&aGM&7] &9Список игровых режимов&7:"));
                            Message.CMD_GAMEMODE_HELP2.print(sender,"prefix:&e/gm 0 &7-",'a');
                            //sender.sendMessage(TextFormat.colorize("&e/gm 0 &7- &aИзменяет ваш игровой режим на выживание"));
                            Message.CMD_GAMEMODE_HELP3.print(sender,"prefix:&e/gm 1 &7-",'a');
                            //sender.sendMessage(TextFormat.colorize("&e/gm 1 &7- &aИзменяет ваш игровой режим на креатив"));
                            if (sender.hasPermission("toolspro.gamemode.other")) {
                                Message.CMD_GAMEMODE_HELP4.print(sender,"prefix:&e/gm 2 &7-",'a');
                                //sender.sendMessage(TextFormat.colorize("&e/gm 2 &7- &aИзменяет ваш игровой режим на режим приключений"));
                                Message.CMD_GAMEMODE_HELP4.print(sender,"prefix:&e/gm 3 &7-",'a');
                                //sender.sendMessage(TextFormat.colorize("&e/gm 3 &7- &aИзменяет ваш игровой режим на режим наблюдения"));
                            }
                            return true;
                        default:
                            Message.CMD_GAMEMODE_TYPE_HELP.print(sender,"prefix:&7[&aGM&7]",'a','e',"/gm help");
                            //sender.sendMessage(TextFormat.colorize("&7[&aGM&7] Пожалуйста, используйте &e/gm help &aдля просмотра всех игровых режимов"));
                            return true;
                    }
                } else {
                    Message.CMD_GAMEMODE_TYPE_HELP.print(sender,"prefix:&7[&aGM&7]",'a','e',"/gm help");
                    //sender.sendMessage(TextFormat.colorize("&7[&aGM&7] Пожалуйста, используйте &e/gm help &aдля просмотра всех игровых режимов"));
                }
            } else {
                return Message.NEED_PLAYER.print(sender, "prefix:&7[&aGM&7]", 'c');
            }
        }
        return true;
    }
}
