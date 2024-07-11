#!/bin/bash

# Mettre à jour les paquets
sudo apt-get update

# Installer Python et pip
sudo apt-get install -y python3 python3-pip

# Installer Django
pip3 install django psycopg2

# Créer un projet Django
django-admin startproject myproject /vagrant_data/myproject

# Modifier les paramètres de la base de données dans settings.py
sed -i "s/'ENGINE': 'django.db.backends.sqlite3'/'ENGINE': 'django.db.backends.postgresql'/" /vagrant_data/myproject/myproject/settings.py
sed -i "s/'NAME': BASE_DIR \/ 'db.sqlite3'/'NAME': 'vagrant'/" /vagrant_data/myproject/myproject/settings.py
sed -i "s/# 'USER': 'mydatabaseuser'/'USER': 'vagrant'/" /vagrant_data/myproject/myproject/settings.py
sed -i "s/# 'PASSWORD': 'mypassword'/'PASSWORD': 'vagrant'/" /vagrant_data/myproject/myproject/settings.py
sed -i "s/# 'HOST': 'localhost'/'HOST': '192.168.33.10'/" /vagrant_data/myproject/myproject/settings.py

# Appliquer les migrations de la base de données
cd /vagrant_data/myproject
python3 manage.py migrate

# Démarrer le serveur Django
python3 manage.py runserver 0.0.0.0:8000
