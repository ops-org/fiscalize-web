#!/bin/bash
# CONSTROI JARS DA CARGA
COLOR='\033[0;32m'
COLOR_SEC='\033[0;33m'
COLOR_TER='\033[0;31m'
NO_COLOR='\033[0m'

DIR_BUILD="/root/fiscalize/build"
DIR_PROJETOS="$DIR_BUILD/projetos"

echo -e "${COLOR}Excluindo diretorio de BUILD antigo: $DIR_BUILD ${NO_COLOR}"
rm -R $DIR_BUILD

# cria diretórios
/root/fiscalize/prepare.sh

if [ ! -d "$DIR_PROJETOS" ]; then
	echo -e "${COLOR}Criando diretorio PROJETOS: $DIR_PROJETOS ${NO_COLOR}"
	mkdir $DIR_PROJETOS
fi

cd $DIR_PROJETOS
# puxa projetos do git
REPOSITORY="https://dfavoratti@bitbucket.org/dfavoratti/fiscalize.git"
	
echo -e "${COLOR}Puxando projetos GIT: $REPOSITORY ${NO_COLOR}"
git clone $REPOSITORY

# compila JAR CARGA FISCALIZE
echo -e "\n${COLOR_SEC}COMPILANDO JAR CARGA FISCALIZE ${NO_COLOR}\n"
	
DIR_GIT="$DIR_PROJETOS/fiscalize"
DIR_PROJECT_CARGA="$DIR_GIT/cargafiscalize"

DIR_JAR_CARGA="$DIR_PROJECT_CARGA/target/cargafiscalize-1.0.jar"
DIR_JAR_CARGA_FINAL="$DIR_BUILD/cargafiscalize.jar"

cd $DIR_PROJECT_CARGA

mvn package
cp $DIR_JAR_CARGA $DIR_JAR_CARGA_FINAL

# compila JAR NORMALIZA CARGA
echo -e "\n${COLOR_SEC}COMPILANDO JAR NORMALIZA CARGA ${NO_COLOR}\n"

DIR_GIT="$DIR_PROJETOS/fiscalize"
DIR_PROJECT_NORMALIZA="$DIR_GIT/normalizacotas"

DIR_JAR_NORMALIZA="$DIR_PROJECT_NORMALIZA/target/normalizacotas-1.0.jar"
DIR_JAR_NORMALIZA_FINAL="$DIR_BUILD/normalizacotas.jar"

cd $DIR_PROJECT_NORMALIZA

mvn package
cp $DIR_JAR_NORMALIZA $DIR_JAR_NORMALIZA_FINAL

echo -e "${COLOR_SEC}CRIACAO DE JAR FINALIZADAS EM\n ${COLOR}Carga: $DIR_JAR_CARGA_FINAL\n Normaliza: $DIR_JAR_NORMALIZA_FINAL ${NO_COLOR}\n"

exit 1
