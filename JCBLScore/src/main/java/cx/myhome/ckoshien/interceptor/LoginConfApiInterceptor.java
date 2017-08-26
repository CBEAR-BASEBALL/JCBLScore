package cx.myhome.ckoshien.interceptor;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.struts.annotation.Execute;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;
import org.seasar.framework.container.SingletonS2Container;

import cx.myhome.ckoshien.dto.LoginUserDto;


public class LoginConfApiInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 1L;

	@Resource
	protected LoginUserDto loginUserDto;

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// 両方の条件を満たしていない場合、Loginページへ飛ばす。
		Object object;
		if(!isExecuteMethod(invocation) || isLoggedIn()){
			object=invocation.proceed();
		}else{
			HttpServletResponse response = SingletonS2Container.getComponent(HttpServletResponse.class);
			response.sendError(401);
			return null;
		}
		return object;

	}
		private boolean isExecuteMethod(MethodInvocation invocation) {
				return invocation.getMethod().isAnnotationPresent(Execute.class);
		}

			/**
			 * セッション上にDtoがあるか、あった場合その中にuserIDは保持されているか。
			 * @return 上記の条件を両方満たしていればtrue
			 */
		private boolean isLoggedIn() {
			Map<String, Object> sessionScope = SingletonS2Container.getComponent("sessionScope");
			LoginUserDto loginUserDto = (LoginUserDto) sessionScope.get("loginUserDto");
				return (loginUserDto != null && loginUserDto.getId() != null);
		}




}
