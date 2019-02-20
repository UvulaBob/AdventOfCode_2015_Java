public class Part1 {
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

            if (heroArmorTimer > 0) {
                heroArmorTimer--;
            }

            if (villainPoisonTimer > 0) {
                villainHitPoints -= 3;
                villainPoisonTimer--;
            }

            if (villainHitPoints <= 0) {
                System.out.println("You win!");
                System.out.println("Mana Spent: " + totalManaSpent);
                return;
            }

            if (heroRechargeTime > 0) {
                heroMana += 101;
                heroRechargeTime--;
            }

            if (heroMana < 53) {
                System.out.println("Out of mana! You lose!");
                return;
            }

            switch (heroTurn) {
                case 1:
                    shield();
                    break;
                case 2:
                    recharge();
                    break;
                case 3:
                    poison();
                    break;
                case 4:
                    magicMissile();
                    break;
                case 5:
                    magicMissile();
                    break;
                case 6:
                    magicMissile();
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
            }

            // Villain Turn
            if (heroArmorTimer > 0) {
                heroArmorTimer--;
            }

            if (villainPoisonTimer > 0) {
                villainHitPoints -= 3;
                villainPoisonTimer--;
            }

            if (heroRechargeTime > 0) {
                heroMana += 101;
                heroRechargeTime--;
            }

            if (villainHitPoints <= 0) {
                System.out.println("You win!");
                System.out.println("Mana Spent: " + totalManaSpent);
                return;
            }

            heroHitPoints -= (heroArmorTimer > 0) ? 8 : 1 ;
        }

        System.out.println("You got killed! You lose!");

    }

    private static void magicMissile() {
        System.out.println("Casting Magic Missile");
        villainHitPoints -= 4;
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
        System.out.println("Casting Shield");
        heroArmorTimer = 6;
        heroMana -= 113;
        totalManaSpent += 113;
    }

    private static void poison() {
        System.out.println("Casting Poison");
        villainPoisonTimer = 6;
        heroMana -= 173;
        totalManaSpent += 173;
    }

    private static void recharge() {
        System.out.println("Casting Recharge");
        heroRechargeTime = 5;
        heroMana -= 229;
        totalManaSpent += 229;
    }

}
