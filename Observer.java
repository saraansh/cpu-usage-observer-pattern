public abstract class Observer {
    protected Subject subject;

    public abstract void update(int lowerLimit, int upperLimit);

    public abstract void lowUsageAction();

    public abstract void highUsageAction();
}