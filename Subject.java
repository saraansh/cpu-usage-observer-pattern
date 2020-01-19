import java.util.ArrayList;
import java.util.List;

public class Subject {
    
    final int lowerLimit = 20, upperLimit = 80;

    private List<Observer> observers = new ArrayList<Observer>();
    private double cpuUsage;

    public double getCpuUsage() {
        return cpuUsage;
    }

    public void setCpuUsage(double cpuUsage) {
        String report = "%CPU Usage - " + cpuUsage;
        if (cpuUsage < 0 || cpuUsage > 100) {
            System.out.println("[Subject.java] Error in reported CPU Usage!\n" + report);
        } else {
            System.out.println(report);
            this.cpuUsage = cpuUsage;
            notifyAllObservers();
        }
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update(lowerLimit, upperLimit);
        }
    }
}