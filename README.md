# spring-boot-playground
Playground project configurated with Spring Boot, JSF 2.2 and PrimeFaces.

## Jenkins setup groovy script
Create $JENKINS_HOME/init.groovy.d before initial startup and put following scripts.

Create admin user
```
#!groovy
import java.util.logging.Level
import java.util.logging.Logger
import jenkins.model.*
import hudson.security.*

/*
 * Create initial admin user in jenkins internal user store.
 * Needed for plugins installation. Unavailable after active-directory configuration.
 */
def instance = Jenkins.getInstance()
def log = Logger.getLogger(Jenkins.class.getName())

log.log(Level.INFO, "ANSIBLE - START - Create admin user")

final String jenkins_home = "/var/lib/jenkins/"
final String user = "admin"
final String pass = "admin"

def hudsonRealm = new HudsonPrivateSecurityRealm(false)
def users = hudsonRealm.getAllUsers()
users_s = users.collect { it.toString() }

if (user in users_s) {
	log.log(Level.INFO, "ANSIBLE - Skipped admin user creation (user already exists)")
} else {
	log.log(Level.INFO, "ANSIBLE - Create new admin user " + user)
	hudsonRealm.createAccount(user, pass)
	instance.setSecurityRealm(hudsonRealm)
	instance.save()
}

log.log(Level.INFO, "ANSIBLE - FINISHED - Create admin user")
```

Basic configuration
```
import hudson.markup.*
import hudson.maven.MavenModuleSet
import jenkins.model.*

println "[INFO] Set Server admin email address to ${adminEmail} and disasble usage statistics"
Jenkins.instance.setSystemMessage(welcomeMessage)
Jenkins.instance.setNoUsageStatistics(false)
def localConfig = JenkinsLocationConfiguration.get()
localConfig.setAdminAddress(adminEmail)
localConfig.save()

println "[INFO] Set timezone"
System.setProperty("org.apache.commons.jelly.tags.fmt.timeZone", "Europe/Berlin")

println "[INFO] ------------------------------------------------------------------------"
println "[INFO] Basic configuration complete"
println "[INFO] ------------------------------------------------------------------------"
```

Plugins installation
```
#!groovy
// adpted from https://github.com/samrocketman/jenkins-bootstrap-slack/blob/master/scripts/bootstrap.groovy
import hudson.model.UpdateSite
import hudson.PluginWrapper

/*
 * Install list of jenkins plugins. Jenkins skips duplicate installations on its own.
 * Jenkins is not restarted by this script.
 *
 * Prerequisites
 *  - Proxy information
 *  - List of plugins to install
 */
final String proxyuser = "PUT_USERNAME_HERE"
final String proxypass = "PUT_PASSWORD_HERE"

Set<String> plugins_to_install = [
	"git",
	"..."
	// git-Changelog, pipeline, owasp dependency check
]

/******************************************************************************
 * All necessary configs are set now                                          *
 ******************************************************************************/

println "[INFO] Check plugin update metadata"
Jenkins.instance.pluginManager.doCheckUpdatesServer()

println "[INFO] Start installation of ${plugins_to_install.size()} plugins"
plugins_to_install.each {
	println "Install plugin: " + it
	UpdateSite.Plugin plugin = Jenkins.instance.updateCenter.getPlugin(it)
	Throwable error = plugin.deploy().get().getError()
	if(error != null) {
		println "[ERROR]    Installing ${it} failed: ${error}"
	}
	println "Installation complete: " + it
}
Jenkins.instance.save()

println "[INFO] ------------------------------------------------------------------------"
println "[INFO] Plugins installation complete"
println "[INFO] Restart Jenkins for all installations to take effect"
println "[INFO] ------------------------------------------------------------------------"

```
