app.run(['$rootScope','$state','UserModel',
    function($rootScope,$state,UserModel){
        $rootScope.$on('$stateChangeStart', function(event,toState,toParams,fromState,fromParams) {
        	if (!toState.auth) {    // authプロパティが存在しなければチェックせずに終了
        		return;
            }
        	if(!UserModel.isLogged()){  // ログインしていなければ
        		event.preventDefault(); //遷移を止める
        		$state.go('login');
            }
//        	console.log(toState);
//        	console.log($state);
//        	console.log("$stateChangeStart " + fromState.name + JSON.stringify(fromParams) + " -> " + toState.name + JSON.stringify(toParams));
        })
    }
]);