/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
import java.awt.Dimension;
import java.util.List;
import org.knowm.xchart.XChartPanel;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.XYSeries;
import org.knowm.xchart.style.Styler;
import org.knowm.xchart.style.colors.ChartColor;
import org.knowm.xchart.style.lines.SeriesLines;
import org.knowm.xchart.style.markers.SeriesMarkers;

/**
 *
 * @author Administrador
 */
public class grafico {

    XChartPanel panel;
    XYSeries serie;
    XYChart chart;

    public XChartPanel grafico(String Mx, String My) {
        chart = new XYChartBuilder().width(600).height(600).theme(Styler.ChartTheme.XChart).title("Envoltória").xAxisTitle(Mx).yAxisTitle(My).build();
        chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.WHITE));
        chart.getStyler().setPlotGridLinesColor(new Color(0, 0, 0));
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideSE);
        chart.getStyler().setChartFontColor(Color.black);
        chart.getStyler().setChartTitleBoxBackgroundColor(Color.white);
        chart.getStyler().setChartTitleBoxVisible(false);
        chart.getStyler().setChartTitleBoxBorderColor(Color.black);
        chart.getStyler().setPlotGridLinesVisible(true);
        chart.getStyler().setAxisTickMarkLength(5);
        chart.getStyler().setPlotMargin(10);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setToolTipsEnabled(true);
        chart.getStyler().setToolTipsAlwaysVisible(false);
        chart.getStyler().setPlotContentSize(1);
        chart.getStyler().setXAxisTitleVisible(false);
        panel = new XChartPanel(chart);
        panel.setPreferredSize(new Dimension(600,600));
        panel.setAutoscrolls(true);
        return panel;
    }

    public void setEspacamento(double p) {
        chart.getStyler().setYAxisTickMarkSpacingHint((int) p);
        chart.getStyler().setXAxisTickMarkSpacingHint((int) p);

    }

    public void setAxis() {
        double[] xData_xAxis = new double[]{serie.getXMin(), 0, serie.getXMax()};

        double[] yData_xAxis = new double[]{0, 0, 0};

        double[] xData_yAxis = new double[]{0, 0, 0};

        double[] yData_yAxis = new double[]{serie.getYMin(), 0, serie.getYMax()};

        XYSeries xaS = chart.addSeries("Axis X", xData_xAxis, yData_xAxis);
        xaS.setMarker(SeriesMarkers.NONE);
        xaS.setLineColor(Color.black);
        XYSeries yaS = chart.addSeries("Axis Y", xData_yAxis, yData_yAxis);
        yaS.setMarker(SeriesMarkers.NONE);
        yaS.setLineColor(Color.black);
        panel.revalidate();

    }

    public void setSeries(List<Float> Mx, List<Float> My, float taxa) {

        serie = chart.addSeries("ω = " + String.format("%.2f", taxa), Mx, My);
        serie.setMarker(SeriesMarkers.NONE);
        serie.setLineStyle(SeriesLines.SOLID);
        serie.setXYSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);
        serie.setLabel("ω = " + String.format("%.2f", taxa));
        panel.revalidate();
        panel.repaint();

    }

}
