package ml.pkom.easyapi.config;

import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;

import java.io.File;

import ml.pkom.easyapi.FileControll;

public class YamlConfig extends Config {

    /**
     * 
     * @param file ファイル
     */
    public YamlConfig(File file) {
        super(file);
    }

    /**
     * 
     * @param file ファイル名 
     */
    public YamlConfig(String file) {
        super(file);
    }

    public YamlConfig() {
        super();
    }

    /**
     * Config→JsonConfigへ変換
     * 
     * @param config Config
     */
    public YamlConfig(Config config) {
        super(config);
    }

    private FlowStyle style = FlowStyle.BLOCK;

    /**
     * 出力するYamlのスタイルの設定
     * 
     * @param flowStyle FlowStyle
     */
    public void setStyle(FlowStyle flowStyle) {
        style = flowStyle;
    }

    /**
     * Yamlファイルを読み込んでMapへ変換
     * 
     * @param file ファイル
     * @return 成功→true / 失敗→false
     */
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

    /**
     * MapをYamlへ変換し、Yamlファイルとして保存
     * 
     * @param file   ファイル
     * @param pretty 整形
     * @return 成功→true / 失敗→false
     */
    public boolean save(File file, boolean pretty) {
        try {
            String configData = this.toYaml(pretty);
            FileControll.fileWriteContents(file, configData);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * MapをYamlへ変換 失敗した場合はnullを返す
     * 
     * @param pretty 整形
     * @return Yamlの文字列
     */
    public String toYaml(boolean pretty) {
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
