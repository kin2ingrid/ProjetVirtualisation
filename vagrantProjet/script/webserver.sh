#!/bin/bash

# Mis à jour des paquets et installation de Apache
sudo apt-get update
sudo apt-get install -y apache2

# Installation de JDK 17
sudo apt-get install -y openjdk-17-jdk


# Activer les modules nécessaires pour Apache
sudo a2enmod rewrite

# Créer un fichier de configuration pour l'application
cat <<EOL | sudo tee /etc/apache2/sites-available/myapp2.conf
<VirtualHost *:84>
    ServerAdmin webmaster@localhost
    ServerName 192.168.1.4
    DocumentRoot /var/www/html

    <Directory /var/www/html>
        Options Indexes FollowSymLinks
        AllowOverride All
        Require all granted
    </Directory>

    ErrorLog ${APACHE_LOG_DIR}/error.log
    CustomLog ${APACHE_LOG_DIR}/access.log combined
</VirtualHost>
EOL

# Activer le site et recharger Apache
sudo a2ensite myapp2.conf
sudo systemctl reload apache2

# Assurer les permissions correctes
sudo chown -R www-data:www-data /var/www/html
sudo chmod -R 755 /var/www/html

# Copie du fichier .jar de l'application dans le répertoire approprié
sudo cp /vagrant/target/vagrantFront-0.0.1-SNAPSHOT.jar /var/www/html/vagrantFront-0.0.1-SNAPSHOT.jar
sudo chmod +x /var/www/html/vagrantFront-0.0.1-SNAPSHOT.jar

# Créeation d'un fichier de service systemd pour l'application Spring Boot
cat <<EOL | sudo tee /etc/systemd/system/myapp2.service
[Unit]
Description=My vagrant frontEnd application
After=network.target

[Service]
User=www-data
ExecStart=/usr/bin/java -jar /var/www/html/vagrantFront-0.0.1-SNAPSHOT.jar
SuccessExitStatus=143
Restart=always
RestartSec=10

[Install]
WantedBy=multi-user.target
EOL

# Démarrage et activation du service
sudo systemctl daemon-reload
sudo systemctl enable myapp2
sudo systemctl start myapp2

# Vérification d statut du service pour s'assurer qu'il fonctionne correctement
sudo systemctl status myapp2
