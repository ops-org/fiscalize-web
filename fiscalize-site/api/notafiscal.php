<?php
	include_once 'json_include.php';
	include_once INCLUDE_ROOT.'/controllers/PresencaController.class.php';

	$arrJson = array();	
	$erro = false;

	$publicoId = validarToken();
	if($publicoId) {
		$presencaController = new PresencaController();
		
		$method = $_SERVER['REQUEST_METHOD'];
		
		if($method==POST) {
			if(isset($_POST["flyerId"])) {
				$flyerId = $_POST["flyerId"];
				try {
					$retorno = $presencaController->incluirPresenca($publicoId, $flyerId);
					if($retorno) {
						http_response_code(HTTP_CREATED);
					} else {
						http_response_code(HTTP_INTERNAL_SERVER_ERROR);
					}
				} catch (IncluirPresencaException $e) {
				    $erro = $e->getMessage();
				}
			} else {
				http_response_code(HTTP_BAD_REQUEST);
			}
		
		} else if($method==DELETE) {
			if(isset($_REQUEST["flyerId"])) {
				$flyerId = $_REQUEST["flyerId"];
				try {
					$retorno = $presencaController->excluirPresenca($publicoId, $flyerId);
					if($retorno) {
						http_response_code(HTTP_NO_CONTENT);
					} else {
						http_response_code(HTTP_INTERNAL_SERVER_ERROR);
					}
				} catch (ExcluirPresencaException $e) {
				    $erro = $e->getMessage();
				}
			} else {
				http_response_code(HTTP_BAD_REQUEST);
			}
		} else {
			http_response_code(HTTP_METHOD_NOT_ALLOWED);
		}
	} else {
		http_response_code(HTTP_UNAUTHORIZED);
	}

	if($erro) {
		$arrJson = array('erro' => $erro);
		echo json_encode($arrJson);
	}

?>