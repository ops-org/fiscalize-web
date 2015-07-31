<?php
include_once INCLUDE_ROOT.'/framework/exceptions/ExceptionBase.class.php';

class UfException extends ExceptionBase { }

class ConsultarUfsException extends UfException {
	
	public function __construct($mensagem = null) {
		if($mensagem==null or $mensagem=="") $mensagem = "Não foi possível resgatar as ufs!";
		parent::__construct($mensagem);
	}
	
}

class NenhumaUfEncontradaException extends UfException {
	
	public function __construct($mensagem = null) {
		if($mensagem==null or $mensagem=="") $mensagem = "Nenhuma UF foi encontrada com os dados fornecidos!";
		parent::__construct($mensagem);
	}
	
}

?>