/*
 * #%L
 * Legendary Card Randomizer
 * %%
 * Copyright (C) 2015 Bryan Saunders
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-3.0.html>.
 * #L%
 */
(function() {
    'use strict';

    angular
        .module('app')
        .controller('RandomizeController', RandomizeController);

    RandomizeController.$inject = ['randomizeService'];

    function RandomizeController(randomizeService) {
        /*jshint validthis: true */
        var vm = this;
        vm.title = 'Randomize';

        vm.getSetup = getSetup;
        activate();

        function activate() {
          vm.setup = null;
          vm.setupOptions = {};
          vm.setupOptions.numOfPlayers = null;
          vm.setupOptions.selectedMastermind = null;
          vm.setupOptions.selectedScheme = null;

          vm.schemes={};
          vm.masterminds={};

          randomizeService.getSchemes()
          .then(getSchemesSuccess, getSchemesError);

          randomizeService.getMasterminds()
          .then(getMastermindSuccess, getMastermindError);
        }

        function getSchemesSuccess(result){
          vm.schemes = result.data;
        }
        function getSchemesError(result){
          alert('Errors');
        }

        function getMastermindSuccess(result){
          vm.masterminds = result.data;
        }
        function getMastermindError(result){
          alert('Errors');
        }
        function getSetup(isValid){
          if(isValid)
          {
            randomizeService.getSetup(vm.setupOptions)
            .then(getSetupSuccess, getSetupError);
          }
          else
          {
            alert("Invalid");
          }
        }
        function getSetupSuccess(result){
          vm.setup = result.data;
        }
        function getSetupError(result){
          alert("Error getting setup");
        }
    }
})();
