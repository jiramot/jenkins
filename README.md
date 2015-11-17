# Jenkins-docker

To create jenkins's docker container with plugin job-sdl to auto re-create all your job.

Docker compose will mount job-dsl folder to container.

```
ci_master:
  image: jiramot/jenkins-docker
  container_name: ci_master
  environment:
    JAVA_OPTS: "-Djava.awt.headless=true"
  ports:
    - "50000:50000"
    - "8080:8080"
  volumes:
    - /var/jenkins_home
    - ./job-dsl:/var/job-dsl
```

To start docker compose

```
$docker-compose up
```

See more
- [job-dsl](https://github.com/jenkinsci/job-dsl-plugin)