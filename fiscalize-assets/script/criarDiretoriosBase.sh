#!/bin/bash
# CRIA DIRETÓRIOS BASE
COLOR='\033[0;31m'
COLOR_SEC='\033[0;33m'
NO_COLOR='\033[0m'

echo -e "${COLOR_SEC}VERIFICANDO DIRETORIOS${NO_COLOR}\n"

# cria diretórios
DIR="/root"
DIR_COTAS="$DIR/cotas"
DIR_BUILD="$DIR_COTAS/build"

if [ ! -d "$DIR_COTAS" ]; then
	echo -e "${COLOR}Criando diretorio BASE: $DIR_COTAS ${NO_COLOR}"
	cd $DIR
	mkdir $DIR_COTAS
fi

if [ ! -d "$DIR_BUILD" ]; then
	echo -e "${COLOR}Criando diretorio BUILD: $DIR_BUILD ${NO_COLOR}"
	cd $DIR_COTAS
	mkdir $DIR_BUILD
fi