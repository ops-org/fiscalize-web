#!/bin/bash
# CRIA DIRETÓRIOS BASE (NAO EXECUTE DIRETAMENTE! SCRIPT UTILIZADO PELOS OUTROS SCRIPTS)
COLOR='\033[0;32m'
COLOR_SEC='\033[0;33m'
COLOR_TER='\033[0;31m'
NO_COLOR='\033[0m'

echo -e "${COLOR_SEC}VERIFICANDO DIRETORIOS${NO_COLOR}\n"

# cria diretórios
DIR="/root"
DIR_COTAS="$DIR/fiscalize"
DIR_BUILD="$DIR_COTAS/build"
DIR_LOGS="$DIR_BUILD/logs"
DIR_IMAGENS="$DIR_BUILD/imagens"

if [ ! -d "$DIR_COTAS" ]; then
	echo -e "${COLOR}Criando diretorio BASE: $DIR_COTAS ${NO_COLOR}"
	mkdir $DIR_COTAS
fi

if [ ! -d "$DIR_BUILD" ]; then
	echo -e "${COLOR}Criando diretorio BUILD: $DIR_BUILD ${NO_COLOR}"
	mkdir $DIR_BUILD
fi

if [ ! -d "$DIR_LOGS" ]; then
	echo -e "${COLOR}Criando diretorio LOGS: $DIR_LOGS ${NO_COLOR}"
	mkdir $DIR_LOGS
fi

if [ ! -d "$DIR_IMAGENS" ]; then
	echo -e "${COLOR}Criando diretorio IMAGENS: $DIR_IMAGENS ${NO_COLOR}"
	mkdir $DIR_IMAGENS
fi

exit 1