package com.resintec.mola.model.config;

import java.io.Serializable;

/**
 * target single file config
 * 
 * @author woodenlock
 *
 */
public class SinglePathConfiguration implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * the target basic diretory
	 */
	private String targetBasicDirectory;

	/**
	 * the path of the target package
	 */
	private String targetPackagePath;

	/**
	 * suffix of the target file name
	 */
	private String targetSuffix;

	/**
	 * extension of the target file name
	 */
	private String targetExtension;

	/**
	 * full directory of the source template file
	 */
	private String templateFullPath;

	/**
	 * file name of the source template file
	 */
	private String templateFileName;

	/**
	 * whether need to output
	 */
	private boolean output = true;

	public SinglePathConfiguration() {
	}

	public SinglePathConfiguration(String targetBasicDirectory, String targetPackagePath, String targetSuffix,
			String targetExtension, String templateFullPath, String templateFileName, boolean output) {
		this.targetBasicDirectory = targetBasicDirectory;
		this.targetPackagePath = targetPackagePath;
		this.targetSuffix = targetSuffix;
		this.targetExtension = targetExtension;
		this.templateFullPath = templateFullPath;
		this.templateFileName = templateFileName;
		this.output = output;
	}

	/**
	 * @return the targetBasicDirectory
	 */
	public String getTargetBasicDirectory() {
		return targetBasicDirectory;
	}

	/**
	 * @param targetBasicDirectory
	 */
	public void setTargetBasicDirectory(String targetBasicDirectory) {
		this.targetBasicDirectory = targetBasicDirectory;
	}

	/**
	 * @return the targetPackagePath
	 */
	public String getTargetPackagePath() {
		return targetPackagePath;
	}

	/**
	 * @param targetPackagePath
	 */
	public void setTargetPackagePath(String targetPackagePath) {
		this.targetPackagePath = targetPackagePath;
	}

	/**
	 * @return the targetSuffix
	 */
	public String getTargetSuffix() {
		return targetSuffix;
	}

	/**
	 * @param targetSuffix
	 */
	public void setTargetSuffix(String targetSuffix) {
		this.targetSuffix = targetSuffix;
	}

	/**
	 * @return the targetExtension
	 */
	public String getTargetExtension() {
		return targetExtension;
	}

	/**
	 * @param targetExtension
	 */
	public void setTargetExtension(String targetExtension) {
		this.targetExtension = targetExtension;
	}

	/**
	 * @return the templateFullPath
	 */
	public String getTemplateFullPath() {
		return templateFullPath;
	}

	/**
	 * @param templateFullPath
	 */
	public void setTemplateFullPath(String templateFullPath) {
		this.templateFullPath = templateFullPath;
	}

	/**
	 * @return the templateFileName
	 */
	public String getTemplateFileName() {
		return templateFileName;
	}

	/**
	 * @param templateFileName
	 */
	public void setTemplateFileName(String templateFileName) {
		this.templateFileName = templateFileName;
	}

	public boolean isOutput() {
		return output;
	}

	public void setOutput(boolean output) {
		this.output = output;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SinglePathConfiguration [targetBasicDirectory=");
		builder.append(targetBasicDirectory);
		builder.append(", targetPackagePath=");
		builder.append(targetPackagePath);
		builder.append(", targetSuffix=");
		builder.append(targetSuffix);
		builder.append(", targetExtension=");
		builder.append(targetExtension);
		builder.append(", templateFullPath=");
		builder.append(templateFullPath);
		builder.append(", templateFileName=");
		builder.append(templateFileName);
		builder.append(", output=");
		builder.append(output);
		builder.append("]");
		return builder.toString();
	}
}