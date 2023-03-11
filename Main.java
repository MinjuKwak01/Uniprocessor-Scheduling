import java.util.*;

class Jobs {
    // 도착할 각 프로세스의 이름, 도착시간, 서비스시간 등을 배열로 관리함
    private String processNames[];
    private int arrivalTimes[];
    private int serviceTimes[];
    private int index; // 다음 번에 도착할 프로세스의 위 배열 인덱스

    public void printJobs() {
        for (String n : processNames)
            System.out.printf("%2s ", n);
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

}

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Jobs jobs = null;

        while (true) {
            System.out.println("************************ Main Menu *******************");
            System.out.println("* 0.Exit  1.Jobs 2.Process                           *");
            System.out.println("* 3.FCFS  4.SPN  5.HRRN  6.SRT  7.RR(q=1)  8.RR(q=4) *");
            System.out.println("******************************************************");
            System.out.print("Menu item number? ");

            int idx = scan.nextInt();
            if (idx == 0)
                break;

            switch (idx) {
                case 1:
                    jobs = new Jobs(scan);
                    break;
                default:
                    System.out.println("WRONG menu item\n");
                    break;
            }
            System.out.println();
        }
        System.out.println("Good bye.");
        scan.close();
    }
}