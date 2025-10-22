package com.model;

import java.sql.Date;

public class Leave {
    private int id;
    private Date startDate;
    private Date endDate;
    private String status;

    
    
    public Leave(int id, String status, Date startDate, Date endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
	}
	// getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
