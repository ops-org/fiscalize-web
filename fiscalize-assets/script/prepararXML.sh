#!/bin/bash
# BAIXA ARQUIVO E INICIAR A CARGA
COLOR='\033[0;31m'
COLOR_SEC='\033[0;33m'
NO_COLOR='\033[0m'

# cria diretórios
/root/cotas/criarDiretoriosBase.sh

DIR_BASE="/root/cotas/build"
DIR_DOWNLOAD="$DIR_BASE/downloads"

if [ ! -d "$DIR_DOWNLOAD" ]; then
	echo -e "${COLOR}Criando diretorio DOWNLOAD: $DIR_DOWNLOAD ${NO_COLOR}"
	cd $DIR_BASE
	mkdir $DIR_DOWNLOAD
fi
cd $DIR_DOWNLOAD

# prepara arquivo XML
ZIP_URL="http://www.camara.gov.br/cotas/AnoAtual.zip"
ZIP_NAME="AnoAtual.zip"

echo -e "${COLOR}Download: $ZIP_URL ${NO_COLOR}"
wget -N $ZIP_URL

echo -e "${COLOR}Unzip: $ZIP_NAME ${NO_COLOR}"
unzip -o $ZIP_NAME