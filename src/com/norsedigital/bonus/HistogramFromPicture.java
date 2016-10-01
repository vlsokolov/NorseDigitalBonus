package com.norsedigital.bonus;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.data.statistics.HistogramDataset;

public class HistogramFromPicture {

    private static final int BINS = 256;
    private BufferedImage image = null;
    private String inputFile = "";
    private String outputFile = "";
      

    public HistogramFromPicture(String argsin, String argsout) {
       
    	inputFile = argsin;
    	outputFile = argsout;
    	
    	try {
			InputStream in = new FileInputStream(new File(inputFile));
			image = ImageIO.read(in);
		} catch (IOException e1) {
			e1.printStackTrace(System.err);
		}
    	
        HistogramDataset dataset = new HistogramDataset();
        Raster raster = image.getRaster();
        final int w = image.getWidth();
        final int h = image.getHeight();
        double[] r = new double[w * h];
        r = raster.getSamples(0, 0, w, h, 0, r);
        dataset.addSeries("Red", r, BINS);
        r = raster.getSamples(0, 0, w, h, 1, r);
        dataset.addSeries("Green", r, BINS);
        r = raster.getSamples(0, 0, w, h, 2, r);
        dataset.addSeries("Blue", r, BINS);
       
        JFreeChart chart = ChartFactory.createHistogram("Histogram", "Value",
            "Count", dataset, PlotOrientation.VERTICAL, true, true, false);
        XYPlot plot = (XYPlot) chart.getPlot();
        XYBarRenderer renderer = (XYBarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardXYBarPainter());        
        
        try {
        	OutputStream out = new FileOutputStream(new File(outputFile));
			ChartUtilities.writeChartAsPNG(out, chart, 700, 500);        	
		} catch (IOException e) {
			e.printStackTrace(System.err);
		}       
    }
    
    public static void main(String[] args) {        
            new HistogramFromPicture(args[2], args[4]); 
    }
}
