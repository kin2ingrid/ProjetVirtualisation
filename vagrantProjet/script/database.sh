# Provisionnement avec un script shell
#!/bin/bash

# Mettre à jour les paquets
apt-get update

# Installer PostgreSQL
apt-get install -y postgresql

# Créer un utilisateur PostgreSQL et une base de données
sudo -u postgres psql -c "ALTER USER postgres  PASSWORD 'vagrant';"
sudo -u postgres psql -c "create database vagrant;"
sudo -u postgres psql -c "grant all privileges on database vagrant to postgres;"

# Démarrer le service PostgreSQL
sudo systemctl start postgresql
