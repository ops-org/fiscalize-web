#!/bin/bash
# CRIA DIRETÓRIOS BASE (NAO EXECUTE DIRETAMENTE! SCRIPT UTILIZADO PELOS OUTROS SCRIPTS)
COLOR='\033[0;32m'
COLOR_SEC='\033[0;33m'
COLOR_TER='\033[0;31m'
NO_COLOR='\033[0m'

echo -e "${COLOR_SEC}VERIFICANDO DIRETORIOS${NO_COLOR}\n"

DIR="/root"
DIR_FISCALIZE="$DIR/fiscalize"
DIR_BKP="$DIR_FISCALIZE/bkp"
DIR_LOGS="$DIR_FISCALIZE/logs"
DIR_BUILD="$DIR_FISCALIZE/build"
DIR_IMAGENS="$DIR_BUILD/imagens"

# apaga bkp mais antigos que 10 dias
find $DIR_BKP -maxdepth 2 -mtime +10 -type f -name "*.zip" -exec rm -f {} \;

# cria diretórios
if [ ! -d "$DIR_FISCALIZE" ]; then
	echo -e "${COLOR}Criando diretorio BASE: $DIR_FISCALIZE ${NO_COLOR}"
	mkdir $DIR_FISCALIZE
fi

if [ ! -d "$DIR_BKP" ]; then
	echo -e "${COLOR}Criando diretorio BKP: $DIR_BKP ${NO_COLOR}"
	mkdir $DIR_BKP
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