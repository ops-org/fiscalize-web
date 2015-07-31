<?php
class SQLException extends Exception {
	
	public function __construct($mensagem = null) {
		$mensagem = "SQL Inválido! ".$mensagem==null?"":$mensagem;
		parent::__construct($mensagem);
	}
	
}
?>