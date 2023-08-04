class ComputerSystem {
    public Jobs jobs;

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
    }

    public ComputerSystem(Jobs jobs) {
        setJobs(jobs);
    }

    // 문제 3:
    public void printEpilog(Scheduler scheduler) {
        /*
         * 화면에 다음과 같이 시간 테이블을 출력함
         * Scheduling Algorithm: 알고리즘이름
         * 0 1 2 3 4 5 // 시간 십단위
         * 0123456789012345678901234567890123456789012345678901234 // 시간 일단위
         */
        System.out.println("Scheduling Algorithm: " + scheduler.getName());
        System.out.println("0         1         2         3         4         5");
        System.out.println("0123456789012345678901234567890123456789012345678901234");

        System.out.println();
    }

    public void run(Scheduler scheduler) { // 스케줄링 알고리즘을 테스트 함

        printEpilog(scheduler); // 화면에 단위시간 눈금자를 출력함
        scheduler.setJobs(jobs);

        while (scheduler.hasMoreProcessesToExecute()) { // 아직 더 실행해야 할 프로세스가 있는지 체크

            scheduler.clockInterrupt(); // 매 시간단위마다 스케줄러의 clock interrupt handler를 호출함

            if (scheduler.isSchedulable()) // 새로 스케줄링 해야하는 시점인지 체크
                scheduler.schedule(); // 새로 스케줄링 함

            try { // 우리 스케줄러에서 사용할 시간단위는 100ms: 빠르게 실행하려면 이 값을 10 또는 1로 줄여도 됨
                // 100ms마다 한번씩 위 scheduler.clockInterrupt()와 schedule()가 한번씩 호출됨
                Thread.sleep(10); // 100 millisecond 동안 정지했다가 리턴함
            }
            // sleep()하는 동안 다른 스레드에 의해 인터럽이 들어 온 경우, 여기서는 전혀 발생하지 않음
            catch (InterruptedException e) {
                // InterruptedException이 발생했을 경우 지금껏 호출된 함수 리스트를 출력해 볼 수 있음
                e.printStackTrace();
                return;
            }
        }
        System.out.println("\n");
    }
}