passAPackApp.service ('searchTripsService', function ($http) {
	this.getTrips = function (_from, _to) {
		return $http ({
			method: 'GET',
			url: SERVICE_GET_TRIPS_URL + '?from=' + _from.latitude + ',' + _from.longitude + '&to=' + _to.latitude + ',' + _to.longitude,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		});
	}
	
	this.getMyTrips = function () {
		return $http ({
			method: 'GET',
			url: SERVICE_GET_TRIPS_LOCAL_URL,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		});
	}
});