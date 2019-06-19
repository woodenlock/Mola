package com.resintec.mola.util;

import java.io.PrintStream;
import java.util.Arrays;

/**
 * uitl to provide a lighting log implement
 * @author woodenlock
 *
 */
public class LogUtils {
	/**
	 * prevent to ctreate Log entity by new LogUtils().new Log()
	 */
	private LogUtils() {}
	
	/**
	 * return the instance of the log
	 * @param clazz
	 * @return
	 */
	public static Log build(Class<?> clazz) {
		return new LogUtils().new Log(clazz);
	}
	
	/** log instance **/
	public class Log{
		private Class<?> clazz;
		
		public Log(Class<?> clazz) {
			this.clazz = clazz;
		}
		
		/**
		 * output log of the debug
		 * @param template
		 * @param args
		 */
		public void debug(String template, Object... args) {
			output(template, Level.DEBUG, clazz, args);
		}
		
		/**
		 * output log of the info
		 * @param template
		 * @param args
		 */
		public void info(String template, Object... args) {
			output(template, Level.INFO, clazz, args);
		}
		
		/**
		 * output log of the warn
		 * @param template
		 * @param args
		 */
		public void warn(String template, Object... args) {
			output(template, Level.WARN, clazz, args);
		}
		
		/**
		 * output log of the error
		 * @param template
		 * @param args
		 */
		public void error(String template, Object... args) {
			output(template, Level.ERROR, clazz, args);
		}
		
		/**
		 * return the class operator of the log implement
		 * @return
		 */
		public Class<?> getClazz() {
			return clazz;
		}
	}
	
	/**
	 * output the target log
	 * @param template
	 * @param level
	 * @param clazz
	 * @param args
	 * TODO consider to overwrite by java.util.logging.Handler to deal with logs from freemarker
	 */
	private static void output(String template, Level level, Class<?> clazz, Object... args) {
		if(null == level || null == clazz) {
			throw new IllegalArgumentException("Failed to output log due to illegal params: " + level + "," + clazz);
		}
		if(!CommonUtils.isBlank(template)){
		    if (!CommonUtils.isBlank(args)) {
	            try {
	                for (Object object : args) {
	                    if(object instanceof Throwable){
	                        template += "\n";
	                    }
	                    template = template.replaceFirst("\\{\\}", CommonUtils.toString(object));
	                }
	            } catch (Throwable e) {
	                throw new RuntimeException("Failed to output log due to fail to group content: "
	                    + template + "," + level + "," + clazz + "," + Arrays.toString(args));
	            }
	        }
	        template = "[" + level.getFlag() + "] [" + clazz.getName() + " " + CommonUtils.getCurrentTime() + "] "
	                + template;
	        level.getStream().println(template);
		}
	}

	/**
	 * log level
	 * 
	 * @author woodenlock
	 *
	 */
	public enum Level {
		/** debug **/
		DEBUG("DEBUG", 1, System.out),

		/** info **/
		INFO("INFO", 2, System.out),

		/** warn **/
		WARN("WARN", 3, System.err),

		/** error **/
		ERROR("ERROR", 4, System.err);

		/**
		 * flag
		 */
		private String flag;

		/**
		 * level
		 */
		private Integer level;
		
		/**
		 * output implements
		 */
		private PrintStream stream;

		private Level(String flag, Integer level, PrintStream stream) {
			this.flag = flag;
			this.level = level;
			this.stream = stream;
		}

		public String getFlag() {
			return flag;
		}

		public Integer getLevel() {
			return level;
		}
		
		public PrintStream getStream() {
		    return stream;
		}
	}
}
