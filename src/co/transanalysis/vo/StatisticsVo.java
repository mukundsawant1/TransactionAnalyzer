package co.transanalysis.vo;

public class StatisticsVo {

	private double numOfTransactions=0;
	private double avaerageVal=0;
	
	public double getNumOfTransactions() {
		return numOfTransactions;
	}
	public void setNumOfTransactions(double numOfTransactions) {
		this.numOfTransactions = numOfTransactions;
	}
	public double getAvaerageVal() {
		return avaerageVal;
	}
	public void setAvaerageVal(double avaerageVal) {
		this.avaerageVal = avaerageVal;
	}
	@Override
	public String toString() {
		return "StatasticsVo [numOfTransactions=" + numOfTransactions + ", avaerageVal=" + avaerageVal + "]";
	}
	
}
