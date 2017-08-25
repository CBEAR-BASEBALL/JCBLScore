package cx.myhome.ckoshien.dto;

import java.io.Serializable;

import org.seasar.framework.container.annotation.tiger.Component;
import org.seasar.framework.container.annotation.tiger.InstanceType;

@Component(instance = InstanceType.SESSION)
public class LoginUserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	private String id;
	//private String email;
	//private String password;
	private String name;
	private String authority;
	private String token;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}


}
