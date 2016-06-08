import hudson.model.FreeStyleProject;
import hudson.triggers.SCMTrigger;
import jenkins.model.*
import hudson.plugins.git.*;
import javaposse.jobdsl.plugin.*;
import hudson.plugins.filesystem_scm.*;


jenkins = Jenkins.instance;
jenkins.setNumExecutors(1);

configuration = JenkinsLocationConfiguration.get();
configuration.setUrl(System.getenv("JENKINS_URL"));
configuration.save();

jobName = "seed-job";
gitTrigger = new SCMTrigger("* * * * *");

// Get jobs from mount /var/job-dsl folder (this docker-compose mount ./job-dsl to /var/job-dsl)
dslBuilder = new ExecuteDslScripts(scriptLocation=new ExecuteDslScripts.ScriptLocation(value = "false",
  targets="*.groovy", scriptText=""),
  ignoreExisting=false,
  removedJobAction=RemovedJobAction.DISABLE);
seedJobProject = new hudson.model.FreeStyleProject(jenkins, jobName);
seedJobProject.scm = new FSSCM("/var/job-dsl", false, false, false, false, null);

// If you want to pull
//dslBuilder = new ExecuteDslScripts(scriptLocation=new ExecuteDslScripts.ScriptLocation(value = "false",
//  targets="job-dsl/*.groovy", scriptText=""),
//  ignoreExisting=false,
//  removedJobAction=RemovedJobAction.DISABLE);
//seedJobProject.scm = new GitSCM("git@github.com:jiramot/jenkins-docker.git");
//seedJobProject.scm.branches = [new BranchSpec("*/master")];


seedJobProject.getPublishersList().add(dslBuilder);
jenkins.add(seedJobProject, jobName);
gitTrigger.start(seedJobProject, true);
