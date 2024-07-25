# Mis à jour des paquets
sudo apt-get update

# Installation de Apache
sudo apt-get install -y apache2

# Démarrage du service Apache
sudo systemctl enable apache2
sudo systemctl start apache2

# S'assurer que le dossier synchronisé est utilisé
sudo rm -rf /var/www/html
sudo ln -fs /vagrant/vagrantFront /var/www/html
