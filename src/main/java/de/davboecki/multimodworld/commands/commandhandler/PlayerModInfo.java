package de.davboecki.multimodworld.commands.commandhandler;

import org.bukkit.command.CommandSender;

import de.davboecki.multimodworld.MMWPlayer;
import de.davboecki.multimodworld.commands.ICommandHandler;
import de.davboecki.multimodworld.commands.MorePageDisplay;
import de.davboecki.multimodworld.handler.ColorHandler;
import de.davboecki.multimodworld.handler.LanguageHandler;
import de.davboecki.multimodworld.mod.ModInfo;
import de.davboecki.multimodworld.mod.ModInfoBase;

public class PlayerModInfo implements ICommandHandler {

	public boolean isCommand(String command) {
		return command.equalsIgnoreCase("player");
	}
	
	private boolean checkCommand(String command, String[] args, int pos) {
		if(args == null) return false;
		if(command == "") return false;
		if(pos < 0) return false;
		if(pos >= args.length) return false;
		return args[pos].equalsIgnoreCase(command);
	}
	
	public boolean onCommand(CommandSender sender, String[] args) {
		if(args == null || checkCommand("help",args,0)) {
			displayhelp(sender);
			return true;
		}
		if(args.length < 2) {
			sender.sendMessage(ColorHandler.RED.toString(sender)+LanguageHandler.Error+ColorHandler.WHITE.toString(sender)+": "+LanguageHandler.Fewarguments);
			return true;
		}
		if(args.length > 0 && MMWPlayer.getDefinedMMWPlayer(args[0]) == null) {
			// TODO Error: First Argument not a Player
			return true;
		}
		MMWPlayer mplayer = MMWPlayer.getDefinedMMWPlayer(args[0]);
		if(checkCommand("info",args,1)) {
			sender.sendMessage(mplayer.getPlayer().getLocation().toString());
			// TODO: Enter Info Output
			return true;
		} else if(checkCommand("modlist",args,1)) {
			// TODO: List Output
			// TODO Header of output
			MorePageDisplay display = new MorePageDisplay(new String[]{"< "+LanguageHandler.Mod_List+" [%/$] >"},sender.getName());
			for(ModInfoBase info:mplayer.getKnownMods()) {
				if(info instanceof ModInfo) {
					display.append(LanguageHandler.ServerMod+" "+info.getName());
				} else {
					display.append(LanguageHandler.ClientMod+" "+info.getName());
				}
			}
			display.append(LanguageHandler.Version_Header.toString());
			boolean flag = false;
			for(ModInfoBase info:mplayer.getKnownModsWithDifferentVersion()) {
				display.append(LanguageHandler.VersionMod+" "+info.getName(),!flag);
				flag = true;
			}
			if(!flag) {
				sender.sendMessage(LanguageHandler.None.toString());
			}
			display.display(sender);
			return true;
		}
		displayhelp(sender);
		return true;
	}

	private void displayhelp(CommandSender sender) {
		MorePageDisplay display = new MorePageDisplay(new String[]{"< "+LanguageHandler.Player_Info_Help+" [%/$] >"},sender.getName());
		display.display(sender);
	}

	public String returnhelp() {
		// TODO Auto-generated method stub
		return null;
	}

}
