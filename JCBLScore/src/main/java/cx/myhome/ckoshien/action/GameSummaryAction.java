package cx.myhome.ckoshien.action;

import org.seasar.struts.annotation.Execute;

public class GameSummaryAction {



	@Execute(validator = false)
	public String edit(){
		return "edit.jsp";
	}

}
