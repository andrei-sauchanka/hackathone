passAPackApp.service ('getCityService', function ($http) {
	this.getCity = function (_value) {
		return $http ({
			method: 'GET',
			url: SERVICE_GET_CITY_URL + '?filter=' + _value,
			headers: {
				'Content-Type': 'application/json; charset=utf-8'
			}
		});
	}
});