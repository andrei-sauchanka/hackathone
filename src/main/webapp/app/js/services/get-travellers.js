passAPackApp.service ('searchTravellerService', function ($http) {
	this.getTravellers = function (_from, _to) {
		return $http ({
			method: 'GET',
			url: SERVICE_GET_TRAVELER_URL + '?from=' + _from + '&to=' + _to,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		});
	}
});