/**
 * TEMPLATES
 */
job("template-defaults") {
  description "This job sets default properties for all jobs"
  disabled(true)

  logRotator(-1, 20)
  quietPeriod(1)

  wrappers {
    colorizeOutput()
    timestamps()
  }
}
