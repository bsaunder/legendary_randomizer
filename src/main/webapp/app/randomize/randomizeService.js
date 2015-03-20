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
    .controller('randomizeService', randomizeService);

    randomizeService.$inject=['$http'];
function randomizeService($http) {
  var service = {
    getSchemes: getSchemes,
    getMasterMinds: getMasterMinds
  };
  return service;

  function getSchemes(){
    var url = 'http://legendary-bsaunders.rhcloud.com/rest/scheme';
    return $http.get(url);
  }
  function getMasterMinds(){
    var url = 'http://legendary-bsaunders.rhcloud.com/rest/mastermind';
    return $http.get(url);
  }
})();
