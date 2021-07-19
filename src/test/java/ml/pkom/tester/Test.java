package ml.pkom.tester;

import ml.pkom.easyapi.config.Config;
import ml.pkom.easyapi.config.JsonConfig;
import ml.pkom.easyapi.config.YamlConfig;

public class Test {
    public static void main(String[] args) {
        Config config = new Config();
        config.set("hoge.str", "hoge");
        config.set("hoge.int", 1);
        config.set("hoge.bool", true);
        config.set("str.ja.aiueo", "あいうえお");
        config.set("str.ja.kakikukeko", "かきくけこ");
        YamlConfig yamlConfig = new YamlConfig(config);
        JsonConfig jsonConfig = new JsonConfig(config);
        jsonConfig.save("test/test.json");
        yamlConfig.save("test/test.yml");
    }
}
