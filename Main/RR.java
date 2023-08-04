class RR extends Scheduler {
    private int quantum; // RR 알고리즘의 time quantum, time slice
    private int execTime; // 현재 프로세스의 주어진 time quantum을 소진한 실행시간,
    // time quantum 보다 작아야 하며, 만약 동일하면 time slice를 모두 소진한 것임
    // 이 값은 매 시간 단위마다 1씩 증가함

    RR(String name, int qauntum) {
        // 슈퍼 클래스의 생성자를 먼저 호출하고
        // qauntum 멤버 초기화
        super(name);
        this.quantum = qauntum;
        execTime = 0;
    }

    // 현재 실행중인 프로세스가 주어진 time quantum을 모두 소진한 경우 true
    private boolean timeQuantumExausted() {
        // 현재 실행하는 프로세스가 존재하고(currentProcess 값을 체크할 것)
        // 현재 execTime이 quantum과 동일한 경우 true 반환;
        if (execTime == quantum && currentProcess != null)
            return true;
        else {
            return false;
        }
    }

    @Override
    public void schedule() {
        super.schedule();

        if (timeQuantumExausted()) {
            Process f = readyQueue.pollFirst();
            readyQueue.offerLast(f);
        }
        currentProcess = readyQueue.peek();
        execTime = 0;
    }

    @Override
    public boolean isSchedulable() {
        return super.isSchedulable() || timeQuantumExausted();
    }

    @Override
    public void clockInterrupt() {
        super.clockInterrupt();
        execTime++;
    }

}