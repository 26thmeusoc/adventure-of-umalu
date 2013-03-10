package rogue.creature;

import java.util.Arrays;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Direction;
import java.util.Random;

public class Monster extends Creature {

    private String name;
    private int maxHitpoints;
    public Monster(ColoredChar face) {
        super(face);
        strength = 5;
        maxHitpoints = 10;
        hitpoints = maxHitpoints;
    }

    public Monster(ColoredChar face, String name,int maxHitpoints, int strength) {
        super(face);
        this.strength = strength;
        this.maxHitpoints= maxHitpoints;
        hitpoints = maxHitpoints;
        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public void act() {
        boolean fight = false;

        for (Direction dir : Arrays.asList(Direction.values())) {
            Player player = world().getActorAt(Player.class, x() + dir.dx(), y() + dir.dy());
            if (player != null) {
                fight(player);
                //move(dir); taken out, because the Monster doesnt move, when it fights
                fight = true;
                break;

            }

        }
        
        if (!fight) {

            move(Dice.global.choose(Arrays.asList(Direction.values())));
        }
    }
    /*
     * fight of the Moster aganst the Player
     * causes random damage between 1 and 5
     */
    // TODO Clean up Messages in Console, to use just a single line
    private void fight(Player opponent) {
        System.out.println("der " + name + "greift dich an");
	// Create Randomizer
        Random random = new Random();
	// Generate Damage
        int abzug = random.nextInt(strength)+1;
	// Do Damage to Oppenent
        opponent.loseHitpoints(abzug);
	// Print Result
        System.out.println("Du hast "+ abzug + "HP verloren");
        System.out.println("verbleibende HP:"+ opponent.hitpoints);
    }
}
