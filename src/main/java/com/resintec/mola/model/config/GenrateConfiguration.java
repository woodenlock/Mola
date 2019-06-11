package com.resintec.mola.model.config;

import java.io.Serializable;
import java.util.Arrays;

/**
 * other configurations you wann to define
 * 
 * @author woodenlock
 *
 */
public class GenrateConfiguration implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * author generated in target files
	 */
	private String author;

	/**
	 * weather need to override methods if target files is exist TODO will implement
	 * in feture version
	 */
	private Boolean override;

	/**
	 * config of the target DB connection
	 */
	private ConnectionConfiguration connection;

	/**
	 * target tables need to be generated
	 */
	private DataSourceConfiguration dataSource;

	/**
	 * config of generated files
	 */
	private AbstractFilePathConfiguration path;

	/**
	 * config of mappings of target fields
	 */
	private FieldMappingConfiguration[] mappings;

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Boolean getOverride() {
		return override;
	}

	public void setOverride(Boolean override) {
		this.override = override;
	}

	public ConnectionConfiguration getConnection() {
		return connection;
	}

	public void setConnection(ConnectionConfiguration connection) {
		this.connection = connection;
	}

	public DataSourceConfiguration getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSourceConfiguration dataSource) {
		this.dataSource = dataSource;
	}

	public AbstractFilePathConfiguration getPath() {
		return path;
	}

	public void setPath(AbstractFilePathConfiguration path) {
		this.path = path;
	}

	public FieldMappingConfiguration[] getMappings() {
		return mappings;
	}

	public void setMappings(FieldMappingConfiguration[] mappings) {
		this.mappings = mappings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GenrateConfiguration [author=");
		builder.append(author);
		builder.append(", override=");
		builder.append(override);
		builder.append(", connection=");
		builder.append(connection);
		builder.append(", dataSource=");
		builder.append(dataSource);
		builder.append(", path=");
		builder.append(path);
		builder.append(", mappings=");
		builder.append(Arrays.toString(mappings));
		builder.append("]");
		return builder.toString();
	}
}