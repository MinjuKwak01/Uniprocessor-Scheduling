
class HRRN extends Scheduler {
    HRRN(String name) {
        super(name);
    }

    @Override
    public void schedule() {
        super.schedule();

        if (readyQueue.isEmpty()) {
            currentProcess = readyQueue.peek();
        } else {
            double max = 0.0;
            int index = 0;
            for (int i = 0; i < super.numOfProcess; i++) {
                if (max < readyQueue.get(i).getResponeRatioTime(currentTime)) {
                    max = readyQueue.get(i).getResponeRatioTime(currentTime);
                    index = i;
                }
            }
            currentProcess = readyQueue.get(index);
        }

    }
}