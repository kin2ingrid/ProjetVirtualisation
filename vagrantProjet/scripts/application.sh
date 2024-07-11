#!/bin/bash

# Mettre à jour la liste des paquets
apt-get update

# Installer les dépendances nécessaires
apt-get install -y python3-pip python3-dev libpq-dev

# Installer virtualenv
pip3 install virtualenv

# Créer un environnement virtuel
cd /vagrant_app
virtualenv venv
source venv/bin/activate

# Installer Django, Gunicorn et psycopg2
pip install django gunicorn psycopg2-binary

# Démarrer et activer Gunicorn
systemctl start gunicorn
systemctl enable gunicorn
