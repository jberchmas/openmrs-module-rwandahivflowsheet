package org.openmrs.module.rwandahivflowsheet.web.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.DrugOrder;
import org.openmrs.Patient;
import org.openmrs.PatientProgram;
import org.openmrs.PatientState;
import org.openmrs.Program;
import org.openmrs.api.context.Context;
import org.openmrs.module.heightweighttracker.impl.pih.WHOMapping;
import org.openmrs.module.rwandahivflowsheet.impl.pih.ConceptDictionary;
import org.openmrs.module.rwandahivflowsheet.impl.pih.ProphylaxisMapping;
import org.openmrs.module.rwandahivflowsheet.regimen.RegimenDrugHelper;
import org.openmrs.module.rwandahivflowsheet.web.UIHelper;

public class PediHivFlowsheetFormData extends HivFlowsheetFormData {
	
	protected final Log log = LogFactory.getLog(getClass());

	private WHOMapping whoMapping = null;
	
	public PediHivFlowsheetFormData(Patient p) {
		super(p);
	}

	public String getGender()
	{
		return getPatient().getGender();
	}
	
	public PatientProgram getPediHIVProgram() {
		Program hivProgram = Context.getProgramWorkflowService().getProgram(ConceptDictionary.ProgramId_PediHIVProgram);
		if (hivProgram == null)
			throw new RuntimeException("Please configure the mapping in the global property for the hiv program.  The current value resolves to " + ConceptDictionary.ProgramId_PediHIVProgram);
		PatientProgram ret = null;
		if (hivProgram != null){
			List<PatientProgram> ppList = Context.getProgramWorkflowService().getPatientPrograms(this.getPatient(), hivProgram, null, null, null, null, false);
			if (ppList != null && ppList.size() > 0){
				//sorts descending
				Collections.sort(ppList,new Comparator<PatientProgram>() {
			        public int compare(PatientProgram left, PatientProgram right) {
			     	    Date now = new Date();
			            long leftWeight = (left.getDateEnrolled() == null) ? now.getTime() : left.getDateEnrolled().getTime();
			            long rightWeight = (right.getDateEnrolled() == null) ? now.getTime() : right.getDateEnrolled().getTime();
			            if (leftWeight < rightWeight) 
			           	    return 1;
			            return -1;
			        }    
			     });
				return ppList.get(0);
			}
		}	
		return ret;
	}
	
	private int getPatientInformedState(Date date){
		PatientProgram pp = getPediHIVProgram();
		if (pp != null){
			for (PatientState ps : pp.getStates()){
				if (ps.getState().getProgramWorkflow().getConcept().getConceptId().equals(ConceptDictionary.INFORMED_STATUS))
				{
					if(ps.getStartDate() != null && date != null && (ps.getStartDate().equals(date) || ps.getStartDate().before(date)))
					{
						if(ps.getEndDate() == null || ps.getEndDate().after(date))
						{
							return ps.getState().getConcept().getId();
						}
						
					}
				}
			}
		}
		return 0;
	}
	
	public boolean isPatientInformed(Date date)
	{
		int id = getPatientInformedState(date);
		if(id == ConceptDictionary.PATIENT_INFORMED)
		{
			return true;
		}
		return false;
	}
	
	public boolean isPatientNotInformed(Date date)
	{
		int id = getPatientInformedState(date);
		if(id == ConceptDictionary.PATIENT_NOT_INFORMED)
		{
			return true;
		}
		return false;
	}
	
