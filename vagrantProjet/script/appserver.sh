#remarque: tomcat est embarqué(integré dans Spring Boot) donc pas necessaire de l'installer encore
# Mis à jour des paquets
sudo apt-get update

# Installation de Java (JDK 17)
sudo apt-get install -y wget
sudo wget https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.deb
sudo apt install -y ./jdk-17_linux-x64_bin.deb maven dos2unix

# Convertit les fins de ligne du fichier mvnw en format Unix
dos2unix /home/vagrant/vagrantApi/mvnw

# Installation de Maven
sudo apt-get install -y maven
#donner les drots d'execution 
sudo chmod +x /home/vagrant/vagrantApi/target/vagrantApi-0.0.1-SNAPSHOT.jar

# se positionner dans le dossier de l'application et construire l'application
cd /home/vagrant/vagrantApi
 ./mvnw clean install

 # demarrage de  l'application
nohup java -jar /home/vagrant/vagrantApi/target/vagrantApi-0.0.1-SNAPSHOT.jar > /home/vagrant/vagrantApi/app.log 2>&1 &

