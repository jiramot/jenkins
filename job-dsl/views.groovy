/**
 * VIEWS
 */
def views = [
  [
    name: "All",
    description: "",
    regex: ".*",
    recurse: true,
  ]
].each { i ->
  listView(i["name"]) {
    description i["description"]

    filterBuildQueue(false)
    filterExecutors(false)

    jobs {
      regex(i["regex"])
    }

    columns {
      status()
      weather()
      name()
      lastSuccess()
      lastFailure()
      lastDuration()
      buildButton()
    }

    configure {
      (it / "recurse").setValue(i["recurse"])
    }
  }
}

deliveryPipelineView("Pipeline") {
  pipelineInstances(1)
  showAggregatedPipeline(false)
  columns(1)
  sorting(Sorting.NONE)
  updateInterval(2)
  enableManualTriggers(true)
  showAvatars(true)
  showChangeLog(false)
  pipelines {
    component("sample", "sample/build")
  }
}

buildMonitorView("buildstatus") {
  description("Build monitor for all projects")
  recurse(true)
  jobs {
    regex("(?!seed-|template-|packages.*).*")
  }
}
