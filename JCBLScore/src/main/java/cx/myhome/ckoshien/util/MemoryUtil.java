package cx.myhome.ckoshien.util;

import java.text.DecimalFormat;

import org.apache.log4j.Logger;

public class MemoryUtil {
	private static Logger logger = Logger.getLogger("rootLogger");
	public static String getMemoryInfo() {
		String info = "";

		DecimalFormat format_mem =   new DecimalFormat("#,###KB");
		DecimalFormat format_ratio = new DecimalFormat("##.#");
		long free =  Runtime.getRuntime().freeMemory() / 1024;
		long total = Runtime.getRuntime().totalMemory() / 1024;
		long max =   Runtime.getRuntime().maxMemory() / 1024;
		long used =  total - free;
		double ratio = (used * 100 / (double)total);

		info += "Total   = " + format_mem.format(total);
		info += "\n";
		info += "Free    = " + format_mem.format(total);
		info += "\n";
		info += "use     = " + format_mem.format(used) + " (" + format_ratio.format(ratio) + "%)";
		info += "\n";
		info += "can use = " + format_mem.format(max);
		return info;
	}

	public static void viewMemoryInfo() {
	    //logger.info("---------- MemoryInfo --------");
	    logger.info(getMemoryInfo());
	    //logger.info("------------------------------");
	}

}
