apt-get update
apt-get install -y nginx
cat <<EOT > /etc/nginx/sites-available/default
server {
    listen 82;
    server_name localhost;
    location / {
        proxy_pass http://192.168.1.5:8082;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
EOT
      systemctl restart nginx
    
  