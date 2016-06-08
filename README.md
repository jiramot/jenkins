# Jenkins-docker

This project for demo immutable jenkins with job-dsl plugin.

So in this project I mount the job-dsl folder into docker, Another way you can change the init.groovy.d/create-seed-job.groovy to create seed job from pulling from the git

### To start docker compose

```
$docker-compose up
```

Official jenkins docker has script to download plugin from plugins.txt during build a docker image, so that I put all necessary plugin into that file

by the way that script only download and save into jenkins plugins folder BUT is some plugin had a dependencies plugin so you need to put all dependencies plugin into plugins.txt.

### See more
- [Job-dsl](https://github.com/jenkinsci/job-dsl-plugin)
- [Job DSL API Viewe](https://jenkinsci.github.io/job-dsl-plugin/)
- [Official jenkins docker](https://github.com/jenkinsci/docker)
