#!/bin/bash

# Mettre à jour les paquets
sudo apt-get update

# Installer Apache
sudo apt-get install -y apache2

# Installer mod_wsgi pour servir l'application Django
sudo apt-get install -y libapache2-mod-wsgi-py3

# Configurer Apache pour servir l'application Django
cat <<EOF | sudo tee /etc/apache2/sites-available/000-default.conf
<VirtualHost *:81>
    ServerAdmin webmaster@localhost
    DocumentRoot /var/www/html

    Alias /static /vagrant_data/myproject/static
    <Directory /vagrant_data/myproject/static>
        Require all granted
    </Directory>

    <Directory /vagrant_data/myproject/myproject>
        <Files wsgi.py>
            Require all granted
        </Files>
    </Directory>

    WSGIDaemonProcess myproject python-path=/vagrant_data/myproject python-home=/vagrant_data/myproject/venv
    WSGIProcessGroup myproject
    WSGIScriptAlias / /vagrant_data/myproject/myproject/wsgi.py
</VirtualHost>
EOF

# Activer le site par défaut
sudo a2ensite 000-default

# Redémarrer Apache
sudo systemctl restart apache2

