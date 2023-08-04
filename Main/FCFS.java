class FCFS extends Scheduler {
    FCFS(String name) {
        super(name);
    } // 스케줄러 이름

    @Override
    public void schedule() {
        super.schedule();
        // 다음에 실행할 프로세스 선택
        // 큐의 헤드에 있는 원소를 반환 (삭제하지는 않음) , or 없으면 null 리턴
        currentProcess = readyQueue.peek();
        // 실제 시스템에서는 여기서 currentProcess에게 CPU를 넘김.
    }
}