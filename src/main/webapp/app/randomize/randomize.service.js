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
(function(){
angular
    .module('app')
    .service('randomizeService', randomizeService);

    randomizeService.$inject=['$http'];
function randomizeService($http) {
  var service = {
    getSchemes: getSchemes,
    getMasterminds : getMasterminds,
    getSetup: getSetup
  };
  return service;

  function getSchemes(){
    var url = 'http://bts-fsw.airdns.org:30454/rest/scheme';
    return $http.get(url);
  }
  function getMasterminds(){
    var url = 'http://bts-fsw.airdns.org:30454/rest/mastermind';
    return $http.get(url);
  }
  function getSetup(setup){
    var url = 'http://bts-fsw.airdns.org:30454/rest/setup/{0}?scheme={1}&mastermind={2}';
    
    url = url.replace('{0}', setup.numOfPlayers);
    
    if(setup.selectedScheme){
    	url = url.replace('{1}', setup.selectedScheme.id);
    }else{
    	url = url.replace('{1}', -1);
    }
    
    if(setup.selectedMastermind){
    	url = url.replace('{2}', setup.selectedMastermind.id);
    }else{
    	url = url.replace('{2}', -1);
    }
    
    return $http.get(url);
  }
}})();
