(function(){
angular
    .module('app')
    .service('randomizeService', randomizeService);

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
}})();
