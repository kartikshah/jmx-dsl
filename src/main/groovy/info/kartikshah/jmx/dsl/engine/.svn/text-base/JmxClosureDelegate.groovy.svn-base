package info.kartikshah.jmx.dsl.engine

import javax.management.remote.JMXConnectorFactory
import javax.management.remote.JMXServiceURL as JmxUrl
import javax.naming.Context

/**
 * Created by IntelliJ IDEA.
 * User: Kartik.Shah
 * Date: Feb 12, 2010
 * Time: 11:13:51 PM
 * To change this template use File | Settings | File Templates.
 */
class JmxClosureDelegate {

  def initialContextFactory
  def protocolProvider
  def username
  def password
  
  def methodMissing(String name, args) {
    return [name, args[0]]
  }

  void server(param){
    def (serverUrl, cl) = param
    def env = [:]
    env["java.naming.factory.initial"] = this.initialContextFactory
    env[JMXConnectorFactory.PROTOCOL_PROVIDER_PACKAGES] = this.protocolProvider
    env[Context.SECURITY_PRINCIPAL] = this.username
    env[Context.SECURITY_CREDENTIALS] = this.password
    def server = JMXConnectorFactory.connect(new JmxUrl(serverUrl),env).MBeanServerConnection
    cl.delegate = new JmxServerClosureDelegate(server)
    cl.resolveStrategy = Closure.DELEGATE_FIRST
    cl()
  }
}
