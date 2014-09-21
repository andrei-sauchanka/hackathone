var passAPackApp = angular.module ('passAPackApp', ['ngRoute', 'ngSanitize'])
	.config (function ($routeProvider, $httpProvider) {
		$routeProvider.
			when ('/search-trips', {
				controller: searchTripsController,
				templateUrl: 'search-trips.html'
			}).
			when ('/search-trips/from/:from/to/:to', {
				controller: searchTripsController,
				templateUrl: 'search-trips.html'
			}).
			when ('/my-trips', {
				controller: myTripsController,
				templateUrl: 'my-trips.html'
			}).
			otherwise ({
				redirectTo: '/search-trips'
			});

		$httpProvider.defaults.useXDomain = true;
	});