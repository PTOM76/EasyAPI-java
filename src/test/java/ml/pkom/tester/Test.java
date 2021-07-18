package ml.pkom.tester;

import ml.pkom.easyapi.config.JsonConfig;
import ml.pkom.easyapi.config.YamlConfig;

public class Test {
    public static void main(String[] args) {
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.set("hoge.str", "hoge");
        jsonConfig.set("hoge.int", 1);
        jsonConfig.set("hoge.bool", true);
        jsonConfig.set("str.ja.aiueo", "あいうえお");
        jsonConfig.set("str.ja.kakikukeko", "かきくけこ");
        jsonConfig.save("test/test.json");

        YamlConfig yamlConfig = new YamlConfig();
        yamlConfig.set("hoge.str", "hoge");
        yamlConfig.set("hoge.int", 1);
        yamlConfig.set("hoge.bool", true);
        yamlConfig.set("str.ja\\.aiueo", "あいうえお");
        yamlConfig.set("str.ja\\.kakikukeko", "かきくけこ");
        yamlConfig.save("test/test.yml");
    }
}
