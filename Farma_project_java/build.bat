@echo off
echo Compilando...
javac -d out model\*.java service\*.java main\*.java ui\*.java

echo Criando JAR...
jar cfm Farma_project.jar manifest.txt -C out .

echo Pronto! Rode com: java -jar Farma_project.jar
