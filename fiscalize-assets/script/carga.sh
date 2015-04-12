#!/bin/bash
# BAIXA ARQUIVO E INICIA A CARGA (SE HOUVER CODIGO FONTE NOVO, EXECUTE build.sh ANTES)
COLOR='\033[0;32m'
COLOR_SEC='\033[0;33m'
COLOR_TER='\033[0;31m'
NO_COLOR='\033[0m'

# cria diretórios
/root/fiscalize/prepare.sh

DB_PROTOCOL="TCP"
DB_USER="fiscalize"
DB_PASSWORD="fiscalize"
DB_DATABASE="fiscalize"

DIR="/root"
DIR_FISCALIZE="$DIR/fiscalize"
DIR_BKP="$DIR_FISCALIZE/bkp"
DIR_BUILD="$DIR_FISCALIZE/build"
DIR_LOGS="$DIR_BUILD/logs/"
DIR_IMAGENS="$DIR_BUILD/imagens/"
DIR_DOWNLOAD="$DIR_BUILD/downloads"

JAR_CARGA="$DIR_BUILD/cargafiscalize.jar"
JAR_NORMALIZA="$DIR_BUILD/normalizacotas.jar"

if [ ! -f "$JAR_CARGA" ]; then
	echo -e "${COLOR_TER}Nao existe o JAR de carga: $JAR_CARGA (execute build.sh antes) ${NO_COLOR}"
	exit 0
fi

if [ ! -f "$JAR_NORMALIZA" ]; then
	echo -e "${COLOR_TER}Nao existe o JAR de normalizacao: $JAR_NORMALIZA (execute build.sh antes) ${NO_COLOR}"
	exit 0
fi

if [ ! -d "$DIR_DOWNLOAD" ]; then
	echo -e "${COLOR}Criando diretorio DOWNLOAD: $DIR_DOWNLOAD ${NO_COLOR}"
	mkdir $DIR_DOWNLOAD
fi
cd $DIR_DOWNLOAD

# prepara arquivo XML
ZIP_URL="http://www.camara.gov.br/cotas/AnoAtual.zip"
ZIP_NAME="AnoAtual.zip"
XML_NAME="AnoAtual.xml"

echo -e "${COLOR}Download: $ZIP_URL ${NO_COLOR}"
wget -N $ZIP_URL

echo -e "${COLOR}Unzip: $ZIP_NAME ${NO_COLOR}"
unzip -o $ZIP_NAME

# BKP banco atual 
DUMP_NAME=bkp.sql.`date +"%Y%m%d_%H%M%S"`
DUMP_NAME_ZIP="$DUMP_NAME.zip"
DUMP_PATH="$DIR_BKP/$DUMP_NAME"
DUMP_PATH_ZIP="$DIR_BKP/$DUMP_NAME_ZIP"

echo -e "${COLOR}Criando e Zipando BKP DATABASE ATUAL: $DUMP_PATH_ZIP ${NO_COLOR}"
mysqldump --opt --protocol=${DB_PROTOCOL} --user=${DB_USER} --password=${DB_PASSWORD} ${DB_DATABASE} > $DUMP_PATH
cd $DIR_BKP
zip $DUMP_NAME_ZIP $DUMP_NAME
rm $DUMP_NAME

# le arquivo xml e popula banco de dados (carga)
java -jar $JAR_CARGA DB $XML_NAME $DIR_LOGS

# le banco desnormalizado e normaliza (carga)
java -jar $JAR_NORMALIZA $DIR_LOGS $DIR_IMAGENS

DIR_IMAGENS_WEB="/var/www/html/fiscalize"
if [ ! -d "$DIR_IMAGENS_WEB" ]; then
	echo -e "${COLOR}Criando diretorio IMAGENS WEB: $DIR_IMAGENS_WEB ${NO_COLOR}"
	mkdir $DIR_IMAGENS_WEB
fi

echo -e "${COLOR}Copiando imagens de partidos e deputados em: $DIR_IMAGENS_WEB ${NO_COLOR}"
cp -R "$DIR_IMAGENS/*" $DIR_IMAGENS_WEB

echo -e "\n${COLOR_SEC}CARGA FINALIZADA ${NO_COLOR}\n"

exit 1