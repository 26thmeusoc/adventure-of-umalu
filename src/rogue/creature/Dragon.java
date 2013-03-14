/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package rogue.creature;

import java.util.Arrays;
import java.util.Random;

import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;
import jade.ui.Terminal;
import jade.util.Dice;
import jade.util.datatype.Direction;
import java.util.Arrays;

import rogue.level.Screen;

/**
 * TODO Delete this Class, when it is not used anymore, as instances of Montster do the same thing right now
 * @author alle
 */
public class Dragon extends Monster {
   /*
    * every Dragon has 100 Hitpoints and Strength 5(until now)
    */
    public Dragon(ColoredChar face, String name, Terminal term) {
        super(face,name, 100, 5, term);
    }

    @Override
    public void act() {
		boolean fight = false;

		for (Direction dir : Arrays.asList(Direction.values())) {
			Player player = world().getActorAt(Player.class, x() + dir.dx(),
					y() + dir.dy());
			if (player != null) {
				fight(player);
				fight = true;
				break;
			}
		}

		if (!fight) {
			move(Dice.global.choose(Arrays.asList(Direction.values())));
		}
	}

	@Override
	public void fight(Player opponent) {
		// TODO Auto-generated method stub {
	        System.out.println("der " + name + "greift dich an");
		// Create Randomizer
	        Random random = new Random();
		// Generate Damage
	        int abzug = random.nextInt(strength)+1;
		// Do Damage to Oppenent
	        opponent.loseHitpoints(abzug);
		// Print Result
	        System.out.println("Du hast "+ abzug + " HP verloren");
	        System.out.println("verbleibende HP: "+ opponent.hitpoints);
		Screen.redrawEventLine(name+" macht "+abzug+" Schaden (Rest: "+opponent.hitpoints+")");
		try {
			term.getKey();
		} catch(InterruptedException e) {
			System.out.println("!InterruptedException");
			e.printStackTrace();
		}
	    }

	}


