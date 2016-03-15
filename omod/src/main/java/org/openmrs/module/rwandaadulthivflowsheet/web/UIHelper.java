package org.openmrs.module.rwandaadulthivflowsheet.web;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.openmrs.DrugOrder;
import org.openmrs.Obs;
import org.openmrs.module.rwandaadulthivflowsheet.mapper.Lab;
import org.openmrs.module.rwandaadulthivflowsheet.web.model.AdultHivFlowsheetFormData;

public class UIHelper {
	
	public static String getCheckBoxWidget(boolean checked, String label) {
		StringBuilder builder = new StringBuilder(256);
		builder.append("<span class=\"checkbox-box\">");
		if(checked)
			builder.append("&#x2713;");
		else
			builder.append("&nbsp;");
		builder.append("</span>");
		builder.append("<span class=\"checkbox-label\">");
		builder.append(label);
		builder.append("</span>");
		return builder.toString();
	}
	
	public static String formatRegimenDisplaySummary(List<DrugOrder> drugOrders) {
		if(drugOrders == null || drugOrders.size() == 0)
			return "";
		
		StringBuilder builder = new StringBuilder(1024);
		boolean firstDrug = true;
		for(DrugOrder drugOrder : drugOrders) {
			if(firstDrug)
				firstDrug = false;
			else
				builder.append("&nbsp;+&nbsp;");
			builder.append(formatRegimenDisplaySummary(drugOrder));
		}
		return builder.toString();
	}

	public static String formatRegimenDisplaySummary(DrugOrder drugOrder) {
		if(drugOrder == null || drugOrder.getDrug() == null)
			return "";
		
		String drugName = drugOrder.getDrug().getName();
		Double dose = drugOrder.getDose();
		String units = "";
		String frequency = "";
		if(drugOrder.getUnits() != null)
			units = drugOrder.getUnits().replaceAll("tab\\(s\\)", "cé");
		if(drugOrder.getFrequency() != null)
			frequency = drugOrder.getFrequency().replaceAll(" x 7 days/week", "").replaceAll("day", "j");
		return drugName + " " + dose + units + " " + frequency;
	}

	public static String getCd4XAxis(AdultHivFlowsheetFormData data) {
		Collection<Lab> cd4List = data.getObsMapper().getCd4ObsList();
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		
		Date minDate = new Date();
		if(cd4List != null && cd4List.size() > 0) {
			for(Lab obs : cd4List) {
				if(obs.getDate() != null && minDate.after(obs.getDate())) {
					minDate = obs.getDate();
				}
			}
		}
		calendar.setTime(minDate);
		int minYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(minYear, 1, 1);
		long min = calendar.getTimeInMillis();
		calendar.set(currentYear + 5, 1, 1);
		long max = calendar.getTimeInMillis();
		
		StringBuilder builder = new StringBuilder();
		builder.append("xaxis: { ");
		builder.append("mode: \"time\"");
		builder.append(", timeformat: \"%d/%m/%y\"");
		builder.append(", min: ").append(min);
		builder.append(", max: ").append(max);
		builder.append(", tickSize: [6, \"month\"]");
		//	max: 13,
		//	ticks: [
		//			for(int index = 0; index < 14; index++) {
		//				if(index > 0)
		//					out.print(", ");
		//				if(index < cd4List.size())
		//					out.print("["+index+", \""+formData.formatDate(AdultHivFlowsheetFormData.FormatDate_General, cd4List.get(index).getObsDatetime())+"\"]");
		//				else
		//					out.print("["+index+", \""+AdultHivFlowsheetFormData.DateTextPlaceHolder+"\"]");
		//			}
		//		] 
		
		builder.append(" }");
		
		return builder.toString();
	}
	
	public static String getBMIXAxis(AdultHivFlowsheetFormData data) {
		Collection<Obs> weightList = data.getObsMapper().getWeightObsList();
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		
		Date minDate = new Date();
		if(weightList != null && weightList.size() > 0) {
			for(Obs obs : weightList) {
				if(obs.getObsDatetime() != null && minDate.after(obs.getObsDatetime())) {
					minDate = obs.getObsDatetime();
				}
			}
		}
		calendar.setTime(minDate);
		int minYear = calendar.get(Calendar.YEAR);
		calendar.clear();
		calendar.set(minYear, 1, 1);
		long min = calendar.getTimeInMillis();
		calendar.set(currentYear + 5, 1, 1);
		long max = calendar.getTimeInMillis();
		
		StringBuilder builder = new StringBuilder();
		builder.append("xaxis: { ");
		builder.append("mode: \"time\"");
		builder.append(", timeformat: \"%d/%m/%y\"");
		builder.append(", min: ").append(min);
		builder.append(", max: ").append(max);
		builder.append(", tickSize: [6, \"month\"]");
		//	max: 13,
		//	ticks: [
		//			for(int index = 0; index < 14; index++) {
		//				if(index > 0)
		//					out.print(", ");
		//				if(index < cd4List.size())
		//					out.print("["+index+", \""+formData.formatDate(AdultHivFlowsheetFormData.FormatDate_General, cd4List.get(index).getObsDatetime())+"\"]");
		//				else
		//					out.print("["+index+", \""+AdultHivFlowsheetFormData.DateTextPlaceHolder+"\"]");
		//			}
		//		] 
		
		builder.append(" }");
		
		return builder.toString();
	}

}