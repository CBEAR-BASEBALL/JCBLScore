package cx.myhome.ckoshien.interceptor;

import javax.annotation.Resource;

import org.aopalliance.intercept.MethodInvocation;
import org.seasar.struts.annotation.Execute;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

import cx.myhome.ckoshien.dto.LoginUserDto;


public class LoginConfInterceptor extends AbstractInterceptor {

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
			object="/login/";
		}
		return object;
//		return (!isExecuteMethod(invocation) || isLoggedIn()) ? invocation
//				.proceed() : "/login/";
	}
		private boolean isExecuteMethod(MethodInvocation invocation) {
				return invocation.getMethod().isAnnotationPresent(Execute.class);
		}

			/**
			 * セッション上にDtoがあるか、あった場合その中にuserIDは保持されているか。
			 * @return 上記の条件を両方満たしていればtrue
			 */
		private boolean isLoggedIn() {
				return (loginUserDto != null && loginUserDto.getId() != null);
		}




}
