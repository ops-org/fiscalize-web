<?php
	header ("content-type: application/json; charset=UTF-8");
	date_default_timezone_set("America/Sao_Paulo");
?>
[
  {
    "notaFiscalId": 1,
    "parlamentar": {
      "nome": "Zezinho das Couves",
      "email": "zezinho@camara.gov.br",
      "urlFoto": "http://www.ops.com.br/fotos/zezinho.jpg"
    },
    "partido": {
	  "nome": "Partido dos Trabalhadores",
	  "sigla": "PT",
	  "urlFoto": "http://www.ops.com.br/partidos/pt.jpg"
	},
    "cota": "Passagens Aéreas",
    "uf": "SP",
    "dataEmissao": "10/10/2014",
    "descricao": "Viagem para Buenos Aires",
    "fornecedor": "GOL",
    "cpfcnpj": "04.584.598/0001-04",
    "ano": 2014,
    "mes": 10,
    "numeroDocumento": "78624",
    "valor": "1234,00",
    "valorGlosa": "50,00",
    "valorLiquido": "1568,00"
  },
  {
    "notaFiscalId": 2,
    "parlamentar": {
      "nome": "Joao das Cenouras",
      "email": "jonhy@camara.gov.br",
      "urlFoto": "http://www.ops.com.br/fotos/joao.jpg"
    },
    "partido": {
	  "nome": "Partido dos Trabalhadores",
	  "sigla": "PT",
	  "urlFoto": "http://www.ops.com.br/partidos/pt.jpg"
	},
    "cota": "Material de Escritório",
    "uf": "SC",
    "dataEmissao": "01/01/2014",
    "descricao": null,
    "fornecedor": null,
    "cpfcnpj": "09.246.873/0001-11",
    "ano": 2014,
    "mes": 5,
    "numeroDocumento": "6884",
    "valor": "245,00",
    "valorGlosa": "10,00",
    "valorLiquido": "0,00"
  }
]
