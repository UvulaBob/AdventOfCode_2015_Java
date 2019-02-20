import java.util.HashSet;

public class Part2 {
    private static HashSet<Weapon> weapons = new HashSet<Weapon>();
    private static HashSet<Armor> armors = new HashSet<Armor>();
    private static HashSet<Ring> rings = new HashSet<Ring>();

    public static void main(String[] args) {
        initialize();

        int maximumGoldSpent = 0;

        for (Weapon weapon : weapons) {
            for (Armor armor : armors) {
                for (Ring ring1 : rings) {
                    for (Ring ring2 : rings) {
                        if (ring1.name.equals(ring2.name) && !(ring1.name.equals("Nothing"))) {
                            continue;
                        }

                        int goldSpent = weapon.cost + armor.cost + ring1.cost + ring2.cost;
                        if (goldSpent < maximumGoldSpent) {
                            continue;
                        }

                        int villainHitPoints = 103;
                        int villainDamage = 9;
                        int villainArmor = 2;
                        int heroHitPoints = 100;
                        int heroDamage = weapon.damage + ring1.damage + ring2.damage;
                        int heroArmor = armor.armor + ring1.armor + ring2.armor;

                        while (heroHitPoints > 0) {
                            villainHitPoints -= heroDamage - villainArmor;
                            if (villainHitPoints < 1) {
                                break;
                            }
                            heroHitPoints -= villainDamage - heroArmor;
                        }

                        if (villainHitPoints > 0) {
                            maximumGoldSpent = (goldSpent > maximumGoldSpent) ? goldSpent : maximumGoldSpent;
                        }
                    }
                }
            }
        }

        System.out.println("Maximum Gold Spent For Loss: " + maximumGoldSpent);
        System.out.println("Done!");

    }

    private static void initialize() {
        weapons.add(new Weapon("Dagger", 8, 4));
        weapons.add(new Weapon("Shortsword", 10, 5));
        weapons.add(new Weapon("Warhammer", 25, 6));
        weapons.add(new Weapon("Longsword", 40, 7));
        weapons.add(new Weapon("Greataxe", 74, 8));

        armors.add(new Armor("Nothing", 0, 0));
        armors.add(new Armor("Leather", 13, 1));
        armors.add(new Armor("Chainmail", 31, 2));
        armors.add(new Armor("Splintmail", 53, 3));
        armors.add(new Armor("Bandedmail", 75, 4));
        armors.add(new Armor("Platemail", 102, 5));

        rings.add(new Ring("Nothing", 0, 0, 0));
        rings.add(new Ring("Damage +1", 25, 1, 0));
        rings.add(new Ring("Damage +2", 50, 2, 0));
        rings.add(new Ring("Damage +3", 100, 3, 0));
        rings.add(new Ring("Defense +1", 20, 0, 1));
        rings.add(new Ring("Defense +2", 40, 0, 2));
        rings.add(new Ring("Defense +3", 80, 0, 3));
    }
}
