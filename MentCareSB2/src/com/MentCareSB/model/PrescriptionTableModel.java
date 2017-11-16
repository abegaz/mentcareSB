package com.MentCareSB.model;


public class PrescriptionTableModel 
{
    private String prescriptionName;
    
    public PrescriptionTableModel(String pName) 
    {
        this.prescriptionName = pName;
    }
    
    public String getPrescriptionName() 
    {
        return prescriptionName;
    }
}