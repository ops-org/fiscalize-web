<?php
include_once INCLUDE_ROOT.'/daos/UfDAO.class.php';

class UfController {
	
	public function consultarUfs() {
		$strHTML = "";
		try {
			$ufDAO = new UfDAO();
			$ufs = $ufDAO->consultarUfs();
			foreach($ufs as $uf) {
				$strHTML .= "$uf->sigla<br/>";
			}
		} catch(UfException $e) {
			$strHTML = $e->getMessage();
		}
		return $strHTML;
	}
	
	public function consultarUf($ufId) {
		$strHTML = "";
		try {
			$ufDAO = new UfDAO();
			$uf = $ufDAO->consultarUf($ufId);
			$strHTML = "<b>$uf->sigla</b>";
		} catch(UfException $e) {
			$strHTML = $e->getMessage();
		}
		return $strHTML;
	}
	
}
?>