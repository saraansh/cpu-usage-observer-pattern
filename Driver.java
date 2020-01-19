import java.io.*;

public class Driver {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Subject subject = new Subject();
        new DemoObserver(subject);

        // to read outputs form shell commands
        ProcessBuilder processBuilder = new ProcessBuilder();
        // if multiple paramters are required - modify the command to get those and parse them separately in getSnapshot function
        // also return a dictionary / hashmap for better handling of the multiple parameters
        final String command = "top -b -d1 -n1|grep -i \"Cpu(s)\"|head -c21|cut -d \' \' -f2|cut -d \'%\' -f1";
        // System.out.println(command);
        processBuilder.command(command);

        System.out.println("Time for monitoring (in seconds) - ");
        int seconds = Integer.parseInt(br.readLine());

        for (int i = 0; i < seconds; i++) {
            subject.setCpuUsage(getSnapshot(processBuilder));
        }

    }
    
    public static double getSnapshot(ProcessBuilder processBuilder) {
        // get a snapshot of top command here
        // optionally you can perform extraction of the cpuUsage in a dedicated function as well
        // return cpuUsage or memUsage as required
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            double cpuUsage = Double.parseDouble(reader.readLine());
            return cpuUsage;
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("[Driver.java] Error in fetching %CPU Usage");
            return -1;
        }
    }
}