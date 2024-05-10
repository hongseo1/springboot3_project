(g=>{var h,a,k,p="The Google Maps JavaScript API",c="google",l="importLibrary",q="__ib__",m=document,b=window;b=b[c]||(b[c]={});var d=b.maps||(b.maps={}),r=new Set,e=new URLSearchParams,u=()=>h||(h=new Promise(async(f,n)=>{await (a=m.createElement("script"));e.set("libraries",[...r]+"");for(k in g)e.set(k.replace(/[A-Z]/g,t=>"_"+t[0].toLowerCase()),g[k]);e.set("callback",c+".maps."+q);a.src=`https://maps.${c}apis.com/maps/api/js?`+e;d[q]=f;a.onerror=()=>h=n(Error(p+" could not load."));a.nonce=m.querySelector("script[nonce]")?.nonce||"";m.head.append(a)}));d[l]?console.warn(p+" only loads once. Ignoring:",g):d[l]=(f,...n)=>r.add(f)&&u().then(()=>d[l](f,...n))})({
key: "AIzaSyCcwKDQM2Pzfg_BmsBB3l1eqwnDy7vkCzA", v: "beta"
// Add other bootstrap parameters as needed, using camel case.
// Use the 'v' parameter to indicate the version to load (alpha, beta, weekly, etc.)
});

