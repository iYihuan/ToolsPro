package ToolsPro.commands;

import ToolsPro.ToolsPro;
import ToolsPro.util.Message;
import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.Config;
import cn.nukkit.utils.TextFormat;

import java.io.File;

/**
 * Created by Pub4Game on 21.12.2015.
 */
public class UnmuteCommand extends ToolsProCommand {

    private ToolsPro plugin;

    public UnmuteCommand(ToolsPro plugin) {
        super("unmute", Message.CMD_UNMUTE_DESCRIPTION, Message.CMD_UNMUTE_DESCRIPTION2.toString());
        this.setPermission("toolspro.commands.unmute");
        this.plugin = plugin;
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!sender.hasPermission(this.getPermission())) {
            sender.sendMessage(this.getPermissionMessage());
        } else {
            if (args.length != 0) {
                Config mute = new Config(new File(this.plugin.getDataFolder(), "mute.yml"), Config.YAML);
                Player p = this.plugin.getServer().getPlayer(args[0]);
                if (!mute.exists(args[0].toLowerCase())) {
                    Message.CMD_UNMUTE_PLAYER_NOT_MUTED.print(sender, "prefix:&7[&aMute&7]", 'a', 'b', p.getName());
                } else {
                    mute.remove(args[0].toLowerCase());
                    mute.save();
                    if (p instanceof Player) {
                        Message.CMD_UNMUTE_PLAYER_MESSAGE.print(p, "prefix:&7[&aMute&7]", 'c');
                    }
                    Message.CMD_UNMUTE_SENDER.print(sender, "prefix:&7[&aHealth&7]", 'a', 'b', p.getName());
                    this.plugin.info(sender, Message.CMD_UNMUTE_PLAYER_INFO.getText("prefix:&7[Mute]", sender.getName(), p.getName()));
                }
            } else {
                return Message.CMD_UNMUTE_USAGE.print(sender, "prefix:&7[&aMute&7]", 'c');
            }
        }
        return true;
    }
}