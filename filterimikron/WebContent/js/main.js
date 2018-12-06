var map = null;
var directionsDisplay = new google.maps.DirectionsRenderer();
var directionsService = new google.maps.DirectionsService();
var mapCenter = new google.maps.LatLng(44.626802, 20.370151);

$(document).ready(function() {

  $(".sidebar_container").load("sidebar.html");
//  $("#content").load("home.html");
//
//  $("#nav li").click(function(e) {
//    e.preventDefault();
//    var href = $(this).find('a').attr('href');
//    $(".current").removeClass("current");
//    $(this).addClass("current");
//    $("#content").load(href, function() {
//      if (href.indexOf("contact") >= 0) {
//        loadMap();
//      }
//    });
//
//  });

});

function loadMap() {
  var googleMapOptions = {
      center : mapCenter, // map center
      zoom : 12, // zoom level, 0 = earth view to higher value
      panControl : true, // enable pan Control
      zoomControl : true, // enable zoom control
      zoomControlOptions : {
        style : google.maps.ZoomControlStyle.SMALL
      // zoom control size
      },
      scaleControl : true, // enable scale control
      mapTypeId : google.maps.MapTypeId.ROADMAP
  // google map type
  };
  map = new google.maps.Map(document.getElementById("map_canvas"), googleMapOptions);

  var marker = new google.maps.Marker({
      position : new google.maps.LatLng(44.626802, 20.370151),
      map : map,
      draggable : false,
      animation : google.maps.Animation.DROP,
      title : "filteri mikron"
  });

}

function getDirections() {
  navigator.geolocation.getCurrentPosition(GetLocation);
  $("#directionLoader").width($('#directionLoader').parent().width());
  $("#directionLoader").height($('#directionLoader').parent().height());
  $("#directionLoader").show();
  directionsDisplay.setMap(map);
}

function GetLocation(location) {

  var start = new google.maps.LatLng(location.coords.latitude, location.coords.longitude);
  var request = {
      origin : start,
      destination : mapCenter,
      travelMode : google.maps.TravelMode.DRIVING
  };
  directionsService.route(request, function(response, status) {
    if (status == google.maps.DirectionsStatus.OK) {
      directionsDisplay.setDirections(response);
    }
  });
  $("#directionLoader").hide();
}
