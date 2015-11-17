import hudson.model.FreeStyleProject;
import hudson.triggers.SCMTrigger;
import jenkins.model.*
import hudson.plugins.git.*;
import javaposse.jobdsl.plugin.*;
import hudson.plugins.filesystem_scm.*;


jenkins = Jenkins.instance;
jenkins.setNumExecutors(4);

configuration = JenkinsLocationConfiguration.get();
configuration.setUrl(System.getenv("JENKINS_URL"));
configuration.save();

jobName = "seed-job";
gitTrigger = new SCMTrigger("* * * * *");
dslBuilder = new ExecuteDslScripts(scriptLocation=new ExecuteDslScripts.ScriptLocation(value = "false", 
  targets="*.groovy", scriptText=""), 
  ignoreExisting=false, 
  removedJobAction=RemovedJobAction.DISABLE);
seedJobProject = new hudson.model.FreeStyleProject(jenkins, jobName);
seedJobProject.scm = new FSSCM("/var/job-dsl", false, false, false, false, null);
seedJobProject.getPublishersList().add(dslBuilder);
jenkins.add(seedJobProject, jobName);
gitTrigger.start(seedJobProject, true);