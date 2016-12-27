package cx.myhome.ckoshien.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.DailyRollingFileAppender;
import org.apache.log4j.helpers.LogLog;

public class DailyRollingFileAppenderEx extends DailyRollingFileAppender{
	SimpleDateFormat sdf;
	private String scheduledFilename;
	Date now = new Date();

	void rollOver() throws IOException {

	    /* Compute filename, but only if datePattern is specified */
	    if (getDatePattern() == null) {
	      errorHandler.error("Missing DatePattern option in rollOver().");
	      return;
	    }

	    String datedFilename = fileName+sdf.format(now);

	    if (scheduledFilename.equals(datedFilename)) {
	      return;
	    }

	    // close current file, and rename it to datedFilename
	    this.closeFile();

	    File target  = new File(scheduledFilename);
	    if (target.exists()) {
	      target.delete();
	    }

	    File file = new File(fileName);
	    boolean result = file.renameTo(target);
	    if (!result) {
	        try {
	            FileInputStream fis = new FileInputStream(file);
	            FileOutputStream fos = new FileOutputStream(target);
	            int n;
	            while ((n = fis.read()) != -1) {
	                fos.write(n);
	            }
	            fis.close();
	            fos.close();

	            result = true;

	        } catch (IOException e) {
	            result = false;
	        }
	    }

	    if(result) {
	      LogLog.debug(fileName +" -> "+ scheduledFilename);
	    } else {
	      LogLog.error("Failed to rename ["+fileName+"] to ["+scheduledFilename+"].");
	    }

	    try {
	      this.setFile(fileName, true, this.bufferedIO, this.bufferSize);
	    }
	    catch(IOException e) {
	      errorHandler.error("setFile("+fileName+", true) call failed.");
	    }
	    scheduledFilename = datedFilename;
	  }
}
