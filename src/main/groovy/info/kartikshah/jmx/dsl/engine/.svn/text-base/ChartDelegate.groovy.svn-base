package info.kartikshah.jmx.dsl.engine

import org.jfree.chart.ChartFactory
import groovy.swing.SwingBuilder

import org.jfree.data.category.DefaultCategoryDataset
import org.jfree.chart.plot.PlotOrientation as Orientation
import javax.swing.WindowConstants as WC
/**
 * Created by IntelliJ IDEA.
 * User: Kartik.Shah
 * Date: Feb 14, 2010
 * Time: 1:39:42 AM
 * To change this template use File | Settings | File Templates.
 */
class ChartDelegate {
  def modules

  def chartType
  def attributes
  def labels
  def options
  def windowTitle
  def width
  def height
  def refreshRate
  def orientation = "VERTICAL"
  def dataset
  ChartDelegate(modules){
    this.modules = modules
  }

  void show(){
    switch(chartType){
      case "Bar": drawBarChart(); break;
      default: break;
    }
  }

  void drawBarChart(){
    calculateData()
    def chart = ChartFactory.createBarChart(*labels, dataset, Orientation."${orientation}", *options)
    def swing = new SwingBuilder()
    def frame = swing.frame(title:windowTitle, defaultCloseOperation:WC.EXIT_ON_CLOSE){
      panel(id:'canvas') {rigidArea(width:width, height:height)}
    }

    while(true){
      calculateData()
      chart.fireChartChanged()
      frame.pack()
      frame.show()
      chart.draw(swing.canvas.graphics, swing.canvas.bounds)
      sleep(refreshRate)
    }
  }

  void calculateData(){
    def newDataset = new DefaultCategoryDataset()
    modules.each{ m ->
      def dsCall = attributes.call(m)
      newDataset.addValue dsCall[0], 0, dsCall[1]
    }
    this.dataset = newDataset
  }
}
