package info.kartikshah.jmx.dsl.engine

/**
 * Created by IntelliJ IDEA.
 * User: Kartik.Shah
 * Date: Feb 12, 2010
 * Time: 11:25:44 PM
 * To change this template use File | Settings | File Templates.
 */
class JmxQueryClosureDelegate {

  def allNames
  def server

  JmxQueryClosureDelegate(allNames, server){
    this.allNames = allNames
    this.server = server
  }

  def methodMissing(String name, args) {
    return [name, args[0]]
  }
  
  void findAll(param){
    def (filter, cl) = param
    def modules = allNames.findAll{ name ->
          name.contains(filter)
      }.collect{ new GroovyMBean(server, it) }

    cl.delegate = new JmxFindAllClosureDelegate(modules)
    cl.resolveStrategy = Closure.DELEGATE_FIRST
    cl()

  }

  void find(String filter, Closure cl){

  }
}
