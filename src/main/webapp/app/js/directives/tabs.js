passAPackApp.directive ('tabsWidget', ['$location', function ($location) {
	var tabsContainer = null;
	var tabs = null;
	var urls = [];
	
	var onTabClick = function (_event, _scope) {
		var tab = $(_event.target)
		var index = tab.index();
		
		tabs.removeClass('active');
		tab.addClass('active');

		_scope.$apply(function () {
			$location.path(urls[index]);
		});
	}

	var linkFn = function(_scope, _element, _attributes) {
		urls = $(_element).attr('tabs-urls');
		
		if (typeof urls == 'undefined') {
			return;
		}
		urls = urls.split(',');
		
		tabsContainer = $(_element.children()[0]);
		tabs = tabsContainer.find('LI');
		tabs.click(function (_event) {
			onTabClick (_event, _scope);
		});
	}

	return {
		restrict: 'E',
		link: linkFn
	}
}]);