	private int arvRowNum = 0;
	public String getArvRegimenHtmlTable() {
    	StringBuilder builder = new StringBuilder(4096);
    	builder.append("<table class=\"section-table\">\n");
    	builder.append("<tr>\n");
    	builder.append("<th scope=\"col\" id=\"col-arv-emr-1\">EMR</th>\n");
    	builder.append("<th scope=\"col\" id=\"col-arv-info\">TRAITEMENT ANTIRETRVIRAL</th>\n");
    	builder.append("<th scope=\"col\" id=\"col-arv-emr-2\">EMR</th>\n");
    	builder.append("<th scope=\"col\" id=\"col-arv-stop\"></th>\n");
    	builder.append("</tr>\n");

    	Map<Date,Set<DrugOrder>> drugOrdersGroupedByStartAndEndDate = getRegimenChanges(this.getDrugOrders(ConceptDictionary.ANTIRETROVIRAL_DRUGS));
		int index = 0;
		Map.Entry<Date, Set<DrugOrder>> prevMapEntry = null;
		List<DrugOrder> prevOrder = null;
	
		List<DrugOrder> currentOrder = null;
		for (Map.Entry<Date, Set<DrugOrder>> e : drugOrdersGroupedByStartAndEndDate.entrySet()){
			
			
			currentOrder = new ArrayList<DrugOrder>(e.getValue());
			
			//for pedi we only care about when the drug changes not if only the dosage changes.
			if(changeRegimen(prevOrder, currentOrder) || index == drugOrdersGroupedByStartAndEndDate.size() -1)
			{
				if (prevMapEntry != null && prevMapEntry.getValue().size() > 0)
				{
					Date changeDate = null;
					if(index < drugOrdersGroupedByStartAndEndDate.size() -1)
					{
						for(DrugOrder dro: prevOrder)
						{
							if(dro.isDiscontinued(e.getKey()))
							{
								changeDate = dro.getDiscontinuedDate();
							}
						}
					}
					addARVRegimenTableRow(builder,  prevOrder, prevMapEntry.getKey(), changeDate);
					
				}
				
				Date changeDate = null;
				for(DrugOrder dro: currentOrder)
				{
					if(dro.isDiscontinuedRightNow())
					{
						changeDate = dro.getDiscontinuedDate();
					}
				}
				if ((index == drugOrdersGroupedByStartAndEndDate.size() -1 && e.getValue().size() > 0 && changeRegimen(prevOrder, currentOrder)) || (drugOrdersGroupedByStartAndEndDate.size() == 1 && e.getValue().size() > 0 ))
					addARVRegimenTableRow(builder,  new ArrayList<DrugOrder>(e.getValue()), e.getKey(), changeDate);
				
				prevMapEntry = e;		
			}
			
			if(prevMapEntry == null)
			{
				prevMapEntry = e;
			}
			
			if(changeRegimen(prevOrder, currentOrder) || prevOrder == null)
			{
				prevOrder = currentOrder;
			}
			index ++;
			
			//add to usedDrugOrderIds, for easy exclusion later on in the 'other medications' category'
			if (e.getValue() != null){
				for (DrugOrder dorTmp : e.getValue()){
					if (dorTmp.getOrderId() != null)
						usedDrugOrderIds.add(dorTmp.getOrderId());
				}
			}
		}

		// Make sure there is always a blank row, or 3.
		addARVRegimenTableRow(builder, null, null, null);
		addARVRegimenTableRow(builder, null, null, null);
		addARVRegimenTableRow(builder, null, null, null);
		addARVRegimenTableRow(builder, null, null, null);
		builder.append("</table>\n");
		
		return builder.toString();
    }
    
	private boolean changeRegimen(List<DrugOrder> prevOrder, List<DrugOrder> currentOrder) {
		
		if(prevOrder == null)
		{
			return false;
		}
		
		if(UIHelper.formatRegimenDisplaySummaryPedi(prevOrder).equals(UIHelper.formatRegimenDisplaySummaryPedi(currentOrder)))
		{
			return false;
		}
		
		return true;
	}

