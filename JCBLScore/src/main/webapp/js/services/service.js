//angular.module('myApp',[])
app.service('UserModel', ['$http','$state',
                          function($http,$state){
	var self = this;
	var _data = null;
	this.setUser = function(data){
		_data = data;
	};
	this.isLogged = function(){
		return !!_data;
	};
	this.login=function(loginId,password,token){
		$http({
			method: 'POST',
		    url: '/JCBLScore/api/v1/login/',
		    data:{
		    	loginId: loginId,
		    	password: password,
		    	token:token
		    }
		})
		.success(function(data, status, headers, config) {
			self.setUser(data);
			$state.go('/');
		})
		 .error(function(data, status, headers, config,listSize) {
			 if(status==400){
				 alert('ログイン失敗');
			 }else if(status==404){
				 alert('通信エラー');
			 }
		 })

	}
}]);