package info.kartikshah.jmx.dsl

/**
 * Created by IntelliJ IDEA.
 * User: Kartik.Shah
 * Date: Feb 15, 2010
 * Time: 10:56:56 PM
 * To change this template use File | Settings | File Templates.
 */
jmx {
    server "service:jmx:iiop://10.50.108.40:19400/jndi/weblogic/management/mbeanservers.domainruntime" {
      initialContextFactory="weblogic.jndi.WLInitialContextFactory"
      protocolProvider="weblogic.management.remote"
      username="weblogic"
      password="demoadmin"
      query "com.bea:*" {
        findAll "Type=JDBCConnectionPoolRuntime" {
          chart{
            chartType="Bar"
            attributes={m-> [m.ConnectionDelayTime, m.Name]}
            labels=["Time per Webapp", "Webapp", "Time"]
            options=[false, true, true]
            windowTitle="JBoss Module Processing Time"
            width=1200
            height=700
            refreshRate=5000
            show()
          }
        }
      }
    }
  }