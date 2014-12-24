var links={
	district:{
		map:{
			impact:"NEDv_districts3",
			evolution:"NEDv_districts4",
			sensitivity:"NEDv_districts6"
		},
		graph:{
			impact:"NEDv_sub_district",
			evolution:"NEDv_dist_quar4",
			sensitivity:"NEDv_dist_sens"		
		}
	},
	area:{
		map:{
			impact:"NEDv_neighs",
			evolution:"NEDv_neighs_ev",
			sensitivity:"NEDv_neighs2"			
		},
		graph:{
			impact:"NEDv_area_imp",
			evolution:"NEDv_area_diff",
			sensitivity:"NEDv_neighs3"			
		}
	},
	green:{
		map:{
			impact:"NEDv_map_green2",
			evolution:"NEDv_green_evo",
			sensitivity:"error_sens"			
		},
		graph:{
			impact:"error_green",
			evolution:"error_green",
			sensitivity:"error_sens"			
		}	
	}
}

var zone="district";
var chart="map";
var kpi="impact";

function updateBigFrame(event){
	// TODO: get the first parent of type 'a'
	if (event.target.tagName == "A"){
		var target=event.target;
	} else {
		var target = event.target.parentNode;
	}
	//alert(event.target.getAttribute('SpagoTarget'));
	/* remove pure-menu-selected from all items of the same submenu */
	var ul=document.getElementById("menulist");
	for (var i=0;i< ul.children.length;i++) {
		if (ul.children[i].firstChild.hasAttribute
				&& ul.children[i].firstChild.hasAttribute("menutype") 
				&& ul.children[i].firstChild.getAttribute("menutype") == target.getAttribute("menutype")){
			ul.children[i].classList.remove("pure-menu-selected");
		}
	}
	
	/* add pure-menu-selected to the target*/
	

	target.parentNode.classList.add("pure-menu-selected");

	
	/* update the iframe */
	if(target.hasAttribute('SpagoTarget')){
		document.getElementById("htmlMap").src=target.getAttribute('SpagoTarget');
	}
	if(target.hasAttribute('kpi')){
		window.kpi=target.getAttribute('kpi');
	} else if (target.hasAttribute('chart')){
		window.chart=target.getAttribute('chart');
	}else if (target.hasAttribute('zone')){
		window.zone=target.getAttribute('zone');
	}else {
		return;
	}
	var label=window.links[zone][chart][kpi];
	if (label == "error_sens"){
		spagoTarget="error_sens.html";
	}
	else if (label == "error_green"){
		spagoTarget="error_green.html";
	}
	else {
		var spagoTarget="http://localhost:8080/SpagoBI/servlet/AdapterHTTP?PAGE=LoginPage&NEW_SESSION=TRUE&DIRECT_EXEC=TRUE&OBJECT_LABEL="+label;
	}
	document.getElementById("htmlMap").src=spagoTarget;
	
};