// 지도 시작
let map, infoWindow;
let geocoder;
let responseDiv;
let response;
async function initMap() {
	// 최초의 내 위치
	const position = { lat: 37.39873, lng: 126.9208 };
	// Request needed libraries.
	//@ts-ignore
	const { Map } = await google.maps.importLibrary("maps");
	const { AdvancedMarkerView } = await google.maps.importLibrary("marker");

	// 맵 설정
	map = new Map(document.getElementById("map"), {
		zoom: 16,
		center: position,
		mapId: "honbab",
		//맵 안의 ui컨트롤
		disableDefaultUI: false, //기본 ui전체 컨트롤
		zoomControl: false, //축적
		mapTypeControl: false, //왼쪽상단 맵
		scaleControl: false, //화면 확대 축소
		streetViewControl: false, //위성사진
		rotateControl: false,
		fullscreenControl: true, //전체화면

	});

	const iconBase ="./images/";
	const icons = {
		default_icon: {
		  icon: iconBase + "marker_icon.png",
		},
		store: {
		  icon: iconBase + "store_icon_x0.66.png",
		},
		/*info: {
		  icon: iconBase + "info-i_maps.png",
		}, */
	};
  
	const features = [
		{//처음 마커위치
		  position: new google.maps.LatLng(37.39873, 126.9208), //마커 위치 변경
		  type: "default_icon", //마커 아이콘
		},
		{
		  position: new google.maps.LatLng(37.39873, 126.9208),
		  type: "store",
		}
	];

	// 마커
	const marker = new google.maps.Marker({
		map: map,
		position: features[0].position,
		title: "my_location",
		icon: icons[features[0].type].icon,
	});
	var markers_conv = [];
  
	infoWindow = new google.maps.InfoWindow();

	const locationButton = document.createElement("button");

	locationButton.classList.add("custom-map-control-button");
										//'내 위치'버튼위치
	map.controls[google.maps.ControlPosition.RIGHT_BOTTOM].push(locationButton);
	
	//검색창 연동
	var submitButton = document.getElementById('submit');
	var clearButton = document.getElementById('clear');
	var inputText = document.getElementById('input_location');
	var con_btn = document.getElementById('convenience');
	var conveniences_location = [];
	var default_text = inputText.value;
	var is_located = false;
	var conv_is_located = false;
	var my_location_marker = [];
	var tg_conv;
	var conv_data = [];
	
	
	geocoder = new google.maps.Geocoder();
	
	(() => {
		var url_conv;
		var current_page_num = 1;
		//총 909개
		for(current_page_num = 1; current_page_num<=8; current_page_num++){
			url_conv = "https://safemap.go.kr/openApiService/data/getConvenienceStoreData.do?pageNo="+current_page_num+"&numOfRows=49&Type=&Fclty_Cd=509010&serviceKey=NM8F16J2-NM8F-NM8F-NM8F-NM8F16J2FP&dataType=JSON";
			
			$.ajax({
				url: url_conv,
				type: 'get',
				dataType: 'json',
				async: true,
				error: function(){
					console.log('데이터 불러오기 실패');
				},
				success: function (data){
					for(var i in data.response.body.items){
						conveniences_location.push(data.response.body.items[i].ADRES);
						
						var local_address = data.response.body.items[i].ADRES;
						var local_address_parts = local_address.split(" ");
						var local_address_num = local_address_parts[local_address_parts.length-1];
						var conv_place = data.response.body.items[i].FCLTY_NM;
						
						local_address = local_address.replace(local_address_num,"");
					}
					
				}
			});
		}
		$.ajax({
				url: url_conv,
				type: 'get',
				dataType: 'json',
				async: true,
				error: function(){
					console.log('데이터 불러오기 실패');
				},
				success: function (data){
					for(var i in data.response.body.items){
						conveniences_location.push(data.response.body.items[i].ADRES);
						
						var local_address = data.response.body.items[i].ADRES;
						var local_address_parts = local_address.split(" ");
						var local_address_num = local_address_parts[local_address_parts.length-1];
						var conv_place = data.response.body.items[i].FCLTY_NM;
						
						local_address = local_address.replace(local_address_num,"");
					}
					locate_conv()
				}
			});
	})();
	
	function locate_conv(){
		if(conv_is_located){
			conv_is_located = !conv_is_located
			for (var i in conveniences_location) {
				markers_conv[i].setMap(null);	
			}
			
			return
		}
		console.log(conveniences_location.length);
		conv_is_located = !conv_is_located
		
		for(var i in conveniences_location){
			markers_conv[i] = new google.maps.Marker({
				map: map,
				title: "store",
				icon: icons[features[1].type].icon,
			})
			geocode_replace({ address: conveniences_location[i]},i)
		}
	}
	
	map.addListener("click", (e) => {
		geocode({ location: e.latLng });
	});
	submitButton.addEventListener("click", () =>
		geocode({ address: inputText.value }),
	);
	
	con_btn.addEventListener("click", () =>{
		
		//편의점 아이콘 위치시키기
		locate_conv();
	});
	
	
	
	function clear(clear_all) {
		//모든 마커삭제
		if(clear_all)
		marker.setMap(null);
	
		//내 위치 마커 삭제
		for (var i in my_location_marker) {
			my_location_marker[i].setMap(null);	
		}
		my_location_marker = [];
		
		//버튼 off로 초기화
		if(locationButton.classList.contains('on'))
		locationButton.classList.remove('on');
	}

	function geocode(request) {
		clear(true);
		geocoder
		.geocode(request)
		.then((result) => {
			const { results } = result;

			map.setCenter(results[0].geometry.location);
			marker.setPosition(results[0].geometry.location);
			marker.setMap(map);
			return results;
		})
		.catch((e) => {
			if((inputText.value == default_text && inputText.classList.contains('guide'))||inputText.value == ""||inputText.value == null || inputText.value == undefined){ 
				alert("내용을 입력해주세요")
			}else{
				alert("존재하지 않는 주소입니다.")
			}
			return
		});
	}
	
	function geocode_replace(request,index) {
		
		geocoder
		.geocode(request)
		.then((result) => {
			const { results } = result;
			markers_conv[index].setPosition(results[0].geometry.location);
			markers_conv[index].setMap(map);
			return results;
		})
		.catch((e) => {
			return
		});
	}

	locationButton.addEventListener("click", 
	() => {
		// Try HTML5 geolocation.
		//화면 정지
		document.getElementById('block').style.display ="block";
		if (navigator.geolocation) {
			clear(true)
			if(is_located){
				//위치 지정 필요
				is_located = false
				document.getElementById('block').style.display ="none";
				return
			}
			navigator.geolocation.getCurrentPosition(
				(position) => {
					//내 위치
					const pos = {
						lat: position.coords.latitude,
						lng: position.coords.longitude,
					};
					
					const marker = new google.maps.Marker({
						map: map,
						position: pos,
						title: "my_location",
						icon: icons[features[0].type].icon,
					});
						map.setCenter(pos);
						my_location_marker.push(marker);
						document.getElementById('block').style.display ="none";
				},
				() => {	
					handleLocationError(true, infoWindow, map.getCenter());
					document.getElementById('block').style.display ="none";
				}
			);
			//버튼 아이콘 on으로 변경
			locationButton.classList.add('on');
			//위치 지정 완료
			is_located = true;
		} else {
			stop_all = true;
			// Browser doesn't support Geolocation
			handleLocationError(false, infoWindow, map.getCenter());
		}
		

	}); 
} 



function handleLocationError(browserHasGeolocation, infoWindow, pos) {
	infoWindow.setPosition(pos);
	/* infoWindow.setContent(
		browserHasGeolocation
		? "주소를 불러오는데 실패했습니다."
		: "Error: Your browser doesn't support geolocation."
	);  
	infoWindow.open(map);
	초기 주소 불러오기 실패시 동작*/
	if(browserHasGeolocation){
		alert("주소를 불러오는데 실패했습니다.");
	}else{
		alert("Error: Your browser doesn't support geolocation.");
	}
	
	
}


const eqfeed_callback = function (results) {
  for (let i = 0; i < results.features.length; i++) {
    const coords = results.features[i].geometry.coordinates;
    const latLng = new google.maps.LatLng(coords[1], coords[0]);

    new google.maps.Marker({
      position: latLng,
      map: map,
    });
  }
};


initMap();
window.eqfeed_callback = eqfeed_callback;





