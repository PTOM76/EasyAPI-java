package ml.pkom.easyapi.config;

import java.util.LinkedHashMap;
import java.util.Map;
import java.io.File;

public class Config implements IConfig {
    public Map<String, Object> configMap = new LinkedHashMap<>();

    public Config(File file) {
        load(file);
    }

    public Config(String file) {
        this(new File(file));
    }

    public Config() {};

    @SuppressWarnings("unchecked")
    public Object get(String key) {
        try {
            key = key.replace("\\.", "$&#92;$");
            String[] keyList = key.split("\\.");
            if (keyList.length == 1) {
                key = key.replace("$&#92;$", ".");
                return configMap.get(key);
            }
            int i = 0;
            Map<String, Object> inMap = new LinkedHashMap<>();
            for (String k : keyList) {
                i++;
                k = k.replace("$&#92;$", ".");
                if (keyList.length == i) {
                    return inMap.get(k);
                }
                if (i == 1) {
                    inMap = (Map<String, Object>) configMap.get(k);
                    continue;
                }
                inMap = (Map<String, Object>) inMap.get(k);
            }
            return null;
        } catch (Exception e) {
            return null;
        }

    }

    public String getString(String key) {
        return (String) get(key);
    }

    public int getInt(String key) {
        return (Integer) get(key);
    }

    public boolean getBoolean(String key) {
        return (Boolean) get(key);
    }

    @SuppressWarnings("unchecked")
    public boolean set(String key, Object value) {
        try {
            key = key.replace("\\.", "$&#92;$");
            String[] keyList = key.split("\\.");
            if (keyList.length == 1) {
                key = key.replace("$&#92;$", ".");
                configMap.put(key, value);
                return true;
            }
            int i = 0;
            Map<String, Object> inMap = new LinkedHashMap<>();
            for (String k : keyList) {
                i++;
                k = k.replace("$&#92;$", ".");
                if (keyList.length == i) {
                    inMap.put(k, value);
                    continue;
                }
                Map<String, Object> beforeInMap = inMap;
                if (configMap.containsKey(k)) {
                    inMap = (LinkedHashMap<String, Object>) configMap.get(k);
                }else {
                    inMap = new LinkedHashMap<String, Object>();
                }
                if (i == 1) {
                    configMap.put(k, inMap);
                    continue;
                }
                beforeInMap.put(k, inMap);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean setString(String key, String value) {
        return set(key, value);
    }

    public boolean setInt(String key, int value) {
        return set(key, value);
    }

    public boolean setBoolean(String key, boolean value) {
        return set(key, value);
    }

    public boolean load(String file) {
        return load(new File(file));
    }

    public boolean load(File file) {
        // この関数は継承先で定義
        return false;
    }

    public boolean save(String file) {
        return save(new File(file));
    }

    public boolean save(File file) {
        return save(file, true);
    }

    public boolean save(String file, boolean pretty) {
        return save(new File(file), pretty);
    }

    public boolean save(File file, boolean pretty) {
        // この関数は継承先で定義
        return false;
    }
}
