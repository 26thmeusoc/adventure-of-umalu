/*
 * Item.java
 * Copyright (C) 2013 dirk <dirk@Valinor.local>
 *
 * Distributed under terms of the MIT license.
 */
package rogue.creature.util;

import jade.ui.TiledTermPanel;
import jade.ui.Terminal;
import java.util.ArrayList;

/**
 * Represents a single Item for players Inventory
 */
public class Item {
	private String name;
	private int goldValue;
	private int[] modificators;
	private int itemType;
	private int type;
	private int maxDurability;
	private int durability;
	private boolean equipped;

	/**
	 * Describes Item is as Helmet
	 */
	public static final int ITEMTYPE_HEAD = 0;

	/**
	 * Describes Item as Chest Armor
	 */
	public static final int ITEMTYPE_BODY = 1;

	/**
	 * Describes Item as a Weapon (Sword)
	 */
	public static final int ITEMTYPE_SWORD = 2;

	/**
	 * Creates a new Item Object wth given name and Goldvalue
	 *
	 * @param name Name of Item
	 * @param goldValue Goldvalue of Object
	 */
	public Item(String name, int goldValue, int type, int bonusDamage, int bonusHealth) {
		// Name einfuegen
		this.name = name;
		// Platzhalter fuer den Fall der Faelle
		this.goldValue = goldValue;
		// Was fuer eine Art Item ist das?
		this.type = type;
		// Setze die Modifikatoren
		this.modificators = new int[2];
		this.modificators[0] = bonusDamage;
		this.modificators[1] = bonusHealth;
		equipped = false;
	}

	/**
	 * Returns Goldvalue of this item.
	 *
	 * @return Goldvalue of this Item
	 */
	public int getGoldValue() {
		return goldValue;
	}

	/**
	 * Returns Name of Item
	 *
	 * @return Name of Item
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns Damagebonus of this Item
	 * 
	 * @return Damagebonus of this Item
	 */
	public int getDamageBonus() {
		return modificators[0];
	}

	/**
	 * Returns Healthbonus of this Item
	 *
	 * @return Healthbonus of this Item
	 */
	public int getHealthBonus() {
		return modificators[1];
	}

	/**
	 * Prints Information about given Item on Screen.
	 * 
	 * @param term currently used terminal
	 * @param inventory currently used inventory
	 */
	public void showItem(Terminal term, Inventory inventory) {
		Item item = this;
		ArrayList<String> lines = new ArrayList<String>();
		lines.add(item.getName());
		lines.add("Bonus Damage: "+item.getDamageBonus());
		lines.add("Bonus Health: "+item.getHealthBonus());
		String result = new String();
		if (equipped) {
			System.out.println();
		}
		char key = ' ';
		try{
			key = term.getKey();
		} catch (InterruptedException e) {
			System.out.println("!Error: ");
			e.printStackTrace();
		}
		switch (key) {
			case 'q':
				// Leave this screen (go back to inventory) and the inventory untouched.
				break;
			case 's':
				if (!equipped) {
					// Put this Item on.

				} else {
					// Cannot put this item down without substitute right now.
				}
				break;
			case 'd':
				// Destroy item, when it is not worn
				if (!equipped) {
					ArrayList<String> warningScreen = new ArrayList<String>();
					warningScreen.add("WARNUNG!");
					warningScreen.add("========");
					warningScreen.add("Sicher das "+this.getName()+" zerstoert werden soll?");
					warningScreen.add("(J)a");
					warningScreen.add("(N)ein");
					try {
						char selection = term.getKey();
						if (selection == 'j' || selection == 'J') {
							inventory.removeItem(this);
						}
					} catch (InterruptedException e) {
						System.out.println("!Error: InterruptedException");
						e.printStackTrace();
					}
				}
				break;
		}
	}
}