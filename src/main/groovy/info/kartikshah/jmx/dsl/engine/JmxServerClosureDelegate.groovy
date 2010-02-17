package info.kartikshah.jmx.dsl.engine

import javax.management.ObjectName

/**
 * Created by IntelliJ IDEA.
 * User: Kartik.Shah
 * Date: Feb 12, 2010
 * Time: 11:16:46 PM
 * To change this template use File | Settings | File Templates.
 */
class JmxServerClosureDelegate {
  def server

  JmxServerClosureDelegate(server){
    this.server = server
  }

  def methodMissing(String name, args) {
   return [name, args[0]]
  }

  void query(param){
    def (objectName, cl) = param
    def query = new ObjectName(objectName)
    String[] allNames = server.queryNames(query, null)

    //println allNames

    cl.delegate = new JmxQueryClosureDelegate(allNames, server)
    cl.resolveStrategy = Closure.DELEGATE_FIRST
    cl()
  }
}
