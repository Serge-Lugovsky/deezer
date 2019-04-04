package Managers;

public class SingletonAppManager {
    private static SingletonAppManager singletonInstance = null;
    public AppManager manager;

    private SingletonAppManager() {
        manager = new AppManager();
    }

    public static SingletonAppManager getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new SingletonAppManager();
        }
        return singletonInstance;
    }
}
