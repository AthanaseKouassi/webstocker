'use strict';

describe('Controller Tests', function() {

    describe('Ville Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockVille, MockRegion;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockVille = jasmine.createSpy('MockVille');
            MockRegion = jasmine.createSpy('MockRegion');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity ,
                'Ville': MockVille,
                'Region': MockRegion
            };
            createController = function() {
                $injector.get('$controller')("VilleDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'webstockerApp:villeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
