package logic;

import java.util.HashMap;
import java.util.Map;

public class SimpleDBMS {
    private Map<String, String> dataMap;

    public SimpleDBMS() {
        dataMap = new HashMap<>();
    }

    public void insertData(String key, String value) {
        dataMap.put(key, value);
    }

    public String getData(String key) {
        return dataMap.get(key);
    }

    public Map<String, String> getAllData() {
        return new HashMap<>(dataMap);
    }

    public void updateData(String key, String value) {
        if (dataMap.containsKey(key)) {
            dataMap.put(key, value);
        } else {
            System.out.println("Key not found");
        }
    }

    public void deleteData(String key) {
        if (dataMap.containsKey(key)) {
            dataMap.remove(key);
        } else {
            System.out.println("Key not found");
        }
    }
}


