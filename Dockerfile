FROM jenkins

USER jenkins

COPY plugins.txt /plugins.txt
RUN /usr/local/bin/plugins.sh /plugins.txt

COPY init.groovy.d/create-seed-job.groovy /usr/share/jenkins/ref/init.groovy.d/00-create-seed-job.groovy
