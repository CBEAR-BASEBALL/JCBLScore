package cx.myhome.ckoshien.action.api.v1;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.seasar.struts.annotation.ActionForm;
import org.seasar.struts.annotation.Execute;
import org.seasar.struts.util.ResponseUtil;

import cx.myhome.ckoshien.dto.PlayerDto;
import cx.myhome.ckoshien.dto.api.AutoCompleteDto;
import cx.myhome.ckoshien.entity.Player;
import cx.myhome.ckoshien.form.PlayerForm;
import cx.myhome.ckoshien.service.PlayerService;
import net.arnx.jsonic.JSON;

public class PlayerAction {
	@Resource
	protected PlayerService playerService;

	@Resource
	@ActionForm
	protected PlayerForm playerForm;

	@Execute(validator=false)
	public String search(){
		List<PlayerDto> playerList=new ArrayList<PlayerDto>();
		List<AutoCompleteDto> acDtos=new ArrayList<AutoCompleteDto>();
		if(null!=playerForm.name){
			playerList = playerService.findByLikeName(playerForm.name);
			for(int i=0;i<playerList.size();i++){
				AutoCompleteDto acDto=new AutoCompleteDto();
				acDto.value=playerList.get(i).name+"("+playerList.get(i).teamName+")";
				acDto.label=playerList.get(i).name+"("+playerList.get(i).teamName+")";
				acDto.id=playerList.get(i).id;
				acDtos.add(acDto);
			}
		}
		ResponseUtil.write(JSON.encode(acDtos));
		return null;
	}

}
