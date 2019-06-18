package com.cccts.helpers;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

public class YmlHelper {
    private Yaml yaml;
    private File file;

    /**
     * 指定要操作的文件
     * @param file
     */
    public YmlHelper(File file) {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        this.yaml = new Yaml(options);
        this.file = file;
    }


    /**
     * 在目标文件中添加新的配置信息
     *
     * @param key   添加的key值
     * @param value 添加的对象(如key下方还有链接则添加LinkedHashMap)
     * @author Relic
     * @title addIntoYml
     * @date 2019/1/29 20:52
     */
    public void addIntoYml(String key, Object value) throws IOException {
        //载入当前yml文件
        LinkedHashMap<String, Object> dataMap = yaml.load(new FileReader(file));
        //如果yml内容为空,则会引发空指针异常,此处进行判断
        if (null == dataMap) {
            dataMap = new LinkedHashMap<>();
        }
        dataMap.put(key, value);
        //将数据重新写回文件
        yaml.dump(dataMap, new FileWriter(file));
    }

    /**
     * 从目标yml文件中读取出所指定key的值
     *
     * @param key    需要获取信息的key值
     * @return java.lang.Object
     * @author Relic
     * @title getFromYml
     * @date 2019/1/29 20:56
     */
    public Object getFromYml(String key) throws IOException {
        //载入文件
        LinkedHashMap<String, Object> dataMap = yaml.load(new FileReader(file));
        //获取当前key下的值(如果存在多个节点,则value可能为map,自行判断)
        return dataMap.get(key);
    }
}
