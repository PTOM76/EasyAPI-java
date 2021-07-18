package ml.pkom.easyapi.config;

import java.util.LinkedHashMap;
import java.io.File;
import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import ml.pkom.easyapi.FileControll;

public class JsonConfig extends Config {
    public JsonConfig(File file) {
        super(file);
    }

    public JsonConfig(String file) {
        super(file);
    }

    public JsonConfig() {
        super();
    }

    public boolean load(File file) {
        try {
            String configData = FileControll.fileReadContents(file);
            Gson gson = new Gson();
            Type jsonMap = new TypeToken<LinkedHashMap<String, Object>>() {
            }.getType();
            configMap = gson.fromJson(configData, jsonMap);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean save(File file, boolean pretty) {
        try {
            Gson gson = new Gson();
            if (pretty) {
                gson = new GsonBuilder().setPrettyPrinting().create();
            }
            String configData = gson.toJson(configMap);
            FileControll.fileWriteContents(file, configData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
