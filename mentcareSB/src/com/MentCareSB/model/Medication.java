package com.MentCareSB.model;

import application.Adapter;
import javafx.beans.property.SimpleStringProperty;

public class Medication
{
	public static void addMedication(String name, String usage, String dosage)
	{
		String fieldNames = "name, usage, dosage";
		String values = "'"+name+"', '"+usage+"', '"+dosage+"'";
		Adapter.addRow("medication", fieldNames, values);
	}
	private final SimpleStringProperty  idNumberColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  nameColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  usageColumnProperty = new SimpleStringProperty("");
	private final SimpleStringProperty  dosageColumnProperty = new SimpleStringProperty("");

	public Medication()
	{
	}
	public Medication(String idNumber, String name, String usage, String dosage)
	{
		setIdNumberColumn(idNumber);
		setNameColumn(name);
		setUsageColumn(usage);
		setDosageColumn(dosage);
	}
	public String getIdNumberColumn()
	{
		return idNumberColumnProperty.get();
	}
	public void setIdNumberColumn(String idNumber)
	{
		idNumberColumnProperty.set(idNumber);
	}
	public String getNameColumn()
	{
		return nameColumnProperty.get();
	}
	public void setNameColumn(String name)
	{
		nameColumnProperty.set(name);
	}
	public String getUsageColumn()
	{
		return usageColumnProperty.get();
	}
	public void setUsageColumn(String usage)
	{
		usageColumnProperty.set(usage);
	}
	public String getDosageColumn()
	{
		return dosageColumnProperty.get();
	}
	public void setDosageColumn(String dosage)
	{
		dosageColumnProperty.set(dosage);
	}
}