function check_before_submit(name, surname, email){
	update_district_role(); 
	return checkfields(name, surname, email);
}

function checkfields(name, surname, email){
	if(name.length==0 || surname.length ==0 || email.length ==0){
		document.getElementById("loginmsg").innerHTML="You missed something.";
		return(false);
	}
	var isUserValid=/^[a-zA-Z]{2,200}$/;
	var isMailValid=/^[a-zA-Z0-9_-]*@[a-zA-Z0-9_-]*$/;
	var isPasswordValid=/^.*(?=.{10,})(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$/;
	
	if(!(isUserValid.test(name))){
		document.getElementById("loginmsg").innerHTML="Name is not valid.<br/>" +
				" You can use from 2 to 200 characters.<br/> No special symbols allowed";
		return(false);
	}
	
	if(!(isUserValid.test(surname))){
		document.getElementById("loginmsg").innerHTML="Surname is not valid.<br/>" +
				" You can use from 2 to 200 characters.<br/> No special symbols allowed";
		return(false);
	}
	
	/*if(!(isMailValid.test(email))){
		document.getElementById("loginmsg").innerHTML="Email is not valid.<br/>" +
				" Please check.";
		return(false);
	}*/
}

function update_district_role(){
	var district=document.getElementById("district").value;
	var el=document.getElementById("role");
	if(el.options[el.selectedIndex].getAttribute("granularity")=="District"){
		el.options[el.selectedIndex].value=district-1+parseInt(el.options[el.selectedIndex].getAttribute("orig_value"));
	}
	return true;
}

function hide_neigh(el){
	/*var selects=document.getElementsByTagName("select");
	for (var i=0;i<selects.length;i++){
		if (selects[i].getAttribute("name")=="neighbourhood")
		{		var neigh=selects[i];}
	}*/

	var gran=el.options[el.selectedIndex].getAttribute("granularity");
	dist=document.getElementById("dist");
	if (gran=='District'||gran=='Area'){

		dist.style.display="block";
	}else{
		dist.style.display="none";
	}

		neigh=document.getElementById("neighbourhood");
	if (el.options[el.selectedIndex].getAttribute("granularity")=='Area'){

		neigh.style.display="block";
	}else{
		neigh.style.display="none";
	}
}

function load_active_KPIs(){
	var el=document.getElementById("role");
	var role=el.options[el.selectedIndex].value;
	var granularity=el.options[el.selectedIndex].getAttribute("granularity");
	var di=document.getElementById("district");
	var district=di.options[di.selectedIndex].value;
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp = new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange = function()
	  {
	  if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
		{
		document.getElementById("filtered_kpis").innerHTML = xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET","active_kpis.php?granularity="+granularity+"&district="+district,true);
	xmlhttp.send();
}

function load_areas(){
	var di=document.getElementById("district");
	var district=di.options[di.selectedIndex].value;
	
	var xmlhttp;
	if (window.XMLHttpRequest)
	  {// code for IE7+, Firefox, Chrome, Opera, Safari
	  xmlhttp = new XMLHttpRequest();
	  }
	else
	  {// code for IE6, IE5
	  xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	  }
	xmlhttp.onreadystatechange = function()
	  {
	  if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
		{
		document.getElementById("neighbourhood").innerHTML = xmlhttp.responseText;
		}
	  }
	xmlhttp.open("GET","area_in_district.php?district="+district,true);
	xmlhttp.send();
}