<?php
include_once INCLUDE_ROOT.'/framework/base/BaseDAO.class.php';
include_once INCLUDE_ROOT.'/models/Uf.class.php';
include_once INCLUDE_ROOT.'/daos/UfDAO.class.php';
include_once INCLUDE_ROOT.'/exceptions/UfException.class.php';

class UfDAO extends BaseDAO {
	
	public function __construct_conectado($conexao = null) {
		parent::__construct_conectado($conexao);
	}

	public function __construct() {
		parent::__construct();
	}
	
	public function consultarUfs() {
		
		$sql = "SELECT uf.ufId, uf.sigla, uf.nome "
				." FROM uf "
				." ORDER BY sigla";
		
		$ufs = array();
		if($statement = parent::preparar($sql)) {
			try {
				$statement->execute();
				$statement->bind_result($ufId, $sigla, $nome);
				
				while ($statement->fetch()) {
					$ufObj = new Uf();
					$ufObj->popular($ufId, $sigla, $nome);
					$ufs[] = $ufObj;
				}
				$statement->free_result();
			} catch (Exception $e) {
				throw new ConsultarUfsException();
			}
		} else {
			throw new SQLException($sql);
		}
		$statement->close();
		return $ufs;
	}

	public function consultarUf($ufId) {
		
		$sql = "SELECT uf.sigla, uf.nome "
				." FROM uf "
				." WHERE uf.ufId=?"
				." ORDER BY sigla";
		
		$ufObj = null;
		if($statement = parent::preparar($sql)) {
			try {
				$statement->bind_param('i', $ufId);
				$statement->execute();
				$statement->bind_result($sigla, $nome); 
				if ($statement->fetch()) {
					$ufObj = new Uf();
					$ufObj->popular($ufId, $sigla, $nome);
				} else {
					throw new NenhumaUfEncontradaException();
				}
				$statement->free_result();
			} catch (Exception $e) {
				throw new NenhumaUfEncontradaException();
			}
		} else {
			throw new SQLException($sql);
		}
		$statement->close();
		return $ufObj;
	}
	
}
?>