class Escalonamento {
    public static void main(String[] args) {
        int[] executionTime = {10, 6, 2, 4, 8};
        int[] priorityValue = {3, 5, 2, 1, 4};
        int quantum = 1;

        escalonador(executionTime, priorityValue, quantum);

    }

    private static void escalonador(int[] executionTime, int[] priorityValue, int quantum) {
        System.out.println("O tempo médio por round-robin é: " + roundRobin(executionTime.clone(), quantum));
        System.out.println("O tempo médio por escalonamento por prioridade é: " + priority(executionTime.clone(), priorityValue.clone()));
        System.out.println("O tempo médio por primeiro a chegar, primeiro a ser servido é: " + fcfs(executionTime.clone()));
        System.out.println("O tempo médio por job mais curto primeiro é: " + sjf(executionTime.clone()));
    }

    private static double sjf(int[] executionTime) {
        int sum = 0;
        int time = 0;

        System.out.print("Sequência de processos por SJF: ");
        for (int i = 0; i < executionTime.length; i++) {
            int aux = Integer.MAX_VALUE;
            int index = 0;

            for (int j = 0; j < executionTime.length; j++) {
                if (executionTime[j] < aux) {
                    aux = executionTime[j];
                    index = j;
                }
            }

            time += executionTime[index];
            sum += time;
            executionTime[index] = Integer.MAX_VALUE;
            System.out.print((char) (index + 65) + " ");
        }

        System.out.println();

        return (double)sum/executionTime.length;
    }

    private static double fcfs(int[] executionTime) {
        int sum = 0;
        int time = 0;

        System.out.print("Sequência de processos por FCFS: ");
        for (int i = 0; i < executionTime.length; i++) {
            time += executionTime[i];
            sum += time;
            System.out.print((char) (i + 65) + " ");
        }
        System.out.println();

        return (double)sum/executionTime.length;
    }

    private static double priority(int[] executionTime, int[] priorityValue) {
        int time = 0;
        int sum = 0;

        System.out.print("Sequência de processos por prioridade: ");
        for (int i = 0; i < executionTime.length; i++) {
            int aux = 0;
            int index = 0;

            for (int j = 0; j < executionTime.length; j++) {
                if (priorityValue[j] > aux) {
                    aux = priorityValue[j];
                    index = j;
                }
            }

            time += executionTime[index];
            sum += time;
            priorityValue[index] = 0;
            System.out.print((char) (index + 65) + " ");
        }
        System.out.println();

        return (double)sum/executionTime.length;
    }

    private static double roundRobin(int[] executionTime, int quantum) {
        int time = 0;
        int sum = 0;
        int count = 0;
        int index = 0;

        System.out.print("Sequência de processos por round-robin: ");
        while(true){
            for (int i = 0; i < executionTime.length; i++) {
                if(executionTime[i] > 0){
                    time += quantum;
                    executionTime[i] -= quantum;
                    if(executionTime[i] <= 0){
                        sum += time;
                        count++;
                        index = i;
                        System.out.print((char)(index + 65) + " ");
                    } 
                }
            }
            if(count == executionTime.length) break;
        }
        System.out.println();

        return (double)sum/executionTime.length;
    }
}