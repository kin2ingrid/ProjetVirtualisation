#!/bin/bash

# Mettre à jour la liste des paquets
apt-get update

# Installer Apache et les dépendances nécessaires
apt-get install -y apache2 libapache2-mod-wsgi-py3 python3-pip python3-dev libpq-dev

# Installer virtualenv
pip3 install virtualenv

# Créer un environnement virtuel
cd /vagrant_web
virtualenv venv
source venv/bin/activate

# Installer Django et psycopg2
pip install django psycopg2-binary

# Activer les modules proxy et proxy_http d'Apache
a2enmod proxy proxy_http

# Redémarrer Apache pour appliquer les modifications
service apache2 restart
