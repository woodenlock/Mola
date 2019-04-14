package com.resintec.mola.model.db;

import java.io.Serializable;
import java.util.List;

/**
 * Real catalog in RMDB.Maybe different between different kinds of clients
 * @author woodenlock
 *
 */
public class Catalog implements Serializable{
    private static final long serialVersionUID = 1L;
    
    /**
     * name
     */
    private String name;
    
    /**
     * schema
     */
    private List<Schema>schemas;

    
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
     * @return the schemas
     */
    public List<Schema> getSchemas() {
        return schemas;
    }

    
    /**
     * @param schemas
     */
    public void setSchemas(List<Schema> schemas) {
        this.schemas = schemas;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Catalog [name=");
        builder.append(name);
        builder.append(", schemas=");
        builder.append(schemas);
        builder.append("]");
        return builder.toString();
    }
}
