package info.kartikshah.jmx.dsl.engine

/**
 * Created by IntelliJ IDEA.
 * User: Kartik.Shah
 * Date: Feb 12, 2010
 * Time: 11:05:24 PM
 * To change this template use File | Settings | File Templates.
 */
class JmxReportingDslEngine {

  static main(String[] args){
    if(args.length != 1)
    {
      println("Usage: JmxReportingDslEngine <ScriptFileName>")
    }
    runEngine(new File(args[0]))
  }

  static void runEngine(File dsl){
    Script dslScript = new GroovyShell().parse(dsl.text)
    dslScript.metaClass = createExpandoMetaClass(dslScript.class, {
      ExpandoMetaClass emc ->
        emc.jmx = {
          Closure cl ->
            cl.delegate = new JmxClosureDelegate()
            cl.resolveStrategy = Closure.DELEGATE_FIRST
            cl()
        }
    })
    dslScript.run()
   }

  static ExpandoMetaClass createExpandoMetaClass(Class clazz, Closure cl){
    ExpandoMetaClass emc = new ExpandoMetaClass(clazz, false)
    cl(emc)
    emc.initialize()
    return emc
  }
}
