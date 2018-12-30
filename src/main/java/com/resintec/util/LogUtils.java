package com.resintec.util;

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
		if(null == clazz) {
			throw new IllegalArgumentException("Unimplement log.");
		}
		if (CommonUtils.isBlank(template)) {
			return;
		}
		if (!CommonUtils.isBlank(args)) {
			try {
                for (Object object : args) {
                	template = template.replaceFirst("\\{\\}", CommonUtils.detailsToString(object));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
		}
		template = "[" + level.getFlag() + "]" + " [" + clazz.getName() + " " + CommonUtils.getCurrentTime() + "] "
				+ template;

		switch (level) {
		case DEBUG:
		case INFO:
			System.out.println(template);
			break;
		case WARN:
		case ERROR:
			System.err.println(template);
			break;
		default:
			throw new IllegalArgumentException("Unknown argument:" + level);
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
		DEBUG("DEBUG", 1),

		/** info **/
		INFO("INFO", 2),

		/** warn **/
		WARN("WARN", 3),

		/** error **/
		ERROR("ERROR", 4);

		/**
		 * flag
		 */
		private String flag;

		/**
		 * level
		 */
		private Integer level;

		private Level(String flag, Integer level) {
			this.flag = flag;
			this.level = level;
		}

		public String getFlag() {
			return flag;
		}

		public Integer getLevel() {
			return level;
		}
	}
}
