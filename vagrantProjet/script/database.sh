# Mettre à jour les paquets
sudo apt-get update

# Installer PostgreSQL
sudo apt-get install -y postgresql postgresql-contrib

# Configurer PostgreSQL
sudo -u postgres psql -c "ALTER USER postgres  PASSWORD 'formation';"
sudo -u postgres psql -c "CREATE DATABASE vagrantBD;"
sudo -u postgres psql -c "GRANT ALL PRIVILEGES ON DATABASE vagrantBD TO postgres;"

# Démarrer le service PostgreSQL
sudo systemctl enable postgresql
sudo systemctl start postgresql
