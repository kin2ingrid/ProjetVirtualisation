#!/bin/bash

# Mettre à jour la liste des paquets
apt-get update

# Installer PostgreSQL et ses composants
apt-get install -y postgresql postgresql-contrib

# Créer un utilisateur PostgreSQL et configurer les paramètres
sudo -u postgres psql -c "CREATE USER vagrant WITH PASSWORD 'vagrant';"
sudo -u postgres psql -c "CREATE DATABASE vagrantBD;"
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE vagrantBD TO vagrant;"
