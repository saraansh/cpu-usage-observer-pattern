import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Driver {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Subject subject = new Subject();
        new DemoObserver(subject);

        // to read outputs form shell commands
        final String command = "top -b -d1 -n1";
        String[] commandSequence = command.split(" ");
        // if multiple paramters are required - modify the command to get those and parse them separately in getSnapshot function
        // also return a dictionary / hashmap for better handling of the multiple parameters
        ProcessBuilder processBuilder = new ProcessBuilder(commandSequence);

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
            InputStream is = process.getInputStream();
            String output = "";
            int value = -1;
            while ((value = is.read()) != -1) {
                output += (char) value;
            }
            // System.out.println(output);
            return extractCpuUsage(output);
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("[Driver.java] Error in fetching %CPU Usage");
            return -1;
        }
    }

    public static double extractCpuUsage(String str) {
        Pattern pattern = Pattern.compile("%Cpu\\(s\\):[ ]+[1-9]*[0-9]\\.[0-9][1-9]*");
        Matcher m = pattern.matcher(str);
        if (m.find()) {
            return Double.parseDouble(" " + str.substring(m.start() + 8, m.end()));
        } else {
            System.out.println("this.executed");
            return -1;
        }
    }
}