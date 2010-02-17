package info.kartikshah.jmx

import javax.management.ObjectName
import javax.management.remote.JMXConnectorFactory as JmxFactory
import javax.management.remote.JMXServiceURL as JmxUrl

import org.jfree.chart.ChartFactory
import org.jfree.data.category.DefaultCategoryDataset as Dataset
import org.jfree.chart.plot.PlotOrientation as Orientation

import groovy.swing.SwingBuilder
import javax.swing.WindowConstants as WC
import javax.management.remote.JMXConnectorFactory
import javax.swing.WindowConstants

/**
 * Example Groovy class.
 */
class JmxNativeConsole
{
    static main(String[] args) {

      def serverUrl = 'service:jmx:rmi://localhost/jndi/rmi://localhost:1090/jmxconnector'
      def server = JMXConnectorFactory.connect(new JmxUrl(serverUrl)).MBeanServerConnection
      //def serverInfo = new GroovyMBean(server, 'jboss.management.local:J2EEServer=Local,j2eeType=JVM,name=Sun Microsystems Inc. 1.6.0_18').stats
      //println "Connected to: $serverInfo"


      def query = new ObjectName('jboss.web:*')
      String[] allNames = server.queryNames(query, null)
      println allNames
      def modules = allNames.findAll{ name ->
          name.contains('j2eeType=WebModule')
      }.collect{ new GroovyMBean(server, it) }

      println "Found ${modules.size()} web modules. Processing ..."
      def dataset = new Dataset()

      modules.each{ m ->
          println m.name()
          dataset.addValue m.processingTime, 0, m.path
      }

      def labels = ['Time per Module', 'Module', 'Time']
      def options = [false, true, true]
      def chart = ChartFactory.createBarChart(*labels, dataset,
                      Orientation.VERTICAL, *options)
      def swing = new SwingBuilder()
      def frame = swing.frame(title:'Catalina Module Processing Time',
              defaultCloseOperation:WindowConstants.EXIT_ON_CLOSE) {
          panel(id:'canvas') { rigidArea(width:1200, height:500) }
      }
      frame.pack()
      frame.show()
      chart.draw(swing.canvas.graphics, swing.canvas.bounds)

    }
}
