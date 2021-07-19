package ml.pkom.easyapi.config;

import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;

import java.io.File;

import ml.pkom.easyapi.FileControll;

public class YamlConfig extends Config {

    public YamlConfig(File file) {
        super(file);
    }

    public YamlConfig(String file) {
        super(file);
    }

    public YamlConfig() {
        super();
    }

    public YamlConfig(Config config) {
        super(config);
    }

    private FlowStyle style = FlowStyle.BLOCK;

    public void setStyle(FlowStyle flowStyle) {
        style = flowStyle;
    }

    @SuppressWarnings("unchecked")
    public boolean load(File file) {
        try {
            String configData = FileControll.fileReadContents(file);
            Yaml yaml = new Yaml();
            configMap = (Map<String, Object>) yaml.load(configData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean save(File file, boolean pretty) {
        try {
            String configData = this.toYaml(file, pretty);
            FileControll.fileWriteContents(file, configData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String toYaml(File file, boolean pretty) {
        try {
            DumperOptions options = new DumperOptions();
            options.setDefaultFlowStyle(style);
            if (pretty) {
                options.setPrettyFlow(true);
            }
            Yaml yaml = new Yaml(options);
            String configData = yaml.dump(configMap);
            return configData;
        } catch (Exception e) {
            return null;
        }
    }
}
