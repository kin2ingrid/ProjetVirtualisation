sudo apt-get update
    sudo apt-get install -y wget
    
    # Téléchargement de JDK 21
    wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.deb -O jdk-21.deb
    
    # Installation de JDK 21
    sudo apt-get install -y ./jdk-21.deb
    
    # Configuration de l'environnement Java
    sudo update-alternatives --install /usr/bin/java java /usr/lib/jvm/jdk-21/bin/java 1
    sudo update-alternatives --set java /usr/lib/jvm/jdk-21/bin/java

  