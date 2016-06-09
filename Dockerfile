FROM jenkins

USER root
ADD https://ga-beacon.appspot.com/UA-79032210-1/docker/build/jenkins?pixel /pixel

USER jenkins

COPY plugins.txt /plugins.txt
RUN /usr/local/bin/plugins.sh /plugins.txt
