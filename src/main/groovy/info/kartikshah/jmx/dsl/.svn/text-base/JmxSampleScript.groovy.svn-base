package info.kartikshah.jmx.dsl

jmx {
    server "service:jmx:rmi://localhost/jndi/rmi://localhost:1090/jmxconnector" {
      query "jboss.web:*" {
        findAll "j2eeType=Servlet" {
            chart{
              chartType="Bar"
              attributes={m-> [m.loadTime, m.objectName.find("name=([^,]*)"){it[1]}]}
              labels=["Load Time per Servlet", "Servlet", "Time"]
              options=[false, true, true]
              windowTitle="JBoss Servlet Processing Time"
              width=1200
              height=700
              orientation="HORIZONTAL"
              refreshRate=5000
              show()
            }
        }
      }
    }
}