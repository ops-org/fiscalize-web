<?php
class Uf {
	
	public $ufId;
	public $sigla;
	public $nome;
	
	public function popular($ufId, $sigla, $nome) {
		$this->ufId = $ufId;
		$this->sigla = $sigla;
		$this->nome = $nome;
	}
	
}
?>