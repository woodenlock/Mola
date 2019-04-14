package com.resintec.mola.model.db;

import java.io.Serializable;
import java.util.List;

/**
 * Real schema in RMDB.Maybe different between different kinds of clients
 * @author woodenlock
 *
 */
public class Schema implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /**
     * name
     */
    private String name;
    
    /**
     * 
     */
    private List<Table>tables;

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    
    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    
    /**
     * @return the tables
     */
    public List<Table> getTables() {
        return tables;
    }

    
    /**
     * @param tables
     */
    public void setTables(List<Table> tables) {
        this.tables = tables;
    }


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Schema [name=");
        builder.append(name);
        builder.append(", tables=");
        builder.append(tables);
        builder.append("]");
        return builder.toString();
    }
    
}
