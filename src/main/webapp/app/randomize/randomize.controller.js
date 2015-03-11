(function() {
    'use strict';

    angular
        .module('app')
        .controller('RandomizeController', RandomizeController);

    function RandomizeController() {
        /*jshint validthis: true */
        var vm = this;
        vm.title = 'Randomize';

        activate();

        function activate() {

        }

    }
})();
