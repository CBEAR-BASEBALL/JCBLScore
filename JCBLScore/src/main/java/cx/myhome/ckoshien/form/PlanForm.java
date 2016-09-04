
package cx.myhome.ckoshien.form;

import java.util.List;

import org.seasar.struts.annotation.Required;

public class PlanForm {
	@Required
	private String id;
	private List<String> plans;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<String> getPlans() {
		return plans;
	}
	public void setPlans(List<String> plans) {
		this.plans = plans;
	}

}
