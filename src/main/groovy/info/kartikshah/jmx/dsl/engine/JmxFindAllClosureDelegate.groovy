package info.kartikshah.jmx.dsl.engine

import org.jfree.data.category.DefaultCategoryDataset

/**
 * Created by IntelliJ IDEA.
 * User: Kartik.Shah
 * Date: Feb 12, 2010
 * Time: 11:32:33 PM
 * To change this template use File | Settings | File Templates.
 */
class JmxFindAllClosureDelegate {
  def modules

  JmxFindAllClosureDelegate(modules){
    this.modules = modules
  }

  void listAttributes(){
    modules.each { m ->
      println m.name()
      println m.listAttributeNames()
    }
  }
  void listOperations(){
    modules.each { m ->
      println m.name()
      println m.listOperationNames()
    }
  }

  void chart(Closure cl){
    cl.delegate = new ChartDelegate(modules)
    cl.resolveStrategy = Closure.DELEGATE_FIRST
    cl()
  }
}
