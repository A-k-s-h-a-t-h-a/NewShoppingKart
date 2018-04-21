<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>

<style>
.video-wrapper {
  background: #000 none repeat scroll 0 0;
  overflow: hidden;
  width: 100%;
}

.small-video-row {
  padding: 60px 0px;
}

.slick-slide {
  transition: ease-in-out all .15s ease;
  transform: scale(1, 1);
  background-color: rgba(0, 0, 0, 0.75);
  position: relative;
}

.slick-current {
  transform: scale(1.25, 1.25);
  transition: ease-in-out all .15s ease;
  z-index: 2;
  overflow:hidden;
}

.slick-current:hover {
  cursor: pointer;
}

.video-overlay {
  background-color: rgba(0, 0, 0, 0.75);
  height: 100%;
  position: absolute;
  width: 100%;
}

.slick-current .video-overlay {
  background-color: rgba(0, 0, 0, 0);
}

.small-video-text {color:#fff;}


.slick-slide img {
    display: block;
    width: 100%;
}

/************** Custom overflow ********/

.slick-list {
    overflow: visible; 
}
</style>
</head>

<body>
	<div class="video-wrapper">
		<div class="center   small-video-row">
		
			<div>
	      		<div class="video-overlay"> </</div>
		      	<img src="http://yourshot.nationalgeographic.com/u/fQYSUbVfts-T7pS2VP2wnKyN8wxywmXtY0-FwsgxpCmdjxQkHT2R4ZKvfpvX6LuaSAOx1M7zmssq38qcSTRYoZ86ivjBf8r-rzQWfpyZTsOoYQpB-0skhim5hOpw-Wn0oLYk7f-aBot-OvzY3DMhfhC5p2RdCM_B_dR8oHY2skO24uKDrWWgmqHR7pzSUmRQ0C79_MfYJOaXxab4cubd/">
		        <div class="small-video-text">
		        The wonder of life beneath the waves in videos
		        </div>
		    </div>
		    
		    <div>
		      <div class="video-overlay"> </div>
		      <img src="http://i1.wp.com/thenewcamera.com/wp-content/uploads/2015/11/Fujifilm-X-Pro-2-coming-ima.jpg">
		      <div class="small-video-text">
		        The wonder of life beneath the waves in videos
		      </div>
		    </div>
	
		    <div>
		      <div class="video-overlay"> </div>
		      <img src="http://www.tatnews.org/wp-content/uploads/2015/03/Nat-Geo-The-Beach_03.jpg">
		      <div class="small-video-text">
		        The wonder of life beneath the waves in videos
		      </div>
	    	</div>
	
		    <div>
		      <div class="video-overlay"> </div>
		      <img src="http://www.sparticl.org/assets/uploads/images/resource-images/24296-cropped.png">
		      <div class="small-video-text">
		        The wonder of life beneath the waves in videos
		      </div>
			</div>
		    <div>
		      <div class="video-overlay"> </div>
		      <img src="http://petapixel.com/assets/uploads/2015/03/thirds.jpg">
		      <div class="small-video-text">
		        The wonder of life beneath the waves in videos
		  	  </div>
	    	</div>
	    	
  		</div>
	</div>

<script>
$('.center').slick({
	  centerMode: true,
	  // cssEase: 'cubic-bezier(0.950, 0.050, 0.795, 0.035)',
	  easing: 'easeOutElastic',
	  focusOnSelect: true,
	  centerPadding: '60px',
	  slidesToShow: 3,
	  responsive: [{
	    breakpoint: 768,
	    settings: {
	      arrows: false,
	      centerMode: true,
	      centerPadding: '40px',
	      slidesToShow: 3
	    }
	  }, {
	    breakpoint: 480,
	    settings: {
	      arrows: false,
	      centerMode: true,
	      centerPadding: '40px',
	      slidesToShow: 1
	    }
	  }]
	});
</script>

</body>
</html>
