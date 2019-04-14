package com.resintec.mola.model.config;

import java.io.Serializable;

/**
 * configuration for jdbc connection
 * @author woodenlock
 *
 */
public class ConnectionConfiguration implements Serializable{

    private static final long serialVersionUID = 1L;

    /**
     * url
     */
    private String url = "jdbc:mysql://127.0.0.1:3306/test?serverTimezone=GMT%2B8";

    /**
     * user name
     */
    private String userName = "root";

    /**
     * password
     */
    private String password = "root";

    /**
     * driverClassFullPath
     */
    private String driverClassFullPath = "com.mysql.cj.jdbc.Driver";

    /**
     * private key
     */
    private String privateKey;
    
    public ConnectionConfiguration(){}
    
    public ConnectionConfiguration(String url, String userName, String password, String driverClassFullPath, String privateKey){
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.driverClassFullPath = driverClassFullPath;
        this.privateKey = privateKey;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the driverClassFullPath
     */
    public String getDriverClassFullPath() {
        return driverClassFullPath;
    }

    /**
     * @param driverClassFullPath
     */
    public void setDriverClassFullPath(String driverClassFullPath) {
        this.driverClassFullPath = driverClassFullPath;
    }

    /**
     * @return the privateKey
     */
    public String getPrivateKey() {
        return privateKey;
    }

    /**
     * @param privateKey
     */
    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("ConnectionConfiguration [url=");
        builder.append(url);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", password=");
        builder.append(password);
        builder.append(", driverClassFullPath=");
        builder.append(driverClassFullPath);
        builder.append(", privateKey=");
        builder.append(privateKey);
        builder.append("]");
        return builder.toString();
    }
}
