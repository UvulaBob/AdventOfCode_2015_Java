public class Part2 {
    private static int villainHitPoints = 55;
    private static int heroHitPoints = 50;
    private static int heroMana = 500;
    private static int heroArmorTimer = 0;
    private static int villainPoisonTimer = 0;
    private static int heroRechargeTime = 0;
    private static int totalManaSpent = 0;

    public static void main(String[] args) {

        int heroTurn = 0;

        while (heroHitPoints > 0) {
            //Hero Turn
            heroTurn++;
            heroHitPoints--;
            System.out.println("Player bleeding for 1 point of damage.");
            System.out.println("Current Player Hit Points: " + heroHitPoints);
            if (heroHitPoints <= 0) {
                System.out.println("You bled out! You lose!");
                System.out.println("Current Mana: " + heroMana);
                System.out.println("Mana Spent: " + totalManaSpent);
                System.out.println("Number of Player turns: " + heroTurn);
                return;
            }

            if (heroArmorTimer > 0) {
                heroArmorTimer--;
            }

            if (villainPoisonTimer > 0) {
                villainHitPoints -= 3;
                System.out.println("Villain suffers poison on Player turn for 3 points of damage.");
                System.out.println("Current Villain Hit Points: " + villainHitPoints);
                villainPoisonTimer--;
            }

            if (villainHitPoints <= 0) {
                System.out.println("You win!");
                System.out.println("Current Mana: " + heroMana);
                System.out.println("Mana Spent: " + totalManaSpent);
                System.out.println("Number of Player turns: " + heroTurn);
                return;
            }

            if (heroRechargeTime > 0) {
                heroMana += 101;
                heroRechargeTime--;
            }

            if (heroMana < 53) {
                System.out.println("Out of mana! You lose!");
                System.out.println("Current Mana: " + heroMana);
                System.out.println("Mana Spent: " + totalManaSpent);
                System.out.println("Number of Player turns: " + heroTurn);
                return;
            }

            switch (heroTurn) {
                case 1:
                    poison();
                    break;
                case 2:
                    shield();
                    break;
                case 3:
                    recharge();
                    break;
                case 4:
                    poison();
                    break;
                case 5:
                    shield();
                    break;
                case 6:
                    recharge();
                    break;
                case 7:
                    poison();
                    break;
                case 8:
                    magicMissile();
                    break;
                case 9:
                    magicMissile();
                    break;
                case 10:
                    magicMissile();
                    break;
                case 11:
                    magicMissile();
                    break;
                case 12:
                    magicMissile();
                    break;
            }

            // Villain Turn
            if (heroArmorTimer > 0) {
                heroArmorTimer--;
            }

            if (villainPoisonTimer > 0) {
                villainHitPoints -= 3;
                System.out.println("Villain suffers poison on his turn for 3 points of damage.");
                System.out.println("Current Villain Hit Points: " + villainHitPoints);
                villainPoisonTimer--;
            }

            if (heroRechargeTime > 0) {
                heroMana += 101;
                heroRechargeTime--;
            }

            if (villainHitPoints <= 0) {
                System.out.println("You win!");
                System.out.println("Current Mana: " + heroMana);
                System.out.println("Mana Spent: " + totalManaSpent);
                System.out.println("Number of Player turns: " + heroTurn);
                return;
            }

            int incomingDamage = (heroArmorTimer > 0) ? 1 : 8;
            heroHitPoints -= incomingDamage;
            System.out.println("Punched for " + incomingDamage + " point(s) of damage.");
            System.out.println("Current Player Hit Points: " + heroHitPoints);
        }

        System.out.println("You got killed! You lose!");
        System.out.println("Current Mana: " + heroMana);
        System.out.println("Mana Spent: " + totalManaSpent);
        System.out.println("Number of Player turns: " + heroTurn);

    }

    private static void magicMissile() {
        System.out.println("Casting Magic Missile");
        villainHitPoints -= 4;
        System.out.println("Current Villain Hit Points: " + villainHitPoints);
        heroMana -= 53;
        totalManaSpent += 53;
    }

    private static void drain() {
        villainHitPoints -= 2;
        heroHitPoints += 2;
        heroMana -= 73;
        totalManaSpent += 73;
    }

    private static void shield() {
        if (heroArmorTimer > 0) {
            throw new RuntimeException("Time remaining on shield - unable to cast.");
        } else {
            System.out.println("Casting Shield");
            heroArmorTimer = 6;
            heroMana -= 113;
            totalManaSpent += 113;
        }
    }

    private static void poison() {
        if (villainPoisonTimer > 0) {
            throw new RuntimeException("Time remaining on poison - unable to cast.");
        } else {
            System.out.println("Casting Poison");
            villainPoisonTimer = 6;
            heroMana -= 173;
            totalManaSpent += 173;
        }
    }

    private static void recharge() {
        if (heroRechargeTime > 0) {
            throw new RuntimeException("Time remaining on recharge - unable to cast.");
        } else {
            System.out.println("Casting Recharge");
            heroRechargeTime = 5;
            heroMana -= 229;
            totalManaSpent += 229;
        }
    }

}
