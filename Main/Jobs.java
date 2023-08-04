import java.util.LinkedList;
import java.util.Scanner;

class Jobs {
    // 도착할 각 프로세스의 이름, 도착시간, 서비스시간 등을 배열로 관리함
    private String processNames[] = { "A", "B", "C", "D", "E", "A", "B", "C", "D", "E" };
    private int arrivalTimes[] = { 0, 2, 4, 6, 8, 30, 32, 34, 36, 38 };
    private int serviceTimes[] = { 3, 6, 4, 5, 2, 6, 3, 4, 5, 2 };

    private int index; // 다음 번에 도착할 프로세스의 위 배열 인덱스

    public Jobs() {
        printJobs();
    }

    // 매 시간단위마다 호출되며,
    // 각 프로세스의 도착시간과 현재시간 cTime가 동일한 프로세스를 찾아
    // 해당 프로세스 정보를 인자로하여 Process 객체를 생성하여 반환함
    public Process getNewProcess(int cTime) {
        if (index < arrivalTimes.length && // 아직 도착할 프로세스(index)가 남아 있는 경우
                arrivalTimes[index] == cTime) { // 현재시간이 다음 프로세스(index)의 도착시간과 동일한 경우
            int i = index++; // 인덱스 값을 증가시켜 그 다음 프로세스를 가르키게 함
            return new Process(processNames[i], arrivalTimes[i], serviceTimes[i]);
        } // 도착한 프로세스가 있을 경우 위처럼 새 프로세스를 생성하여 반환하고,
        return null; // 현재시간 cTime에 도착한 프로세스가 없을 경우
    }

    public void printJobs() {
        for (String n : processNames)
            System.out.printf("%2s ", n);
        System.out.println();
        for (int t : arrivalTimes)
            System.out.printf("%2d ", t);
        System.out.println();
        for (int s : serviceTimes)
            System.out.printf("%2d ", s);
        System.out.println();
    }

    public Jobs(Scanner s) { // 생성자
        // 실행할 총 프로세스의 개수를 입력 받음
        System.out.print("The number of processes? ");
        int num = s.nextInt();

        // num개의 원소를 가지는 문자열 processNames[] 배열을 생성
        processNames = new String[num];
        // 적절한 입력용 메시지를 출력하고("input ? process names : ")
        System.out.print("input " + num + " process names : ");
        // for 문을 이용하여 num개 프로세스들의 이름을 입력 받아 processNames[] 배열에 저장
        for (int i = 0; i < num; i++) {
            String pname;
            pname = s.next();
            processNames[i] = pname;
        }

        // num개의 원소를 가지는 정수형 arrivalTimes[] 배열을 생성
        arrivalTimes = new int[num];
        // 적절한 입력용 메시지를 출력하고
        System.out.print("input " + num + " arrival times : ");
        // for 문을 이용하여 num개 프로세스들의 도착시간을 입력 받아 arrivalTimes[] 배열에 저장
        for (int i = 0; i < num; i++) {
            int atime;
            atime = s.nextInt();
            arrivalTimes[i] = atime;
        }

        // num개의 원소를 가지는 정수형 serviceTimes[] 배열을 생성
        serviceTimes = new int[num];
        // 적절한 입력용 메시지를 출력하고
        System.out.print("input " + num + " service times : ");
        // for 문을 이용하여 num개 프로세스들의 서비스시간을 입력 받아 serviceTimes[] 배열에 저장
        for (int i = 0; i < num; i++) {
            int stime;
            stime = s.nextInt();
            serviceTimes[i] = stime;
        }

        System.out.println();
        printJobs();
    }

    // 처음부터 다시 스케줄링을 시작하고자 하는 경우 호출
    public void reset() {
        index = 0;
    }

    // 아직 도착하지 않은 프로세스가 더 있는지 조사
    public boolean hasNextProcess() {
        return index < arrivalTimes.length;
    }

    public void processTest() {
        reset();
        LinkedList<Process> rq = new LinkedList<>();

        System.out.println("Create processes and print their member data.");
        for (int i = 0; i < processNames.length; ++i) {
            Process p = new Process(processNames[i], arrivalTimes[i], serviceTimes[i]);
            rq.add(p);
            System.out.println(p); // 각 프로세스의 멤버 변수들을 출력한다.
        }
        for (Process p : rq) {
            int eTime = p.getServiceTime(); // 이 값이 실행시간이 되도록 할 것이다.
            if (eTime > 3) // 서비스시간이 3보다 큰 경우 실행시간을 반으로 설정하기 위함임
                eTime = (int) (eTime * 0.5 + 0.5); // 실행시간의 반을 반올림
            for (int i = 0; i < eTime; ++i) // 실행시간을 1씩 증가시킨다.
                p.incExecTime();
        }
        System.out.println("\nPrint returned values of member methods of each process.");
        for (Process p : rq) // 각 프로세스의 멤버 메소드의 반환값들을 출력한다.
            p.println(40); // 40은 현재시간을 의미함
    }

}
