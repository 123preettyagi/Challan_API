package com.example.Challan_API.dto;

public class DailyReportDTO 
{

	
	 private int totalChallans;
	    private Double totalAmount;
	    private Double collectedAmount;
	    private Double pendingAmount;
	    private String reportDate; // new field
	    private String location;   // new field
		public int getTotalChallans() {
			return totalChallans;
		}
		public void setTotalChallans(int totalChallans) {
			this.totalChallans = totalChallans;
		}
		public Double getTotalAmount() {
			return totalAmount;
		}
		public void setTotalAmount(Double totalAmount) {
			this.totalAmount = totalAmount;
		}
		public Double getCollectedAmount() {
			return collectedAmount;
		}
		public void setCollectedAmount(Double collectedAmount) {
			this.collectedAmount = collectedAmount;
		}
		public Double getPendingAmount() {
			return pendingAmount;
		}
		public void setPendingAmount(Double pendingAmount) {
			this.pendingAmount = pendingAmount;
		}
		public String getReportDate() {
			return reportDate;
		}
		public void setReportDate(String reportDate) {
			this.reportDate = reportDate;
		}
		public String getLocation() {
			return location;
		}
		public void setLocation(String location) {
			this.location = location;
		}
	    
	    
	
}
