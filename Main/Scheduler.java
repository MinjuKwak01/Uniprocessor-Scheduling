import java.util.LinkedList;

abstract class Scheduler {

    private String name; // 스케줄러 이름
    protected int currentTime; // 현재시간
    protected int numOfProcess; // 현재 readyQueue에 있는 총 프로세스의 개수
    protected Process currentProcess; // 현재 실행되고 있는 프로세스를 지칭하는 변수
    protected boolean isNewProcessArrived; // 현재시간에 새로운 프로세스가 도착한 경우 true, 아니면 false
    protected LinkedList<Process> readyQueue; // ready 상태의 모든 프로세스들을 모아 놓은 ready queue
    private Jobs jobs; // 앞으로 도착할 프세세스들의 정보를 가지고 있음

    protected Scheduler(String name) { // 스케줄러의 각 필드를 초기화함
        this.name = name;
        currentTime = -1;
        numOfProcess = 0;
        currentProcess = null;
        isNewProcessArrived = false;
        readyQueue = new LinkedList<Process>();
    }

    public boolean hasMoreProcessesToExecute() { // 모든 프로세스들의 실행이 종료되었는지 체크
        // 새로 도착할 프로세스가 아직 남아 있거나 또는 레디 큐에 프로세스가 있는 경우 (true 반환)
        // 새로 도착할 프로세스가 더 이상 없고 레디 큐에도 프로세스가 없으면 종료해도 됨 (false 반환)
        return (jobs.hasNextProcess() || (numOfProcess != 0));
    }

    // 매 시간 단위마다 호출되는 함수. ComputerSystem::run()에서 100ms마다 한번씩 호출됨
    // 매 시간단위마다 clock interrupt가 들어와 실행된다고 생각하면 됨
    // 각 스케줄링 알고리즘별로 이 함수를 재정의하되,
    // 각 스케줄링 알고리즘의 재정의 함수에서 super클래스의 이 함수를 제일 먼저 호출해야 함
    public void clockInterrupt() {
        currentTime++; // 현재시간을 1 증가함
        if (currentProcess != null) { // 현재 실행되고 있는 프로세스가 있다면
            currentProcess.incExecTime(); // 현재 실행되는 프로세스의 실행시간을 1 증가함
            System.out.print(currentProcess.getName()); // 1 시간단위만큼 실행된 프로세스의 이름을 출력함
        } else
            System.out.print(" "); // 현재 실행되는 프로세스가 없을 경우 출력

        // 새로 도착한 프로세스가 있을 경우 ready queue의 맨 끝에 추가
        // getNewProcess()는 현재시간 currentTime과 도착시간이 동일한 프로세스를 찾고
        // 해당 프로세스 정보를 기반으로 Process 객체를 생성하여 반환해 줌;
        // 이 시간에 도착한 프로세스가 없을 경우 null 반환함
        for (Process p; (p = jobs.getNewProcess(currentTime)) != null;) {
            isNewProcessArrived = true; // p는 새로 도착한 프로세스 객체
            ++numOfProcess; // 총 프로세스의 개수를 증가
            readyQueue.add(p); // ready 큐의 맨 끝에 프로세스 p 삽입
        }
    }

    public void setJobs(Jobs jobs) {
        this.jobs = jobs;
        jobs.reset(); // 처음부터 다시 스케줄링하기 위해 프로세스 정보도 처음부터 시작하도록 설정
    }

    public String getName() {
        return name;
    } // 스케줄러 이름 반환

    // 현재 시간이 1씩 증가할 때마다 새로 스케줄링 해야할지 말지를 결정하는 함수
    // 모든 스케줄링 알고리즘에서 공통으로 적용됨: 스케줄링 시점을 결정하는 함수
    // 각 스케줄링 알고리즘별로 필요한 경우 이 함수를 재정의하여야 함(오버라이딩)
    public boolean isSchedulable() {
        return ((currentProcess == null) && isNewProcessArrived) || // 현재 실행 프로세스가 없는 상태에서 새로운 프로세스가 도착했을 때
                ((currentProcess != null) && currentProcess.isFinished()); // 현재 실행 중인 프로세스가 실행을 종료했을 때
    }

    // 재정의 함수에서 super클래스의 이 함수를 제일 먼저 호출해야 한며, 그런 후
    // 각 스케줄링 알고리즘별로 이 함수를 재정의해야 함(오버라이딩).
    // 각 스케줄링 알고리즘별로 다음에 실행할 우선순위가 가장 높은 프로세스를 선택해야 함
    public void schedule() {
        // 현재 실행중인 프로세스의 실행이 완료된 경우
        if ((currentProcess != null) && currentProcess.isFinished()) {
            readyQueue.remove(currentProcess); // 현재 프로세스(currentProcess)를 ready queue에서 제거함
            --numOfProcess; // 총 프로세스의 개수를 감소
            currentProcess = null;
        }
        // 이 메소드에선 실행이 종료된 프로세스를 위처럼 큐에서 제거하는 역할만하고 실제 스케줄링은 하지 않음
        // 이 함수에서 리턴된 후 각 스케줄링 알고리즘별로 재정의된 메소드에서
        // 우선순위가 가장 높은 프로세스를 선택해야 함.
        // FCFS클래스의 schedule() 함수 참조.
    }

    public int getCurrentTime() {
        return 0;
    }

}