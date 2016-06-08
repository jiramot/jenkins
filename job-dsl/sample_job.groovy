folder("sample")

job("sample/build") {
  description ""
  disabled(false)
  using "template-defaults"

  deliveryPipelineConfiguration("Build", "Checkout and unit tests")
  label("master")

  steps{
    shell("""
      echo 'building...'
      """.stripIndent().trim())
  }

  publishers{
    downstreamParameterized{
      trigger("sample/integration") {
        parameters{
          predefinedProp("GIT_COMMIT", "\$GIT_COMMIT")
        }
      }
    }
  }
}

job("sample/integration") {
  description ""
  disabled(false)
  using "template-defaults"

  deliveryPipelineConfiguration("integration", "Integration testing")
  label("master")

  steps{
    shell("""
      echo 'integration testing...'
      """.stripIndent().trim())
  }

  publishers{
    downstreamParameterized{
      trigger("sample/staging") {
        parameters{
          predefinedProp("GIT_COMMIT", "\$GIT_COMMIT")
        }
      }
    }
  }
}

job("sample/staging") {
  description ""
  disabled(false)
  using "template-defaults"

  deliveryPipelineConfiguration("staging", "deploy to staging")
  label("master")

  steps{
    shell("""
      echo 'deploy to staging...'
      """.stripIndent().trim())
  }
}

job("sample/production") {
  description ""
  disabled(false)
  using "template-defaults"

  deliveryPipelineConfiguration("production", "deploy to production")
  label("master")

  steps{
    shell("""
      echo 'Deploying to production...'
      """.stripIndent().trim())
  }
}



