<select id="kpi" name="kpi">

<?php
	require 'configuration.php';
	$con = mysqli_connect($DB_server, $DB_user, $DB_pass, $DB_database);
	if (mysqli_connect_errno()) {
		printf("Connect failed: %s\n", mysqli_connect_error());
		exit();
	}
	
	
	$gran=mysqli_real_escape_string($con,$_GET["granularity"]);
	
	if($gran == "Municipality"){
		$query="SELECT * FROM $DB_kpi_db WHERE Granularity = 'Municipality' ";
	} else {
		$dist=mysqli_real_escape_string($con,$_GET["district"]);
		$query="SELECT * FROM $DB_kpi_db WHERE Granularity <> 'Municipality' AND District = '$dist'";
	}

	$result_kpis = mysqli_query($con, $query);
	while ($record = mysqli_fetch_assoc($result_kpis)){
		$kpi_id=$record["ID"];
		$kpi_desc=$record["Description"];				
		echo '<option value="'.$kpi_id.'" >'.$kpi_desc.'</option>';

	}

?>
</select>