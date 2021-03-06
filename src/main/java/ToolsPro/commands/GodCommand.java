package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;

/**
 * Created by Pub4Game on 19.12.2015.
 */
public class GodCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public GodCommand(ToolsPro plugin) {
        super("god", Message.CMD_GOD_DESCRIPTION, Message.CMD_GOD_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.god");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (args.length != 0) {
                if (sender.hasPermission("toolspro.god.other")) {
                    Player p = this.plugin.getServer().getPlayer(args[0]);
                    if (p != null) {
                        if (p.getGamemode() != 0) {
                            Message.PLAYER_NOT_SURVIVAL.print(sender, "prefix:&7[&aGodMode&7]", 'c', 'b');
                        } else {
                            if (this.plugin.isGodMode(p.getName())) {
                                this.plugin.removeGodMode(p.getName());
                                Message.CMD_GOD_PLAYER_DISABLE.print(sender, "prefix:&7[&aGodMode&7]", 'a', 'b', p.getName());
                                Message.CMD_GOD_PLAYER_DISABLE_MESSAGE.print(p, "prefix:&7[&aGodMode&7]", 'a');
                                this.plugin.info(sender, Message.CMD_GOD_PLAYER_DISABLE_INFO.getText("prefix:&7[GodMode]", sender.getName(), p.getName()));
                            } else {
                                this.plugin.setGodMode(p.getName());
                                Message.CMD_GOD_PLAYER_ENABLE.print(sender, "prefix:&7[&aGodMode&7]", 'a', 'b', p.getName());
                                Message.CMD_GOD_PLAYER_ENABLE_MESSAGE.print(p, "prefix:&7[&aGodMode&7]", 'a');
                                this.plugin.info(sender, Message.CMD_GOD_PLAYER_ENABLE_INFO.getText("prefix:&7[GodMode]", sender.getName(), p.getName()));
                            }
                        }
                    } else {
                        Message.UNKNOWN_PLAYER.print(sender, "prefix:&7[&aGodMode&7]", 'c');
                    }
                } else {
                    sender.sendMessage(this.getPermissionMessage());
                }
            } else {
                if (sender instanceof Player) {
                    if (((Player) sender).getGamemode() != 0) {
                        Message.YOU_NOT_SURVIVAL.print(sender, "prefix:&7[&aGodMode&7]", 'c');
                    } else {
                        if (this.plugin.isGodMode(sender.getName())) {
                            this.plugin.removeGodMode(sender.getName());
                            Message.CMD_GOD_SENDER_DISABLE.print(sender, "prefix:&7[&aGodMode&7]", 'a');
                            this.plugin.info(sender, Message.CMD_GOD_SENDER_DISABLE_INFO.getText("prefix:&7[GodMode]"));
                        } else {
                            this.plugin.setGodMode(sender.getName());
                            Message.CMD_GOD_SENDER_ENABLE.print(sender, "prefix:&7[&aGodMode&7]", 'a');
                            this.plugin.info(sender, Message.CMD_GOD_SENDER_ENABLE_INFO.getText("prefix:&7[GodMode]"));
                        }
                    }
                } else {
                    return Message.NEED_PLAYER.print(sender, "prefix:&7[&aGodMode&7]", 'c');
                }
            }
        }
        return true;
    }
}
