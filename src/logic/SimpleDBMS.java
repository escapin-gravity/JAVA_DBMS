package logic; // 이 구문이 있어야 컴파일러가 인식

import java.util.Hashtable;

public class SimpleDBMS {
    private Hashtable<String, String> dataStore;

    public SimpleDBMS() {
        dataStore = new Hashtable<>();
    }

    public void put(String key, String value) {
        dataStore.put(key, value);
    }

    public String get(String key) {
        return dataStore.get(key);
    }

    public boolean containsKey(String key) {
        return dataStore.containsKey(key);
    }

    public String remove(String key) {
        return dataStore.remove(key);
    }
    
    public void update(String key, String newValue) {
        if (dataStore.containsKey(key)) {
            dataStore.put(key, newValue);
        }
    }
}
