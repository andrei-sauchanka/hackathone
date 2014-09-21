function myTripsController ($scope, $http, $location, $routeParams, searchTripsService, getCityService) {
	/* ======================================================== */
	/* properties */
	/* ======================================================== */
	$scope.id = $routeParams.id;

	/* ======================================================== */
	/* methods */
	/* ======================================================== */
	$scope.init = function init () {
		$scope.getMyTrips();
	};
	
	$scope.getMyTrips = function () {
		searchTripsService.getMyTrips().then(
			function (_data) {
				$scope.trips = _data.data;
				$scope.hasTrips = (_data.data.length != 0);
 				$scope.$broadcast ('dataloaded');
			},
			function (_data, _status) {
				$scope.trips = null;
				alert ('ERROR: error code ' + _status);
			}
		);
	}
	
	/* ======================================================== */
	/* events */
	/* ======================================================== */
	$scope.onRegisterButtonClick = function (_event) {
		alert ('Not implemented yet!');
	}
	
	$scope.onHideButtonClick = function (_trip) {
		_trip.hidden = true;
	}
	
	$scope.onShowButtonClick = function (_trip) {
		_trip.hidden = false;
	}

	/* ======================================================== */
	/* init */
	/* ======================================================== */
	$scope.init ();
}