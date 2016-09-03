package cx.myhome.ckoshien.form;

import org.seasar.struts.annotation.Required;

public class ScheduleForm {
	@Required
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

}