	private void addARVRegimenTableRow(StringBuilder builder, List<DrugOrder> drugOrders, Date changeDate, Date nextChangeDate) {
    	builder.append("<tr>\n");
    	boolean drugRegimenInEmr = !(drugOrders == null || drugOrders.size() == 0);
    	// Add a check if it's from the EMR
    	builder.append("<td class=\"section-emr\">");
    	if(drugRegimenInEmr) {
    		builder.append("&#x2713;");    		
    	}
    	builder.append("</td>\n");    		
    	
    	RegimenDrugHelper drugHelper = new RegimenDrugHelper();
    	boolean isABC_3TC_NVP = drugHelper.isABC_3TC_NVP(drugOrders);
    	boolean isABC_3TC_EFV = drugHelper.isABC_3TC_EFV(drugOrders);
    	boolean isAZT_3TC_NVP = drugHelper.isAZT_3TC_NVP(drugOrders);
    	boolean isAZT_3TC_EFV = drugHelper.isAZT_3TC_EFV(drugOrders);
    	boolean isD4T_3TC_NVP = drugHelper.isD4T_3TC_NVP(drugOrders);
		boolean isD4T_3TC_EFV = drugHelper.isD4T_3TC_EFV(drugOrders);
		boolean isABC_3TC_LPVr = drugHelper.isABC_3TC_LPVr(drugOrders);
		boolean isAZT_3TC_LPVr = drugHelper.isAZT_3TC_LPVr(drugOrders);
		boolean isD4T_3TC_LPVr = drugHelper.isD4T_3TC_LPVr(drugOrders);
		
		boolean isOther = drugRegimenInEmr && !(isABC_3TC_NVP || isABC_3TC_EFV || isAZT_3TC_NVP ||
				isAZT_3TC_EFV || isD4T_3TC_NVP || isD4T_3TC_EFV || isABC_3TC_LPVr || isAZT_3TC_LPVr || isD4T_3TC_LPVr);
		

		builder.append("<td>\n");
    	if(arvRowNum == 0) {
    		builder.append("<span class=\"value-label\">TRAITEMENT INITIAL: </span>\n");
		if (drugRegimenInEmr)
    			builder.append(" <span class=\"value-data\">" + formatDate(FormatDate_General,changeDate) +  "</span>");
    	    builder.append("<br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isABC_3TC_NVP, "ABC+3TC+NVP")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isABC_3TC_EFV, "ABC+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_NVP, "AZT+3TC+NVP")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_EFV, "AZT+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isD4T_3TC_NVP, "D4T+3TC+NVP")).append("\n");
    		builder.append("<br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isD4T_3TC_EFV, "D4T+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isABC_3TC_LPVr, "ABC+3TC+LPV/r")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_LPVr, "AZT+3TC+LPV/r")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isD4T_3TC_LPVr, "D4T+3TC+LPV/r")).append("\n");
    		builder.append("<br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isOther, "Autre (a specifier et inclure la dose): ")).append("\n");
    		if(drugRegimenInEmr) {
	    		builder.append("<span class=\"value-data\">").append(UIHelper.formatRegimenDisplaySummaryPedi(drugOrders)).append("</span>");
    		}
    	} else {
    		builder.append("<span class=\"value-label\">");
    		builder.append("CHANGEMENT DU TRAITEMENT (").append(arvRowNum).append(") &nbsp;&nbsp;&nbsp;Date ");
    		builder.append("</span>\n<span class=\"value-date\">");
        	if(drugOrders == null || drugOrders.size() == 0) {
        		builder.append(DateTextPlaceHolder);    		
        	} else {
        		builder.append(formatDate(FormatDate_General, changeDate));
        	}
    		builder.append("</span><br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isABC_3TC_NVP, "ABC+3TC+NVP")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isABC_3TC_EFV, "ABC+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_NVP, "AZT+3TC+NVP")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_EFV, "AZT+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isD4T_3TC_NVP, "D4T+3TC+NVP")).append("\n");
    		builder.append("<br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isD4T_3TC_EFV, "D4T+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isABC_3TC_LPVr, "ABC+3TC+LPV/r")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_LPVr, "AZT+3TC+LPV/r")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isD4T_3TC_LPVr, "D4T+3TC+LPV/r")).append("\n");
    		builder.append("<br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isOther, "Autre (a specifier): ")).append("\n");
    		if(drugRegimenInEmr) {
	    		builder.append("<span class=\"value-data\">").append(UIHelper.formatRegimenDisplaySummaryPedi(drugOrders)).append("</span>");
    		}
    	}    		
    	builder.append("</td>\n");

    	// Is the drug stopped info in the EMR?
    	boolean drugStoppedInEmr = (nextChangeDate != null) ? true : false;

    	builder.append("<td class=\"section-emr\">");
    	if(drugStoppedInEmr) {
    		builder.append("&#x2713;");
    	}
    	builder.append("</td>\n");    		
    	
		builder.append("<td>\n");
		builder.append("<span class=\"value-label\">Date d'arret </span>");
		builder.append("\n<span class=\"value-date\">");
    	if(drugStoppedInEmr) {
    		builder.append(formatDate(FormatDate_General, nextChangeDate));
    	} else {
    		builder.append(DateTextPlaceHolder);    		
    	}
    	
    	boolean isRegimenFailure = drugHelper.isRegimenFailure(drugOrders);
    	boolean isINTERACTION_WITH_TUBERCULOSIS_DRUG = drugHelper.isINTERACTION_WITH_TUBERCULOSIS_DRUG(drugOrders);
    	boolean isToxicity = drugHelper.isToxicity(drugOrders);
    	boolean isPATIENT_PREGNANT = drugHelper.isPATIENT_PREGNANT(drugOrders);
    	boolean isPATIENT_DEFAULTED = drugHelper.isPATIENT_DEFAULTED(drugOrders);
    	boolean isDRUG_UNAVAILABLE = drugHelper.isDRUG_UNAVAILABLE(drugOrders);
    	boolean isPATIENT_DIED = drugHelper.isPATIENT_DIED(drugOrders);
		boolean isOtherStopReason = drugStoppedInEmr && !(isRegimenFailure ||
				isINTERACTION_WITH_TUBERCULOSIS_DRUG || isToxicity || isPATIENT_PREGNANT ||
				isPATIENT_DEFAULTED || isDRUG_UNAVAILABLE || isPATIENT_DIED);
    	
		builder.append("</span><span class=\"value-label\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; RAISON: </span><br/>\n");
		builder.append(UIHelper.getCheckBoxWidget(isRegimenFailure, "Echec")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isINTERACTION_WITH_TUBERCULOSIS_DRUG, "Interaction avec les anti_TB")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isToxicity, "Toxicite")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isPATIENT_PREGNANT, "Grossesse")).append("\n");
		builder.append("<br/>\n");
		builder.append(UIHelper.getCheckBoxWidget(isPATIENT_DEFAULTED, "Abandonne")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isDRUG_UNAVAILABLE, "Rupture de stock")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isPATIENT_DIED, "deces")).append("\n");
		builder.append("<br/>\n");
		builder.append(UIHelper.getCheckBoxWidget(isOtherStopReason, "Autre: ")).append("\n");
    	if(drugStoppedInEmr) {
		builder.append("<span class=\"value-data\">");
    		int i = 0;
    		//SUPER HACK: old regimens have to run out, so we show discontinue reason for every drug with a stop date before nextChangeDate + 7 days
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(nextChangeDate);
    		cal.add(Calendar.DAY_OF_MONTH, 7);
    		Date maxEndDate = cal.getTime();
    		
    		Set<String> reasons = new HashSet<String>();
    		for (DrugOrder dor :drugOrders){
    			if (dor.getDiscontinuedReason() != null 
    					 && (((dor.getDiscontinuedDate() != null && dor.getDiscontinuedDate().before(maxEndDate)) 
    							 ||   (dor.getAutoExpireDate() != null && dor.getAutoExpireDate().before(maxEndDate))))){
    				   
    				   reasons.add(dor.getDiscontinuedReason().getBestName(Context.getLocale()).getName());
    				   
    			}    				
    		}
    		for (String reason:reasons){
    			if (i > 0)
					   builder.append(", ");
				   builder.append(reason);
				   i++;
    		}
    		builder.append("</span>");    	
	}
    	builder.append("</td>\n");
    	
    	builder.append("</tr>\n");
    	arvRowNum++;
    }

	public List<ProphylaxisMapping> getProphylaxisEpisodes(){
    	Set<Concept> cSet = new HashSet<Concept>();
    	cSet.add(Context.getConceptService().getConcept(ConceptDictionary.DRUG_COTRIMOXAZOLE));
    	cSet.add(Context.getConceptService().getConcept(ConceptDictionary.DRUG_FLUCONAZOLE));
    	cSet.add(Context.getConceptService().getConcept(ConceptDictionary.DRUG_DAPSONE));
    	cSet.add(Context.getConceptService().getConcept(ConceptDictionary.DRUG_ISONIAZID));
    	List<DrugOrder> tmp = this.getAllPatientDrugOrdersByConceptList(cSet);
    	
    	List<ProphylaxisMapping> ret = new ArrayList<ProphylaxisMapping>();
    	for (DrugOrder dor : tmp){
    		ret.add(new ProphylaxisMapping(dor));
    		
    		//to easily exclude these drugOrders in the 'other drug orders' category
    		usedDrugOrderIds.add(dor.getOrderId());
    	}
    	//now add 3 blanks
    	ret.add(new ProphylaxisMapping());
    	ret.add(new ProphylaxisMapping());
    	ret.add(new ProphylaxisMapping());
    	return ret;
    }
    
    public Date getPositiveDiagnosisDate()
	{
		try {
			return (Date) getFirstObs(ConceptDictionary.HIV_DIAGNOSIS_DATE);
		} catch (Exception e) {
			log.error("Failed to retrieve positive diagnosis date", e);
		}
		return null;
	}
    
    public WHOMapping getWhoMapping()
    {
    	if(whoMapping == null)
    	{
    		whoMapping = new WHOMapping();
    	}
    	
    	return whoMapping;
    }
    
    public int getAllergyFormId()
    {
    	return ConceptDictionary.PEDI_ALLERGY_FORM;
    }
    
    public int getHospitalisationFormId()
    {
    	return ConceptDictionary.PEDI_HOSPITALISATION_FORM;
    }
    
    public int getOppInfectionFormId()
    {
    	return ConceptDictionary.PEDI_OI_FORM;
    }
    
    public int getProblemFormId()
    {
    	return ConceptDictionary.PEDI_PROBLEM_FORM;
    }
    
    public int getVisitFormId()
    {
    	return ConceptDictionary.PEDI_VISIT_FORM;
    }
    
    public int getLabFormId()
    {
    	return ConceptDictionary.PEDI_LAB_FORM;
    }
    
    public int getImageFormId()
    {
    	return ConceptDictionary.PEDI_IMAGE_FORM;
    }
    public boolean getIsPedi(){
    	return true;
    }
    public boolean getIsAdult(){
    	return false;
    }
}
