package machine;

import java.util.Scanner;
class Supply
{
    public int money=550;
    public int water=400;
    public int milk=540;
    public int beans=120;
    public int cups=9;
}

public class CoffeeMachine {
    public enum States {
        CHOOSING_ACTION,
        BUYING,
        FILLING,
        TAKING,
        CHECKING,
        EXIT;
    }

    States stany;
    Supply zapasy;
    static Scanner scanner = new Scanner(System.in);

    public CoffeeMachine() {
        this.stany = States.CHOOSING_ACTION;
        this.zapasy = new Supply();
    }


    public  boolean check(Supply a, int type) {
        boolean enough = true;
        switch (type) {
            case 1:
                if (a.water < 250) {
                    System.out.println("Sorry, not enough water!");
                    enough = false;
                }
                if (a.beans < 16) {
                    System.out.println("Sorry, not enough coffee beans!");
                    enough = false;
                }
                if (a.cups == 0) {
                    System.out.println("Sorry, not enough cups!");
                    enough = false;
                }
                break;
            case 2:
                if (a.water < 350) {
                    System.out.println("Sorry, not enough water!");
                    enough = false;
                }
                if (a.milk < 75) {
                    System.out.println("Sorry, not enough coffee beans!");
                    enough = false;
                }
                if (a.beans < 20) {
                    System.out.println("Sorry, not enough coffee beans!");
                    enough = false;
                }
                if (a.cups == 0) {
                    System.out.println("Sorry, not enough cups!");
                    enough = false;
                }
                break;

            case 3:
                if (a.water < 200) {
                    System.out.println("Sorry, not enough water!");
                    enough = false;
                }
                if (a.milk < 100) {
                    System.out.println("Sorry, not enough coffee beans!");
                    enough = false;
                }
                if (a.beans < 12) {
                    System.out.println("Sorry, not enough coffee beans!");
                    enough = false;
                }
                if (a.cups == 0) {
                    System.out.println("Sorry, not enough cups!");
                    enough = false;
                }
                break;

        }
        stany=States.CHOOSING_ACTION;
        return enough;


    }

    public Supply buy(Supply a) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        Scanner scanner = new Scanner(System.in);
        String type;
        Boolean hasSupply;
        type = scanner.next();
        switch (type) {
            case "1":
                hasSupply = check(a, 1);
                if (hasSupply == false) {
                    break;
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    a.money += 4;
                    a.water -= 250;
                    a.beans -= 16;
                    a.cups -= 1;
                    break;
                }
            case "2":
                hasSupply = check(a, 2);
                if (hasSupply == false) {
                    break;
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    a.money += 7;
                    a.water -= 350;
                    a.milk -= 75;
                    a.beans -= 20;
                    a.cups -= 1;
                    break;
                }
            case "3":
                hasSupply = check(a, 3);
                if (hasSupply == false) {
                    break;
                } else {
                    System.out.println("I have enough resources, making you a coffee!");
                    a.money += 6;
                    a.water -= 200;
                    a.milk -= 100;
                    a.beans -= 12;
                    a.cups -= 1;
                    break;
                }
            case "back": {
                break;
            }
        }
        stany=States.CHOOSING_ACTION;
        return a;
        //System.out.println("The coffee machine has:");
        //System.out.printf("%d of water\n%d of milk\n%d of coffe beans\n%d of cups\n%d of money\n",water,milk,beans,cups,money);
    }

    public Supply take(Supply a) {

        System.out.println("I gave you $" + a.money);
        a.money = 0;
        stany=States.CHOOSING_ACTION;
        return a;
        //System.out.println("The coffee machine has:");
        //System.out.printf("%d of water\n%d of milk\n%d of coffe beans\n%d of cups\n%d of money\n",water,milk,beans,cups,money);


    }

    public Supply fill(Supply b) {
        Scanner scanner = new Scanner(System.in);
        int a;
        System.out.println("Write how many ml of water do you want to add:");
        a = scanner.nextInt();
        b.water += a;
        System.out.println("Write how many ml of milk do you want to add:");
        a = scanner.nextInt();
        b.milk += a;
        System.out.println("Write how many grams of coffee beans do you want to add:");
        a = scanner.nextInt();
        b.beans += a;
        System.out.println("Write how many disposable cups of coffee do you want to add: ");
        a = scanner.nextInt();
        b.cups += a;
        stany=States.CHOOSING_ACTION;
        return b;

        //System.out.println("The coffee machine has:");
        //System.out.printf("%d of water\n%d of milk\n%d of coffe beans\n%d of cups\n%d of money\n",water,milk,beans,cups,money);
    }

    public void remaining(Supply a) {

        System.out.println("The coffee machine has:");
        System.out.printf("%d of water\n%d of milk\n%d of coffe beans\n%d of cups\n$%d of money\n", a.water, a.milk, a.beans, a.cups, a.money);
        stany=States.CHOOSING_ACTION;
    }



    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        System.out.println("Write action (buy, fill, take, remaining, exit):");

        boolean run = true;
        //Scanner scanner=new Scanner(System.in);
        do {
            //System.out.println("The coffee machine has:");
            //System.out.printf("%d of water\n%d of milk\n%d of coffe beans\n%d of cups\n%d of money\n", water, milk, beans, cups, money);
            String choice = scanner.next();
            switch (choice) {
                case "buy":
                    machine.stany= States.BUYING;
                    machine.buy(machine.zapasy);
                    break;
                case "fill":
                    machine.stany= States.FILLING;
                    machine.fill(machine.zapasy);
                    break;
                case "take":
                    machine.stany= States.TAKING;
                    machine.take(machine.zapasy);
                    break;
                case "remaining":
                    machine.stany= States.CHECKING;
                    machine.remaining(machine.zapasy);
                    break;
                case "exit":
                    machine.stany= States.EXIT;
                    break;
            }
        }while (!machine.stany.equals(States.EXIT));



    }
}
