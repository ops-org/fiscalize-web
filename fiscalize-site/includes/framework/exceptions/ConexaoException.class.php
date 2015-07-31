<?php
class ConexaoException extends Exception {
	
	public function __construct($mensagem = null) {
		$mensagem = "Erro ao conectar no banco de dados! ".$mensagem==null?"":$mensagem;
		parent::__construct($mensagem);
	}
	
}
?>