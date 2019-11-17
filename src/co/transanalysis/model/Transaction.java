package co.transanalysis.model;

import java.util.Date;

public class Transaction {
private String id;
private Date date;
private double amount;
private String merchant;
private String transactionType;
private String relatedTransaction;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public Date getDate() {
	return date;
}
public void setDate(Date date) {
	this.date = date;
}
public double getAmount() {
	return amount;
}
public void setAmount(double amount) {
	this.amount = amount;
}
public String getMerchant() {
	return merchant;
}
public void setMerchant(String merchant) {
	this.merchant = merchant;
}
public String getTransactionType() {
	return transactionType;
}
public void setTransactionType(String transactionType) {
	this.transactionType = transactionType;
}
public String getRelatedTransaction() {
	return relatedTransaction;
}
public void setRelatedTransaction(String relatedTransaction) {
	this.relatedTransaction = relatedTransaction;
}
@Override
public String toString() {
	return "Transaction [id=" + id + ", date=" + date + ", amount=" + amount + ", merchant=" + merchant
			+ ", transactionType=" + transactionType + ", relatedTransaction=" + relatedTransaction + "]";
}

}
