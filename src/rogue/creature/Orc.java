/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package rogue.creature;

import jade.fov.RayCaster;
import jade.path.*;
import jade.ui.Terminal;
import jade.util.Dice;
import jade.util.datatype.ColoredChar;
import jade.util.datatype.Coordinate;
import jade.util.datatype.Direction;
import java.util.Arrays;
import java.util.Collection;

/**
 *
 * An Orc ist a weak Monster
 * he moves randomly like the Dragon and hit the Player if he can
 */
public class Orc extends Monster {

    PathFinder pathfinder = new AStar();
    RayCaster fov;
    int attackRadius;

    public Orc(Terminal term) {
        super(ColoredChar.create('O'), "Orc", 10, 3, term);
        this.typenumber =1;
        fov = new RayCaster();
        attackRadius = 5;
    }

    @Override
    public void act() {
            for (Direction dir : Arrays.asList(Direction.values())) {
            Player player = world().getActorAt(Player.class, x() + dir.dx(), y() + dir.dy());
            if (player != null) {
                fight(player);
                return;
            }
        }
        
            Collection<Coordinate> viewField = fov.getViewField(this.world(), this.pos().x(), this.pos().y(), attackRadius);
           System.out.println(viewField.size());
            for (Coordinate coordinate : viewField) {
                if (this.world().getActorAt(Player.class, coordinate) != null) {
                    Direction dir = this.pos().directionTo(pathfinder.getPath(this.world(), this.pos(), coordinate).get(0));
                    move(dir);
                    return;
                }
            }

            


                move(Dice.global.choose(Arrays.asList(Direction.values())));
            }
        }

    



