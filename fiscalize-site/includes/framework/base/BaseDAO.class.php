<?php
	include_once INCLUDE_ROOT.'/framework/exceptions/ConexaoException.class.php';
	include_once INCLUDE_ROOT.'/framework/exceptions/SQLException.class.php';

class BaseDAO {
	
	private $HOST = "localhost";
	private $DB_NAME = "fiscalize";
	private $USER = "fiscalize";
	private $PASSWORD = "fiscalize";

	private $conexao = null;
	
	public function __construct() {
		$this->conectar();
	}
	
	public function __construct_conectado($conexao = null) {
		if($this->conexao==null) {
			$this->conectar();
		}
	}
	
	private function conectar()  {
		if(!($this->conexao = new mysqli($this->HOST, $this->USER, $this->PASSWORD, $this->DB_NAME))) {
			throw new ConexaoException();
		}
		
		if (mysqli_connect_errno()) {
		    throw new ConexaoException(mysqli_connect_error());
		} else {
			$this->conexao->set_charset("utf8");
		}
	}
	
	protected function preparar($sql) {
		$conexao = $this->getConexao();
		if(empty($sql) OR !($conexao)) {
			return false;
		}
		return $conexao->prepare($sql);
	}
	
	protected function autoCommit($autoCommit) {
		$conexao = $this->getConexao();
		if(empty($autoCommit) OR !($conexao)) {
			return false;
		} else {
			return $conexao->autocommit($autoCommit);
		}
	}
	
	protected function commit() {
		$conexao = $this->getConexao();
		if(($conexao)) {
			return $conexao->commit();
		} else {
			return false;
		}
	}
	
	protected function rollback() {
		$conexao = $this->getConexao();
		if(($conexao)) {
			return $conexao->rollback();
		} else {
			return false;
		}
	}
	
	protected function close() {
		$conexao = $this->getConexao();
		if(($conexao)) {
			return $conexao->close();
		} else {
			return false;
		}
	}
	
	protected function getId() {
		$conexao = $this->getConexao();
		if(($conexao)) {
			return $conexao->insert_id;
		} else {
			return false;
		}
	}
	
	protected function getAffectedRows() {
		$conexao = $this->getConexao();
		if(($conexao)) {
			return $conexao->affected_rows;
		} else {
			return false;
		}
	}
	
	public function getConexao() {
		if($this->conexao==null) {
			$this->conectar();
		}
		return $this->conexao;
	}
	
}
?>