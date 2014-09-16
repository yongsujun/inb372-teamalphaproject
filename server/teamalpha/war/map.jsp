
<%@ page import = "com.teamalpha.datastore.*,java.util.*,com.teamalpha.model.*" %>

<%


//String userid = (String)session.getAttribute("userid");
String userid = "jane@mail.com";



Caretaker caretaker = DatastoreManager.getCaretaker(userid);

String patientID = request.getParameter("patient");
String lng1="",lng2="",lng3="",lng4="";
String lat1="",lat2="",lat3="",lat4="";
Boolean fence = false;
//String patientID = "ahFpbmIzNzItdGVhbS1hbHBoYXIUCxIHUGF0aWVudBiAgICAgICwCAw";
if(patientID.equals("ahFpbmIzNzItdGVhbS1hbHBoYXIUCxIHUGF0aWVudBiAgICAgICwCQw")){
	//No fence
}else if(patientID.equals("ahFpbmIzNzItdGVhbS1hbHBoYXIUCxIHUGF0aWVudBiAgICAgICwCAw")){
	lng1="-27.470506";
	lat1="153.022556";
	
	lng2="-27.478273";
	lat2="153.027534";
	
	lng3="-27.472448";
	lat3="153.030581";
	
	lng4="-27.46845";
	lat4="153.026977";
	
	
	fence = true;
	
}else if(patientID.equals("ahFpbmIzNzItdGVhbS1hbHBoYXIUCxIHUGF0aWVudBiAgICAgICwCgw")){
	
	fence = false;

}


%>


<style>
	#map_canvas { 
    	height: 80%;
        width: 90%;
    }    
</style>
<script src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script src="https://apis.google.com/js/client.js?onload=init"></script>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false&amp;libraries=geometry"></script> 

<script type="text/javascript" src="/js/convex_hull.js"></script> 

