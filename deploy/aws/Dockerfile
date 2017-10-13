FROM desavera/krushweb:krushwebapi

# Exposes the API port
EXPOSE 3000

# Starts MongoDB
#RUN /usr/sbin/service mongodb start


RUN rm -rf /var/www/*
ADD . /var/www


RUN echo "nameserver 8.8.8.8" >> /etc/resolv.conf && cd /var/www/krushwebapi && /usr/bin/npm run start

