package com.company;

public class Main {

    public static void main(String[] args) {

package com.company;

import java.util.Random;

        public class Main {
            public static int bossHealth = 900;
            public static int bossDamage = 60;
            public static String bossDefence = "";
            public static int[] heroesHealth = {260, 250, 270, 300, 400, 240, 200, 340};
            public static int[] heroesDamage = {15, 20, 10, 0, 10, 5, 10 + bossDamage/5, 50};
            public static String[] heroesAttackType = {
                    "Physical", "Magical", "Kinetic", "Medical", "Rock", "Dodge", "Cut", "Thunder"};

            public static void main(String[] args) {
                printStatistics();
                while (!isGameFinished()) {
                    round();
                }
            }

            public static void round() {
                if (bossHealth > 0) {
                    chooseBossDefence();
                    bossHits();
                }
                heroesHit();
                printStatistics();
            }

            public static void chooseBossDefence() {
                Random random = new Random();
                int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
                bossDefence = heroesAttackType[randomIndex];
                System.out.println("Boss choose: " + bossDefence);
            }

            public static boolean isGameFinished() {
                if (bossHealth <= 0) {
                    System.out.println("Heroes won!!!");
                    return true;
                }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&
                heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
                boolean allHeroesDead = true;
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0) {
                        allHeroesDead = false;
                        break;
                    }
                }
                if (allHeroesDead) {
                    System.out.println("Boss won!!!");
                }
                return allHeroesDead;
            }

            public static void heroesHit() {
                for (int i = 0; i < heroesDamage.length; i++) {
                    if (heroesHealth[i] > 0 && bossHealth > 0) {
                        if (bossDefence == heroesAttackType[i]) {
                            Random random = new Random();
                            int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                            if (bossHealth - heroesDamage[i] * coeff < 0) {
                                bossHealth = 0;
                            } else {
                                bossHealth = bossHealth - heroesDamage[i] * coeff;
                            }
                            System.out.println(
                                    "Critical Damage: " + heroesDamage[i] * coeff);
                        } else {
                            if (bossHealth - heroesDamage[i] < 0) {
                                bossHealth = 0;
                            } else {
                                bossHealth = bossHealth - heroesDamage[i];
                            }
                        }
                    }
                }
            }

            public static void bossHits() {
                for (int i = 0; i < heroesHealth.length; i++) {
                    if (heroesHealth[i] > 0 && bossHealth > 0) {
                        if (heroesHealth[i] - bossDamage < 0) {
                            heroesHealth[i] = 0;
                        }else if (heroesHealth[4] - bossDamage/5 > 0){
                            heroesHealth[4] = heroesHealth[4] - bossDamage/5;
                        }
                        else {
                            heroesHealth[i] = heroesHealth[i] - bossDamage + 10;
                        }
                    }
                }
            }

            public static void printStatistics() {
                System.out.println("++++++++++++++");
                System.out.println("Boss health: " + bossHealth + " ["
                        + bossDamage + "]");
                for (int i = 0; i < heroesHealth.length; i++) {
                    System.out.println(heroesAttackType[i]
                            + " health: " + heroesHealth[i] + " ["
                            + heroesDamage[i] + "]");
                }
                System.out.println("++++++++++++++");

                Random random1 = new Random();
                int opp = random1.nextInt(9);
                if (opp > 5){
                    System.out.println("Thor deafened boss");
                }else{
                    System.out.println("Thor couldn't deafen boss");
                }
                System.out.println("Thor health: " + heroesHealth[7]);

                if (heroesHealth[5] > 0){
                    System.out.println("Dodger avoided of the hit");
                }else if (heroesHealth[5] == 0){
                    System.out.println("Dodger didn't avoid of the hit");
                }

                System.out.println("Dodger health: " + heroesHealth[5]);
                System.out.println("Berserk health: " + heroesHealth[6] + ". Berserk returned " + bossDamage/10 + " of boss's hit");
                System.out.println("------------------------------------");

            }

        }

    }
}