<script>

	var map = null;
    var patientMarker = null;
    //-------------------------------------
    var fenceMarker = null;
    var gmarkers = [];
    var points = [];
    var hullPoints = [];    
    var polyline;
    
    var infowindow = new google.maps.InfoWindow(
  		  { 
  		    size: new google.maps.Size(150,50)
  		  });
    //---------------------------------
  
    function initialize() {
            
    	var myLatlng = new google.maps.LatLng( 51.50, -0.12  );
        var myOptions = {
        	zoom: 15,
            center: myLatlng,
            mapTypeControl: true,
            mapTypeControlOptions: {style: google.maps.MapTypeControlStyle.DROPDOWN_MENU},
            navigationControl: true,

            mapTypeId: google.maps.MapTypeId.ROADMAP
        }

        map = new google.maps.Map( document.getElementById( "map_canvas" ), myOptions );
          
        patientMarker = new google.maps.Marker({
            map: map,
            icon: "images/dull-green-circle.png"
        });
            
        setInterval(updatemap, 3500);
        
        
        //check fence exist and setup
        checkFence();
        //-------------------------------------------------------------------------
//        google.maps.event.addListener(map, 'click', function() {
//            infowindow.close();
//            });
     
//      google.maps.event.addListenerOnce(map, 'bounds_changed', function() {
//            // Add 10 markers to the map at random locations
//            var bounds = map.getBounds();
//            var southWest = bounds.getSouthWest();
//            var northEast = bounds.getNorthEast();
//            var lngSpan = northEast.lng() - southWest.lng();
//            var latSpan = northEast.lat() - southWest.lat();
//    	map.setCenter(map.getCenter());
//            map.setZoom(map.getZoom()-1);
//    /* */ 
//           for (var i = 0; i < 10; i++) {
//              var point = new google.maps.LatLng(southWest.lat() + latSpan * Math.random(),
//                                      southWest.lng() + lngSpan * Math.random());
//              points.push(point);
//    	  var marker = createMarker(point, i);
//    	  gmarkers.push(marker);
//            }
//    /* */
//    /*
//            for (var i = 0; i<points.length; i++) {
//    	  var marker = createMarker(points[i], i);
//    	  gmarkers.push(marker);
//              map.addOverlay(marker);
//            }
//    */
//          for (var i=0; i < points.length; i++) {
//            document.getElementById("input_points").innerHTML += i+": "+points[i].toUrlValue()+"<br>";
//          }
//       
//          calculateConvexHull();
//        });
            google.maps.event.addListener(map, "click", function(evt) {
              if (evt.latLng) {
                var latlng = evt.latLng;
               // alert("latlng:"+latlng.toUrlValue());
                var marker = createMarker(latlng, gmarkers.length-1);
                points.push(latlng);
                gmarkers.push(marker);
               
          calculateConvexHull();
    	    
    	    }
          });
  //-----------------------------------------------------------------------------------------
       
    }
    
    function checkFence(){
    	if (<%=fence%> == true){
    		
//    		var p1 = <%=lng1%>+", "+<%=lat1%>;
//    		var p2 = <%=lng2%>+", "+<%=lat2%>;
//    		var p3 = <%=lng3%>+", "+<%=lat3%>;
//    		var p4 = <%=lng4%>+", "+<%=lat4%>;
    		//alert(p4);
    		//fenceMarker = createMarker(<%=lng1%>+", "+<%=lat1%>, -1);
//    		alert(<%=lng1%>+","+<%=lat1%>);
//            points.push(<%=lng1%>+", "+<%=lat1%>);
//            gmarkers.push(marker);
//            calculateConvexHull();
//            marker = createMarker(p2, 0);
//            points.push(p2);
//            gmarkers.push(marker);
//            calculateConvexHull();
//            marker = createMarker(p3, 1);
//            points.push(p3);
//            gmarkers.push(marker);
//            calculateConvexHull();
//            marker = createMarker(p4, 2);
//            points.push(p4);
//            gmarkers.push(marker);
//            
//    		calculateConvexHull();
    	}else{
    		alert("false");
    	}
    	
    	
    }
  //****************************************************************************  
    function removeMarker(latlng) {
        for (var i= 0; i < gmarkers.length;i++) {
          if (google.maps.geometry.spherical.computeDistanceBetween(
                latlng, gmarkers[i].getPosition()) < 0.1) 
          {
 	    gmarkers[i].setMap(null);
             gmarkers.splice(i,1);
          }
        }
       calculateConvexHull();               
 }

 function createMarker(latlng, marker_number) {
	  
     var html = "marker "+marker_number;
     var marker = new google.maps.Marker({
         position: latlng,
         map: map,
         zIndex: Math.round(latlng.lat()*-100000)<<5
         });
      //alert(latlng);
	  //alert(marker_number);
     google.maps.event.addListener(marker, 'click', function() {
         var contentString = "<br>"+marker.getPosition().toUrlValue()+"<br><a href='javascript:removeMarker(new google.maps.LatLng("+marker.getPosition().toUrlValue()+"));'>Remove Marker</a>";
         //var contentString = html + "<br>"+marker.getPosition().toUrlValue()+"<br><a href='javascript:removeMarker(new google.maps.LatLng("+marker.getPosition().toUrlValue()+"));'>Remove Marker</a>";
         infowindow.setContent(contentString); 
         infowindow.open(map,marker);
         });
    
     return marker;
 }
  


      function calculateConvexHull() {
       if (polyline){ 
    	   polyline.setMap(null);
    	   document.getElementById("hull_points").innerHTML = "";
       }
       points = [];
       for (var i=0; i < gmarkers.length; i++) {
         points.push(gmarkers[i].getPosition());
       }
       points.sort(sortPointY);
       points.sort(sortPointX);
       DrawHull();
 }

      function sortPointX(a,b) { return a.lng() - b.lng(); }
      function sortPointY(a,b) { return a.lat() - b.lat(); }

      function DrawHull() {
      hullPoints = [];
      chainHull_2D( points, points.length, hullPoints );
      polyline = new google.maps.Polygon({
       map: map,
       paths:hullPoints, 
       fillColor:"#FF0000",
       strokeWidth:2, 
       fillOpacity:0.5, 
       strokeColor:"#0000FF",
       strokeOpacity:0.5
      });
      displayHullPts();
 }

 function displayHullPts() {
      //document.getElementById("hull_points").innerHTML = "";
      document.getElementById("ta1").innerHTML = "";
      for (var i=0; i < hullPoints.length-1; i++) {
    	  //alert(hullPoints.length);
        document.getElementById("ta1").innerHTML += hullPoints[i].toUrlValue()+"\n";
        //document.getElementById("p"+(i)).value = hullPoints[i].toUrlValue();
      }
 }
//***************************************************************************************
    
    
    function updatemap() {
    	var ROOT = "http://localhost:8888/_ah/api";
    	
    	gapi.client.load("location", "v1", function() {
    		gapi.client.location.get({"id":"<%=patientID%>"}).execute(function(response) {
    			var newPos = new google.maps.LatLng(response.latitude, response.longitude);
    			console.log(newPos);
    			map.panTo(newPos);
    			patientMarker.setPosition(newPos);
    		});
    	}, ROOT);

    }    
    google.maps.event.addDomListener(window, "load", initialize);
    
 
  
 //=================================================================== 


</script>

<jsp:include page="/inc/header.jsp" /> 



<div class="container">

<div class="col-md-6"><div id="map_canvas"></div></div>
<div class="col-md-6">Virture Fence<br>
<div id="hull_points"></div>
<textarea name="ta1" cols="30" rows="6" class="s23" id="ta1" readonly="true" ></textarea>

</div>







</div> <!-- /container -->
    
<jsp:include page="/inc/footer.jsp" /> 