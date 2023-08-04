class SPN extends Scheduler {
    SPN(String name) {
        super(name);
    }

    @Override
    public void schedule() {
        super.schedule();

        if (readyQueue.isEmpty()) {
            currentProcess = readyQueue.peek();
        } else {
            int index = 0;
            int min = readyQueue.peek().getServiceTime();
            for (int i = 0; i < super.numOfProcess; i++) {
                if (min > readyQueue.get(i).getServiceTime()) {
                    min = readyQueue.get(i).getServiceTime();
                    index = i;
                }
            }
            currentProcess = readyQueue.get(index);
        }
    }
}