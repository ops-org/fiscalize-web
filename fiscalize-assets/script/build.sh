#!/bin/bash
# CONSTROI JARS DA CARGA
COLOR='\033[0;32m'
COLOR_SEC='\033[0;33m'
COLOR_TER='\033[0;31m'
NO_COLOR='\033[0m'

DIR_TOMCAT="/var/lib/tomcat7/webapps"
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
DIR_GIT="$DIR_PROJETOS/fiscalize"
REPOSITORY="https://dfavoratti@bitbucket.org/dfavoratti/fiscalize.git"
	
echo -e "${COLOR}Puxando projetos GIT: $REPOSITORY ${NO_COLOR}"
git clone $REPOSITORY

# compila PROJETO BASE JAVA
echo -e "\n${COLOR_SEC}COMPILANDO BASE JAVA ${NO_COLOR}\n"
DIR_PROJECT_BASE="$DIR_GIT/fiscalize-java-base"

cd $DIR_PROJECT_BASE
mvn clean install

# compila JAR CARGA
echo -e "\n${COLOR_SEC}COMPILANDO JAR CARGA ${NO_COLOR}\n"
	
DIR_CARGA_PROJECT="$DIR_GIT/fiscalize-java-carga"
DIR_CARGA_JAR="$DIR_CARGA_PROJECT/target/fiscalize-java-carga-1.0.jar"
DIR_CARGA_JAR_FINAL="$DIR_BUILD/fiscalize-java-carga.jar"

cd $DIR_CARGA_PROJECT
mvn package
cp $DIR_CARGA_JAR $DIR_CARGA_JAR_FINAL

# compila JAR NORMALIZA
echo -e "\n${COLOR_SEC}COMPILANDO JAR NORMALIZA ${NO_COLOR}\n"

DIR_NORMALIZA_PROJECT="$DIR_GIT/fiscalize-java-normaliza"
DIR_NORMALIZA_JAR="$DIR_NORMALIZA_PROJECT/target/fiscalize-java-normaliza-1.0.jar"
DIR_NORMALIZA_JAR_FINAL="$DIR_BUILD/fiscalize-java-normaliza.jar"

cd $DIR_NORMALIZA_PROJECT
mvn package
cp $DIR_NORMALIZA_JAR $DIR_NORMALIZA_JAR_FINAL

# compila WAR WEB
echo -e "\n${COLOR_SEC}COMPILANDO WAR WEB ${NO_COLOR}\n"

DIR_WEB_PROJECT="$DIR_GIT/fiscalize-web"
DIR_WEB_WAR="$DIR_WEB_PROJECT/target/fiscalize-web-1.0.war"
DIR_WEB_WAR_FINAL="$DIR_TOMCAT/fiscalize-web.war"

cd $DIR_WEB_PROJECT
mvn war:war
cp $DIR_WEB_WAR $DIR_WEB_WAR_FINAL

echo -e "${COLOR_SEC}CRIACAO DE JAR FINALIZADAS EM\n ${COLOR}Carga: $DIR_CARGA_JAR_FINAL\n Normaliza: $DIR_NORMALIZA_JAR_FINAL\n Web: $DIR_WEB_WAR_FINAL ${NO_COLOR}\n"

exit 1
