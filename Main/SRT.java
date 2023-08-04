class SRT extends Scheduler {
    SRT(String name) {
        super(name);
    }

    @Override
    public void schedule() {
        super.schedule();

        if (readyQueue.isEmpty()) {
            currentProcess = readyQueue.peek();
        } else {
            int min = readyQueue.peek().getRemainingTime();
            int index = 0;
            for (int i = 0; i < super.numOfProcess; i++) {
                if (min > readyQueue.get(i).getRemainingTime()) {
                    min = readyQueue.get(i).getRemainingTime();
                    index = i;
                }
            }
            currentProcess = readyQueue.get(index);
        }
    }

    @Override
    public boolean isSchedulable() {
        return isNewProcessArrived;
    }

}