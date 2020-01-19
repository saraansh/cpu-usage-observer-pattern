public class DemoObserver extends Observer {

    public DemoObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update(int lowerLimit, int upperLimit) {
        // lowerLimit = -1;
        // upperLimit = -1;

        if (subject.getCpuUsage() < lowerLimit) {
            lowUsageAction();
        } else if (subject.getCpuUsage() > upperLimit) {
            highUsageAction();
        } else {
            // do something if required
            System.out.println("Demo Observer receives normal cpu usage");
        }
    }

    @Override
    public void lowUsageAction() {
        System.out.println("Demo observer takes action on low cpu usage");
    }

    @Override
    public void highUsageAction() {
        System.out.println("Demo observer takes action on high cpu usage");
    }
}