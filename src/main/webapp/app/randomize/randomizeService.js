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
    var url = '';
    return $http.get(url);
  }
  function getMasterMinds(){
    var url = '';
    return $http.get(url);
  }
})();
