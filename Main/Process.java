class Process {
    private String name; // 프로세스 이름
    private int arrivalTime; // 프로세스 도착시간
    private int serviceTime; // 프로세스 서비스시간, 실행해야 할 총 시간
    private int executionTime; // 프로세스의 현재까지 실행된 시간

    // 문제 2-3: 프로세스의 각 필드를 초기화함
    Process(String name, int arrivalTime, int serviceTime) {
        // 클래스의 해당 멤버들을 초기화하라.
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;
        executionTime = 0;
    }

    public void incExecTime() {
        executionTime++;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public int getWaitingTime(int cTime) {
        return cTime - arrivalTime;
    }

    public int getRemainingTime() {
        return serviceTime - executionTime;
    }

    public boolean isFinished() {
        if (serviceTime == executionTime) {
            return true;
        } else
            return false;
    }

    public double getResponeRatioTime(int cTime) {
        int sum = getWaitingTime(cTime) + serviceTime;
        return (double) sum / serviceTime;
    }

    public String getName() {
        return name;
    }

    public void println(int cTime) {
        System.out.printf("%s: s(%d) e(%d) r(%d) w(%2d) rr(%5.2f) f(%s)\n",
                name, getServiceTime(), executionTime, getRemainingTime(),
                getWaitingTime(cTime), getResponeRatioTime(cTime), isFinished());
    }

    public String toString() {
        return String.format("%s: a(%2d) s(%d) e(%d)",
                name, arrivalTime, serviceTime, executionTime);
    }
}