(function(angular){
//angular.module('myApp', ['ui.router','tableSort','angular-loading-bar', 'ngAnimate'])
	app
	// Routes
	.config(function($stateProvider){
		$stateProvider.state('/',{
			url: '/',
			templateUrl: 'index.html'
		})
	})
	.config(function($stateProvider){
		$stateProvider.state('result',{
			url: '/result/season/:id',
			templateUrl: 'views/stats.html',
			controller: 'resultCtrl',
			params:{
				param: null
			}
		})
	})
	.config(function($stateProvider){
		$stateProvider.state('login',{
			url: '/login',
			templateUrl: 'views/login.html',
			controller: 'loginCtrl',
			params:{
				param: null
			}
		})
	})
	.config(function($stateProvider){
		$stateProvider.state('teamCreate',{
			url: '/team/create',
			templateUrl: 'views/teamCreate.html',
			controller: 'teamCreate',
			auth: true,
			params:{
				param: null
			}
		})
	})
	// Default
	.config(function($urlRouterProvider){
		$urlRouterProvider.when('', '/')
	})
})(angular);