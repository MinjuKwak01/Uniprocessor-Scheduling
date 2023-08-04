import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Jobs jobs = new Jobs();
        System.out.println();
        ComputerSystem cs = new ComputerSystem(jobs);

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
                    cs.setJobs(jobs);
                    break;
                case 2:
                    jobs.processTest();
                    break;
                // FCFS 객체를 생성한 후 이를 인자로 사용하여 cs.run()를 호출한다.
                // cs.run()에서 FCFS 스케줄러를 작동시킴; "FCFS"는 스케줄러 이름이다.
                case 3:
                    cs.run(new FCFS("FCFS"));
                    break;
                case 4:
                    cs.run(new SPN("SPN"));
                    break;
                case 5:
                    cs.run(new HRRN("HRRN"));
                    break;
                case 6:
                    cs.run(new SRT("SRT"));
                    break;
                case 7:
                    cs.run(new RR("RR(q=1)", 1));
                    break;
                case 8:
                    cs.run(new RR("RR(q=4)", 4));
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