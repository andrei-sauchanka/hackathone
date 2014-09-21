function searchTripsController ($scope, $http, $location, $routeParams, searchTripsService, getCityService) {
	/* ======================================================== */
	/* properties */
	/* ======================================================== */
	var from = $routeParams.from;
	var to = $routeParams.to;

	if (typeof from == 'undefined') {
		$scope.from = {};
	}
	else {
		from = from.split(',');
		$scope.from = {
			city:from[0],
			latitude:from[1],
			longitude:from[2]
		}
	}

	if (typeof to == 'undefined') {
		$scope.to = {};
	}
	else {
		to = to.split(',');
		$scope.to = {
			city:to[0],
			latitude:to[1],
			longitude:to[2]
		}
	}

	/* elements */
	$scope.citiesContainer;

	/* ======================================================== */
	/* methods */
	/* ======================================================== */
	$scope.init = function init () {
		$scope.citiesContainer = $('#citiesContainer');

		if (typeof $scope.from.city == 'undefined' || typeof $scope.to.city == 'undefined') {
			return;
		}

		$scope.getTrips($scope.from, $scope.to);
	};

	$scope.getTrips = function (_from, _to) {
		searchTripsService.getTrips (_from, _to).then (
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
	};
	
	$scope.getCities = function (_value, _event) {
		getCityService.getCity (_value).then (
			function (_data) {
				var target = $(_event.target);

				$scope.cities = _data.data;
				$scope.hasCities = (_data.data.length != 0);
				if (_data.data.length > 10) {
					$scope.citiesContainer.css({height: '300px'});
				}
				else {
					$scope.citiesContainer.css({height: 'auto'});
				}
 				$scope.$broadcast ('dataloaded');

				$scope.citiesContainer.css ({
					left: target.offset().left,
					top: target.offset().top + target.height() + 15
				});
			},
			function (_data, _status) {
				$scope.travellers = null;
				alert ('ERROR: error code ' + _status);
			}
		);
	};

	/* ======================================================== */
	/* events */
	/* ======================================================== */
	$scope.onSearchButtonClick = function (_event) {
		if (typeof $scope.from.city == 'undefined' || typeof $scope.to.city == 'undefined') {
			return;
		}
		$location.path('/search-trips/from/' + $scope.from.city + ',' + $scope.from.latitude + ',' + $scope.from.longitude + '/to/' + $scope.to.city + ',' + $scope.to.latitude + ',' + $scope.to.longitude);
	}

	$scope.onFromFieldKeyUp = function (_event) {
		$scope.input = $(_event.target);
		if (_event.target.value == '') {
			$scope.hasCities = false;
			return;
		}
		$scope.getCities (_event.target.value, _event);
	}

	$scope.onToFieldKeyUp = function (_event) {
		$scope.input = $(_event.target);
		if (_event.target.value == '') {
			$scope.hasCities = false;
			return;
		}
		$scope.getCities (_event.target.value, _event);
	}

	$scope.onCityItemClick = function (_city) {
		$scope.input.val(_city.city);

		var id = $scope.input.attr('id');

		if (id == 'inptFrom') {
			$scope.from = _city;
		}
		else if (id == 'inptTo') {
			$scope.to = _city;
		}
		$scope.hasCities = false;
	}

	/* ======================================================== */
	/* init */
	/* ======================================================== */
	$scope.init ();
}