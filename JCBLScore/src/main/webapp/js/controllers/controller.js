app
.controller('loginCtrl',['$scope','$stateParams','$rootScope','$state','$http','UserModel',
                         function($scope,$stateParams,$rootScope,$state,$http,UserModel) {

	$http({
		method: 'GET',
	    url: '/JCBLScore/api/v1/login/auth'
	})
	.success(function(data, status, headers, config) {
		$scope.items = data;
	})
	$scope.onclick=function(){
		UserModel.login($scope.loginId,
						$scope.password,
						$scope.items.token)
	}
}])
.controller('resultCtrl', function($scope, $stateParams,$http,$rootScope,blockUI) {
	blockUI.start();
	$scope.id = $stateParams.id;
	$http({
		method: 'GET',
        url: '/JCBLScore/api/v1/result/season/'+$scope.id,
	})
	.success(function(data, status, headers, config,listSize) {
		$scope.items = data;
		listSize=$scope.items.listSize;
		$rootScope.range = function(listSize) {
			var arr = [];
			for (var i=0; i<listSize; ++i) arr.push(i);
			return arr;
		};
		for(var i=0;i<$scope.items.resultList.length;i++){
			$scope.items.resultList[i]['opponentList']=new Array();
			for(var j=0;j<$scope.items.opponentList.length;j++){
				if($scope.items.resultList[i].teamId==$scope.items.opponentList[j].teamId){
					$scope.items.resultList[i]['opponentList'].push($scope.items.opponentList[j]);
				}
			}
		}
		blockUI.stop();
    })
     .error(function(data, status, headers, config){
    	alert("通信エラー");
    });
 })
 .controller('teamCreate', ['$scope','$stateParams','$rootScope','$state','$http',
                            function($scope,$stateParams,$rootScope,$state,$http) {
	 $http({
			method: 'GET',
	        url: '/JCBLScore/api/v1/team/create'
	 })
	 .success(function(data, status, headers, config,listSize) {

	 })
	 .error(function(data, status, headers, config,listSize) {
		 if(status==401){
			 $state.go('login');
		 }

	 })
 }])