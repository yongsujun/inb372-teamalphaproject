function updateLocation() {
     LocationService.updateLocation();
    $("#main-page-footer-location").text(LocationService.getLatitude() + " " + LocationService.getLongitude());
}

function changePage(page) {
    $(":mobile-pagecontainer").pagecontainer("change", "#" + page, { reverse: false});     
}

$(document).ready(function() {
    

    window.setInterval(updateLocation, 2000);

    if (!AppSettings.hasUserID()) {
        changePage("enter-id");
    } else {
        changePage("main-page");
    }
    
    
});

$( document ).ready( function() {
    
   // $( "#popupMap iframe" )
   //     .attr( "width", 0 )
    //    .attr( "height", 0 );
		  
  ///  $( "#popupMap iframe" ).contents().find( "#map_canvas" )
  ///      .css( { "width" : 0, "height" : 0 } );
	 	     
 /*   $( "#popupMap" ).on({
        popupbeforeposition: function() {
        //    var size = scale( 480, 320, 0, 1 ),
       //         w = size.width,
         //       h = size.height;

          //  $( "#popupMap iframe" )
         //       .attr( "width", w - 100 )
          //      .attr( "height", h + 200 );
					 
         //   $( "#popupMap iframe" ).contents().find( "#map_canvas" )
          //      .css( { "width": w - 100, "height" : h + 200} );
        },
        popupafterclose: function() {
            $( "#popupMap iframe" )
                .attr( "width", 0 )
                .attr( "height", 0 );
					 
            $( "#popupMap iframe" ).contents().find( "#map_canvas" )
                .css( { "width": 0, "height" : 0 } );
        }
    }); */
});