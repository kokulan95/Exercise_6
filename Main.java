public class Main {
    public static void main(String[] args) {

        int runs =10000;
        Environment environment = new Environment();
        Agent a1 = new Agent("A1", 0, 0, environment);
        Agent a2 = new Agent("A2", 0, 4, environment);

        // Run simulation with agents a1 and a2 here...
        for (int i = 0; i < runs; i++){

            //reset agent

            a1.resetAgent(0, 0, 0);
            a2.resetAgent(0, 4, 0);

            while (a1.reachedExit() || a2.reachedExit()){
                if (a1.reachedExit()){
                    a1.chooseAction();
                    a1.updateQValue(a1.x, a1.y, a1.environment.getDifficulty(a1.x, a1.y) );
                    a1.increaseAmountOfMovements(1);
                }
                if (a2.reachedExit()){
                    a2.chooseAction();
                    a2.updateQValue(a2.x, a2.y, a2.environment.getDifficulty(a2.x, a2.y) );

                    a2.increaseAmountOfMovements(1);
                }
            }

            System.out.println("Run: " + i);
            System.out.println(a1.name + " Number of Movements: " + a1.amountsOfMovements);
            System.out.println(a2.name + " Number of Movements: " + a2.amountsOfMovements);
            System.out.println("--------------------------------");

        }

        System.out.println("End of Simulation");

    }
}
