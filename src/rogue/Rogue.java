package rogue;

import jade.core.World;
import jade.ui.TiledTermPanel;
import jade.util.datatype.ColoredChar;
import java.awt.Color;
import java.util.Collection;
import rogue.creature.Monster;
import rogue.creature.Player;
import rogue.level.Level;
import rogue.level.Screen;

public class Rogue {
	public static void main(String[] args) throws InterruptedException {
		// Get current Operating System

        	TiledTermPanel term = TiledTermPanel.getFramedTerminal("Jade Rogue");
        	term.registerTile("dungeon.png", 5, 59, ColoredChar.create('#'));
        	term.registerTile("dungeon.png", 3, 60, ColoredChar.create('.'));
        	term.registerTile("dungeon.png", 5, 20, ColoredChar.create('@'));
        	term.registerTile("dungeon.png", 14, 30, ColoredChar.create('D', Color.red));


        	Player player = new Player(term);
        	World world = new Level(69, 24, player);
        	Screen.showFile(normalizePath("\\src\\rogue\\system\\start.txt","rogue/system/start.txt"), term, world);
        	term.getKey();
        	world.addActor(new Monster(ColoredChar.create('D', Color.red),"roter Drache"));
        	term.registerCamera(player, 5, 5);
		while(!player.expired()) {
			Collection<Monster> monsters = world.getActorsAt(Monster.class, player.pos());
			if(!monsters.isEmpty()){
				player.expire();
				continue;
			}
			term.clearBuffer();
			for(int x = 0; x < world.width(); x++)
				for(int y = 0; y < world.height(); y++)
					term.bufferChar(x + 11, y, world.look(x, y));
			term.bufferCameras();
			term.refreshScreen();
			world.tick();
        	}

		term.clearBuffer();
		Screen.showFile(normalizePath("\\src\\rogue\\system\\end.txt","rogue/system/end.txt"), term, world);
		term.getKey();
		System.exit(0);

	}

	public static String normalizePath(String winPath, String otherPath) {;
		if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
			System.out.println("Windows Operating System found");
			// We're running Windows, create an absolute Path
			return System.getProperty("user.dir").concat(winPath);
		} else {
			// Should work okay with relative Paths
			return new String(otherPath);
		}
	}
}
