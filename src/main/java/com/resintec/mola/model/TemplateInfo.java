package com.resintec.mola.model;

import java.io.Serializable;

/**
 * template basic info
 * 
 * @author  woodenlock
 * @version  [v2.0.4, 2019年7月16日]
 */
public class TemplateInfo implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * path of the template(not include the canonical name)
     */
    private String path;

    /**
     * path of the template
     */
    private String fileName;

    /**
     * @return 返回 path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param 对path进行赋值
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return 返回 fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param 对fileName进行赋值
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("TemplateInfo [path=");
        builder.append(path);
        builder.append(", fileName=");
        builder.append(fileName);
        builder.append("]");
        return builder.toString();
    }

}