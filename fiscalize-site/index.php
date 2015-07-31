<?php
	include_once 'web_include.php';
	include_once INCLUDE_ROOT.'/controllers/UfController.class.php';

	$ufController = new UfController();
	
	$html = $ufController->consultarUfs();
	echo "<p>$html</p>";
	
	$html = $ufController->consultarUf(0);
	echo "<p>Sigla da UF com ufId=0 é: $html</p>";
	
	$html = $ufController->consultarUf(1);
	echo "<p>Sigla da UF com ufId=1 é: $html</p>";

?>