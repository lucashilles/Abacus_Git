/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Color;
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

    public XChartPanel grafico() {
        chart = new XYChartBuilder().width(600).height(600).title("Envoltória").xAxisTitle("Mxdr (kN/m)").yAxisTitle("Mydr(kN/m)").build();
        chart.getStyler().setPlotBackgroundColor(ChartColor.getAWTColor(ChartColor.GREY));
        chart.getStyler().setPlotGridLinesColor(new Color(255, 255, 255));
        chart.getStyler().setChartBackgroundColor(Color.WHITE);
        chart.getStyler().setLegendVisible(false);
        chart.getStyler().setLegendPosition(Styler.LegendPosition.OutsideE);
        chart.getStyler().setChartFontColor(Color.black);
        chart.getStyler().setChartTitleBoxBackgroundColor(Color.white);
        chart.getStyler().setChartTitleBoxVisible(true);
        chart.getStyler().setChartTitleBoxBorderColor(Color.white);
        chart.getStyler().setPlotGridLinesVisible(false);
        chart.getStyler().setAxisTickMarkLength((int) 0.1);
        chart.getStyler().setPlotMargin(10);
        chart.getStyler().setLegendVisible(true);
        chart.getStyler().setToolTipsAlwaysVisible(true);
        panel = new XChartPanel(chart);
        return panel;
    }

    public void setEspacamento(double p) {
        chart.getStyler().setAxisTickPadding((int) p);
        chart.getStyler().setChartPadding((int) p);
    //    chart.getStyler().setXAxisTickMarkSpacingHint((int) p);
     //   chart.getStyler().setYAxisTickMarkSpacingHint((int) p);
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
