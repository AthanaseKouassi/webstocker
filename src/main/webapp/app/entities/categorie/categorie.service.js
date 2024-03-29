(function() {
    'use strict';
    angular
        .module('webstockerApp')
        .factory('Categorie', Categorie);

    Categorie.$inject = ['$resource'];

    function Categorie ($resource) {
        var resourceUrl =  'api/categories/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    data = angular.fromJson(data);
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
