import hudson.model.FreeStyleProject;
//import hudson.plugins.git.GitSCM;
//import hudson.plugins.git.BranchSpec;
import hudson.triggers.SCMTrigger;
import jenkins.model.*
import hudson.plugins.git.*;
import javaposse.jobdsl.plugin.*;


jenkins = Jenkins.instance;
jenkins.setNumExecutors(8);

println "aaaaaaaaaaaaa"

configuration = JenkinsLocationConfiguration.get();
configuration.setUrl(System.getenv("JENKINS_URL"));
configuration.save();

jobName = "seed-job";
gitTrigger = new SCMTrigger("* * * * *");
dslBuilder = new ExecuteDslScripts(scriptLocation=new ExecuteDslScripts.ScriptLocation(value = "false", 
  targets="build-pr-from-github,build-github-branch", scriptText=""), 
  ignoreExisting=false, 
  removedJobAction=RemovedJobAction.DISABLE);
seedJobProject = new hudson.model.FreeStyleProject(jenkins, jobName);
//dslProject.scm = new GitSCM("git@github.com:jiramot/jenkins-docker.git");
//dslProject.scm.branches = [new BranchSpec("*/master")];
seedJobProject.getPublishersList().add(dslBuilder);
jenkins.add(seedJobProject, jobName);
gitTrigger.start(seedJobProject, true);