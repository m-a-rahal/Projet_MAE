package application;

import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.TextAnchor;
import org.jfree.data.category.DefaultCategoryDataset;

import Classes.ClauseList;
import Classes.Solution;

public class ResultPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	Application_JFrame parent;


	public class Result{ /* Internal class */
		private Classes.Solution solution;
		private float satisfiability;
		private long time;


		public Result(ClauseList clset, Classes.Solution solution, long time) {
			this.solution = solution;
			this.satisfiability = solution != null ? (float)solution.sat_count(clset)/clset.getN() : 0;
			this.time = time;
		}


		public Solution getSolution() { return solution; }
		public float getSatisfiability() { return satisfiability; }
		public long getTime() { return time; }
	}


	private DefaultCategoryDataset dataset;
	private JFreeChart barChart;
	private ArrayList<Result> resultData;

	public ResultPanel(Application_JFrame parent) {
		this.parent = parent;
		this.dataset = new DefaultCategoryDataset();
		this.resultData = new ArrayList<Result>();

		barChart = ChartFactory.createBarChart("", "", "Nombre de clauses satisfaites", this.dataset, PlotOrientation.VERTICAL, false, false, false);

		CategoryPlot plot = barChart.getCategoryPlot();

		((BarRenderer) plot.getRenderer()).setBarPainter(new StandardBarPainter());
		BarRenderer r = (BarRenderer)barChart.getCategoryPlot().getRenderer();
	    r.setSeriesPaint(0, Color.blue);

		plot.getDomainAxis().setMaximumCategoryLabelLines(10);

		barChart.setBackgroundPaint(Color.decode("#D6D9DF"));
		//barChart.setBorderPaint(Color.decode("#00FA81"));

		CategoryItemRenderer CatRenderer = ((CategoryPlot) barChart.getPlot()).getRenderer();
		CatRenderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
		CatRenderer.setDefaultItemLabelsVisible(true);
		ItemLabelPosition position = new ItemLabelPosition(ItemLabelAnchor.OUTSIDE12,TextAnchor.TOP_CENTER);
		CatRenderer.setDefaultPositiveItemLabelPosition(position);

		ChartPanel chartPanel = new ChartPanel( barChart );
		chartPanel.setDomainZoomable(false);
		chartPanel.setRangeZoomable(false);
		chartPanel.setPopupMenu(null);
		chartPanel.setLayout(null);
		chartPanel.setPreferredSize(new Dimension(680, 400));
		add(chartPanel);

		setBackground(Color.decode("#D6D9DF"));
	}


	public void setUpperBound(int upperBound) {
		barChart.getCategoryPlot().getRangeAxis().setUpperBound(upperBound);
	}


	public void addData(ClauseList clset, Solution solution, long time, int numAttempt) {
		Result result = new Result(clset, solution, time);
		parent.textField_solution.setText(solution != null ? solution.toString() : "pas do solution (non SAT)");

		resultData.add(result);
		this.dataset.setValue(solution != null ? solution.sat_count(clset) : 0, "SAT", "essais "+numAttempt+"\n("+round(time/1000.0, 2)+" s)");
	}


	public void clearData() {
		resultData.clear();
		dataset.clear();
	}


	public double getSatisfiabilityRate() {
		float sumSatisfiedClausesPerAttempt = 0;

		for(int i=0; i<resultData.size(); i++)
			sumSatisfiedClausesPerAttempt += resultData.get(i).getSatisfiability();

		return round(100*sumSatisfiedClausesPerAttempt/resultData.size(), 7);
	}


	public double getAverageSearchTime() {
		long sumSearchTimePerAttempt = 0;

		for(int i=0; i<resultData.size(); i++)
			sumSearchTimePerAttempt += resultData.get(i).getTime();

		return round(sumSearchTimePerAttempt/resultData.size(), 9);
	}


	public void makeTitle(String searchMethodName) {
		if(! resultData.isEmpty())
			this.barChart.setTitle("Nombre de clauses satisfaites pour l'algorithme "+searchMethodName+"\ntaux de clauses satisfaites = "+getSatisfiabilityRate()+"%");
	}


	private double round(double value, int places) {
		if (places < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(Double.toString(value));
		bd = bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
	}
}
