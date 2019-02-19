def project = 'sebastian9486/spring-boot-playground'
def branchApi = new URL("https://api.github.com/repos/${project}/branches")
def branches = new groovy.json.JsonSlurper().parse(branchApi.newReader())
branches.each {
    def branchName = it.name
    def jobName = "${project}_${branchName}".replaceAll('/','_')
    job(jobName) {
        scm {
            git("git://github.com/${project}.git", branchName)
        }
        steps {
            maven("clean verify -Dproject.name=${project}/${branchName}")
        }
    }
}
