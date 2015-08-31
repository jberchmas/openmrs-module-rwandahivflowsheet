package org.openmrs.module.rwandaadulthivflowsheet.web.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.openmrs.Concept;
import org.openmrs.DrugOrder;
import org.openmrs.Obs;
import org.openmrs.Patient;
import org.openmrs.PatientIdentifier;
import org.openmrs.PatientProgram;
import org.openmrs.PatientState;
import org.openmrs.Program;
import org.openmrs.api.context.Context;
import org.openmrs.module.rwandaadulthivflowsheet.impl.pih.AdultHIVFlowsheetObsMapper;
import org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary;
import org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ProphylaxisMapping;
import org.openmrs.module.rwandaadulthivflowsheet.impl.pih.TbEpisodeMapping;
import org.openmrs.module.rwandaadulthivflowsheet.regimen.RegimenDrugHelper;
import org.openmrs.module.rwandaadulthivflowsheet.web.UIHelper;

public class AdultHivFlowsheetFormData extends FormDataModel {
	
	//private static int PatientIdentiferId_Tracnet; //4
	//private static int ProgramId_AdultHIVProgram;  //3
//	private static final int ProgramId_TBProgram = 4;
	public static final String FormatDate_General = "dd/MM/yyyy";
	public static final String DateTextPlaceHolder = "____/____/______";
	public List<Integer> usedDrugOrderIds = new ArrayList<Integer>();
	
	private AdultHIVFlowsheetObsMapper obsMapper;

	public AdultHivFlowsheetFormData(Patient p) {
//		this();
		setPatient(p);
		obsMapper = new AdultHIVFlowsheetObsMapper(p);
	}
	
/*	public AdultHivFlowsheetFormData() {
	}
*/	
	public AdultHIVFlowsheetObsMapper getObsMapper() {
		return obsMapper;
	}

	public String getTracnetId() {
		PatientIdentifier identifier = getPatient().getPatientIdentifier(ConceptDictionary.PatientIdentiferId_Tracnet);
		if(identifier != null)
			return identifier.getIdentifier();
		
		return "";
	}
	

	
		
	public String getArvTreatmentGroup(){
		PatientProgram pp = getAdultHIVProgram();
		if (pp != null){
			for (PatientState ps : pp.getStates()){
				if (ps.getState().getProgramWorkflow().getConcept().getConceptId().equals(ConceptDictionary.TREATMENT_GROUP))
					return ps.getState().getConcept().getBestName(Context.getLocale()).getName();
			}
		}
		return "";
	}
	
	public String getHealthCenter() {
		try{
			return getPersonAttribute("Health Center", "Location", "locationId", "name", false).toString();
		} catch (Exception ex){
			return "";
		}
	}
	
	
	public PatientProgram getAdultHIVProgram() {
		Program hivProgram = Context.getProgramWorkflowService().getProgram(ConceptDictionary.ProgramId_AdultHIVProgram);
		if (hivProgram == null)
			throw new RuntimeException("Please configure the mapping in the global property for the hiv program.  The current value resolves to " + ConceptDictionary.ProgramId_AdultHIVProgram);
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
	
	public Date getArvStartDate() {
		return getEarliestDrugStart(ConceptDictionary.ANTIRETROVIRAL_DRUGS);
	}
	
	public List<DrugOrder> getTbDrugOrders() {
		//are these already chronological asc? yes, they should be according to patientSetService
		List<DrugOrder> tbDrugOrders = getDrugOrders(ConceptDictionary.TUBERCULOSIS_TREATMENT_DRUGS);
		
		//moved to getTuberculosisEpisodes
//    	int existingCount = tbDrugOrders.size();
//    	for(int index = existingCount; index < Math.max(2, existingCount + 1); index++)
//    		tbDrugOrders.add(new DrugOrder());

		return tbDrugOrders;
	}
		
	//returns the tb episodes:
	public List<TbEpisodeMapping> getTuberculosisEpisodes(){
		List<TbEpisodeMapping> episodes = new ArrayList<TbEpisodeMapping>();
		//load the list of all TB-related drug orders:		
		List<DrugOrder> tbOrders = getTbDrugOrders();
		
		//keeps track of already assigned drugOrders
		List<Integer> usedList = new ArrayList<Integer>();
		
		//first pass:  build full episodes
		//NOTE:  these are ascending.
		for (DrugOrder dor : tbOrders){
			if (!usedList.contains(dor.getOrderId()) && (dor.getConcept().getConceptId().equals(ConceptDictionary.TB_DRUG_RHEZ)
					|| dor.getConcept().getConceptId().equals(ConceptDictionary.TB_DRUG_RHZ)
							|| dor.getConcept().getConceptId().equals(ConceptDictionary.TB_DRUG_RHE))){
				//found a first line regimen :  RHEZ, RHE, or RHZ	
					TbEpisodeMapping tem = new TbEpisodeMapping();
					tem.getTbDrugOrders().add(dor);
					usedList.add(dor.getOrderId());
					Date startDate = dor.getStartDate();
					Calendar cal = Calendar.getInstance();
					cal.setTime(startDate);
					cal.add(Calendar.DAY_OF_MONTH, Integer.valueOf((365/2 + 30)));
					Date endDate = cal.getTime();
					System.out.println("Adult HIV Flowsheet Date Range Check.  Regimen Start Date " + startDate + " Regimen End Date " + endDate);
					for (DrugOrder dorInner : tbOrders){
						//found a drug with equal start date or a start date within next 7 months:
						//add this to drugOrderList in the same tb episode
						if (!usedList.contains(dorInner.getOrderId()) && !dorInner.getConcept().getConceptId().equals(ConceptDictionary.TB_DRUG_AMOX_CLAV) 
								&& dorInner.getStartDate().getTime() >= startDate.getTime() 
								&& dorInner.getStartDate().getTime() <= endDate.getTime()){
							   //special handling for RHEZ, just in case there was data entry error -- this will always be initial phase
								if (dorInner.getConcept().getConceptId().equals(ConceptDictionary.TB_DRUG_RHEZ) && dorInner.getStartDate().equals(dor.getStartDate())){
									//this will always be initial phase
									tem.getTbDrugOrders().add(0, dorInner);
									usedList.add(dorInner.getOrderId());
								} else {  //throw in all drugs in the episode
			
									tem.getTbDrugOrders().add(dorInner);
									usedList.add(dorInner.getOrderId());
								}
							
						} 
					}
					episodes.add(tem);
			}
		}
		
		//now each stray drug order gets its own row
		for (DrugOrder dor : tbOrders){
			if (!usedList.contains(dor.getOrderId()) && !dor.getConcept().getConceptId().equals(ConceptDictionary.TB_DRUG_AMOX_CLAV)){
				TbEpisodeMapping tem = new TbEpisodeMapping();
				tem.getTbDrugOrders().add(dor);
				usedList.add(dor.getOrderId());
				episodes.add(tem);
			}
		}
		//reorder the episodes
		Collections.sort(episodes);
		

		List<Concept> cList = new ArrayList<Concept>();
		cList.add(Context.getConceptService().getConcept(Integer.valueOf(ConceptDictionary.TB_TREATMENT_TYPE)));
		cList.add(Context.getConceptService().getConcept(Integer.valueOf(ConceptDictionary.RESULT_OF_TB_SMEAR)));
		//load all matching obs for this patient
		List<Obs> oList = Context.getObsService().getObservations(Collections.singletonList(Context.getPersonService().getPerson(this.getPatient().getPatientId())), null, cList, null, null, null, null, null, null, null, null, false);
		for (TbEpisodeMapping tem : episodes){
			Date startDate = tem.getEpisodeDate();
			Calendar cal = Calendar.getInstance();
			cal.setTime(startDate);
			//creating a buffer around episode start date to look for obs for episdode
			cal.add(Calendar.DAY_OF_MONTH, -15);
			startDate = cal.getTime();
			cal.add(Calendar.DAY_OF_MONTH, +40);
			Date endDate = cal.getTime();
			for (Obs o : oList){
				if (o.getConcept().getConceptId().equals(ConceptDictionary.TB_TREATMENT_TYPE) &&
						o.getObsDatetime().getTime() >= startDate.getTime()  && o.getObsDatetime().getTime() <= endDate.getTime()){
					tem.setSchema(o);
					break;
				}
					
			}
			for (Obs o : oList){
				if (o.getConcept().getConceptId().equals(ConceptDictionary.RESULT_OF_TB_SMEAR) &&
						o.getObsDatetime().getTime() >= startDate.getTime()  && o.getObsDatetime().getTime() <= endDate.getTime()){
					tem.setSmearResult(o);
					break;
				}
					
			}
			
			for (Obs o :oList){
				try {
				if (o.getConcept().getConceptId().equals(ConceptDictionary.RESULT_OF_TB_SMEAR) && o.getValueCoded() != null
						&& o.getValueCoded().equals(ConceptDictionary.RESULT_OF_TB_SMEAR_NEGATIVE) && o.getObsDatetime().getTime() >= startDate.getTime() && 
						o.getObsDatetime().getTime() <= tem.getEpisodeEnd().getTime()){
						tem.setFirstNegativeSmear(o);
						break;
				}
				} catch (Exception ex){
					//pass, probably a null pointer exception
				}
			}
		}
		//add two empty items, if needed:

    		episodes.add(new TbEpisodeMapping());
    		episodes.add(new TbEpisodeMapping());
    	
    	//add to usedDrugOrderIds so that we can exclude these easily from 'other drugs'
    	for (TbEpisodeMapping tem : episodes){
    		List<DrugOrder> dors = tem.getTbDrugOrders();
    		if (dors != null){
	    		for (DrugOrder dor: dors){
	    			if (dor.getOrderId() != null)
	    				usedDrugOrderIds.add(dor.getOrderId());
	    		}
    		}
    	}	
		return episodes;
	}
	
	
	private int arvRowNum = 0;
	public String getArvRegimenHtmlTable() {
    	StringBuilder builder = new StringBuilder(4096);
    	builder.append("<table class=\"section-table\">\n");
    	builder.append("<tr>\n");
    	builder.append("<th scope=\"col\" id=\"col-arv-emr-1\">EMR</th>\n");
    	builder.append("<th scope=\"col\" id=\"col-arv-info\">TRAITEMENT ANTIRETROVIRAUX POUR TOUTE LA VIE</th>\n");
    	builder.append("<th scope=\"col\" id=\"col-arv-emr-2\">EMR</th>\n");
    	builder.append("<th scope=\"col\" id=\"col-arv-stop\"></th>\n");
    	builder.append("</tr>\n");

    	Map<Date,Set<DrugOrder>> drugOrdersGroupedByStartAndEndDate = getRegimenChanges(this.getDrugOrders(ConceptDictionary.ANTIRETROVIRAL_DRUGS));
		int index = 0;
		Map.Entry<Date, Set<DrugOrder>> prevMapEntry = null;
		for (Map.Entry<Date, Set<DrugOrder>> e : drugOrdersGroupedByStartAndEndDate.entrySet()){
			if (index > 0 && prevMapEntry.getValue().size() > 0)
				addARVRegimenTableRow(builder,  new ArrayList<DrugOrder>(prevMapEntry.getValue()), prevMapEntry.getKey(), e.getKey());
			if (index == drugOrdersGroupedByStartAndEndDate.size() -1 && e.getValue().size() > 0)
				addARVRegimenTableRow(builder,  new ArrayList<DrugOrder>(e.getValue()), e.getKey(), null);
			index ++;
			prevMapEntry = e;
			
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
		builder.append("</table>\n");
		
		return builder.toString();
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
		boolean isD4T_3TC_NVP = drugHelper.isD4T_3TC_NVP(drugOrders);
		boolean isD4T_3TC_EFV = drugHelper.isD4T_3TC_EFV(drugOrders);
		boolean isAZT_3TC_NVP = drugHelper.isAZT_3TC_NVP(drugOrders);
		boolean isAZT_3TC_EFV = drugHelper.isAZT_3TC_EFV(drugOrders);
		boolean isTDF_3TC_NVP = drugHelper.isTDF_3TC_NVP(drugOrders);
		boolean isTDF_3TC_EFV = drugHelper.isTDF_3TC_EFV(drugOrders);
		boolean isTDF_3TC_Kaletra = drugHelper.isTDF_3TC_Kaletra(drugOrders);
		boolean isABC_3TC_Kaletra = drugHelper.isABC_3TC_Kaletra(drugOrders);
		boolean isDDI_ABC_Kaletra = drugHelper.isDDI_ABC_Kaletra(drugOrders);
		boolean isAZT_3TC_Kaletra = drugHelper.isAZT_3TC_Kaletra(drugOrders);
		
		boolean isOther1 = drugRegimenInEmr && !(isD4T_3TC_NVP || isD4T_3TC_EFV || isAZT_3TC_NVP ||
				isAZT_3TC_EFV || isTDF_3TC_NVP || isTDF_3TC_EFV);
		boolean isOther2 = drugRegimenInEmr && !(isAZT_3TC_NVP || isAZT_3TC_EFV || 
				isTDF_3TC_Kaletra || isABC_3TC_Kaletra || isDDI_ABC_Kaletra || isAZT_3TC_Kaletra);

		builder.append("<td>\n");
    	if(arvRowNum == 0) {
    		builder.append("<span class=\"value-label\">TRAITEMENT INITIAL: </span>"); 
    		if (drugRegimenInEmr)
    			builder.append(" <span class=\"value-data\">(" + formatDate(FormatDate_General,changeDate) +  ")</span>");
    	    builder.append("<br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isD4T_3TC_NVP, "D4T+3TC+NVP")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isD4T_3TC_EFV, "D4T+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_NVP, "AZT+3TC+NVP")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_EFV, "AZT+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isTDF_3TC_NVP, "TDF+3TC+NVP")).append("\n");
    		builder.append("<br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isTDF_3TC_EFV, "TDF+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isOther1, "Autre (á spécifier et inclure la dose): ")).append("\n");
    		if(drugRegimenInEmr) {
	    		builder.append("<span class=\"value-data\">").append(UIHelper.formatRegimenDisplaySummary(drugOrders)).append("</span>");
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
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_NVP, "AZT+3TC+NVP")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_EFV, "AZT+3TC+EFV")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isTDF_3TC_Kaletra, "TDF+3TC+Kaletra")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isABC_3TC_Kaletra, "ABC+3TC+Kaletra")).append("\n");
    		builder.append("<br/>\n");
    		builder.append(UIHelper.getCheckBoxWidget(isDDI_ABC_Kaletra, "DDI+ABC+Kaletra")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isAZT_3TC_Kaletra, "AZT+3TC+Kaletra")).append("\n");
    		builder.append(UIHelper.getCheckBoxWidget(isOther2, "Autre: ")).append("\n");
    		if(drugRegimenInEmr) {
	    		builder.append("<span class=\"value-data\">").append(UIHelper.formatRegimenDisplaySummary(drugOrders)).append("</span>");
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
		builder.append("<span class=\"value-label\">Date d’arrêt </span>");
		builder.append("\n<span class=\"value-date\">");
    	if(drugStoppedInEmr ) {
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
		builder.append(UIHelper.getCheckBoxWidget(isRegimenFailure, "Échec")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isINTERACTION_WITH_TUBERCULOSIS_DRUG, "Interaction avec médicaments TB")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isToxicity, "Toxicité")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isPATIENT_PREGNANT, "Grossesse")).append("\n");
		builder.append("<br/>\n");
		builder.append(UIHelper.getCheckBoxWidget(isPATIENT_DEFAULTED, "Abandonné")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isDRUG_UNAVAILABLE, "Rupture de stock")).append("\n");
		builder.append(UIHelper.getCheckBoxWidget(isPATIENT_DIED, "Décès")).append("\n");
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
		//<lookup complexExpression="#if($fn.getDrugOrders(''))#set($drugOrders = $fn.getDrugOrders('ANTIRETROVIRAL DRUGS'))#foreach($drugOrder in $drugOrders) &lt;tr&gt; &lt;td align=&quot;center&quot;&gt; &#x2713; &lt;/td&gt;     &lt;td&gt; Commencer: $!{fn.formatDate('$FormatDate_General', $drugOrder.getStartDate())} &lt;br/&gt;$!{drugOrder.getDrug().getName()} $!{drugOrder.getDose()}$!{drugOrder.getUnits().replaceAll('tab\(s\)', 'cé')} $!{drugOrder.getFrequency().replaceAll(' x 7 days/week', '').replaceAll('day', 'j')}&lt;/td&gt; #if($drugOrder.getDiscontinued())&lt;td align=&quot;center&quot;&gt; &#x2713; &lt;/td&gt;  &lt;td&gt; Arrêt: $!{fn.formatDate('$FormatDate_General', $drugOrder.getDiscontinuedDate())} &lt;br/&gt;Raison: $!{drugOrder.getDiscontinuedReason().getName()} &lt;/td&gt; #else &lt;td&gt;  &lt;/td&gt;  &lt;td&gt; Arrêt: $DateTextPlaceHolder &lt;br/&gt;Raison:  &lt;/td&gt; #end &lt;/tr&gt;#end#end" />
    }

    
    /**
     * we need this to return every hiv regien change date, and all drugOrders active on that date, so that we get a reflection of the regimen
     * @param rawOrders
     * @return
     */
    Map<Date, Set<DrugOrder>> getRegimenChanges(List<DrugOrder> rawOrders){
    	Map<Date, Set<DrugOrder>> ret = new TreeMap<Date, Set<DrugOrder>>(); //TreeSet will automatically sort dates
    	
    	for (DrugOrder dor : rawOrders){
    		//drug start dates:
    		Set<DrugOrder> dorListOnStartDate = ret.get(dor.getStartDate());
    		if (dorListOnStartDate == null)
    			dorListOnStartDate = new LinkedHashSet<DrugOrder>();
    		//now check all orders to see if they're active on that date also, and add if so:
    		for (DrugOrder dorInner : rawOrders){
    			if (dorInner.isCurrent(dor.getStartDate()) && !dorListOnStartDate.contains(dorInner)) //only add each DrugOrder once.
    				dorListOnStartDate.add(dorInner);
    		}
    		if (dorListOnStartDate.size() > 0)
    			ret.put(dor.getStartDate(), dorListOnStartDate);
    		
    		Date endDate = dor.getDiscontinuedDate();
    		if (endDate == null)
    			endDate = dor.getAutoExpireDate();
    		
    		if (endDate != null){
	    		Set<DrugOrder> dorListOnEndDate = ret.get(endDate);
	    		if (dorListOnEndDate == null)
	    			dorListOnEndDate = new LinkedHashSet<DrugOrder>();
	    		//now check all orders to see if they're active on that date also, and add if so:
	    		for (DrugOrder dorInner : rawOrders){
	    			if (dorInner.isCurrent(endDate) && !dorListOnStartDate.contains(dorInner)) //only add each DrugOrder once.
	    					dorListOnEndDate.add(dorInner);
	    		}
	    		ret.put(endDate, dorListOnEndDate);
    		}
    		
    	}
    	
    	return ret;
    }
    
//	private List<List<DrugOrder>> getDrugOrdersGroupedByStartAndEndDate(String drugSetName) {
//		List<DrugOrder> drugOrders = getDrugOrders(drugSetName);
//    	
//    	// Group the drugs by start date and end date.  If they are the same
//    	// it's one row.
//    	Map<String, List<DrugOrder>> startDateToDrugOrdersMap = new HashMap<String, List<DrugOrder>>();    	
//    	for(DrugOrder drugOrder : drugOrders) {
//    		String key = getDrugOrderHashTableKey(drugOrder);
//    		List<DrugOrder> drugOrdersOnStartDate = startDateToDrugOrdersMap.get(key);
//    		if(drugOrdersOnStartDate == null) {
//    			drugOrdersOnStartDate = new ArrayList<DrugOrder>(); 
//    			startDateToDrugOrdersMap.put(key, drugOrdersOnStartDate);
//    		}
//			drugOrdersOnStartDate.add(drugOrder);
//    	}
//    	
//    	// Make sure to sort the list
//    	String[] keys = new String[startDateToDrugOrdersMap.size()];
//    	startDateToDrugOrdersMap.keySet().toArray(keys);
//    	Arrays.sort(keys);
//    	
//    	List<List<DrugOrder>> drugOrdersSorted = new ArrayList<List<DrugOrder>>(startDateToDrugOrdersMap.size());
//    	for(String key : keys) {
//    		drugOrdersSorted.add(startDateToDrugOrdersMap.get(key));
//    	}
//    	return drugOrdersSorted;
//	}
//
//    private String getDrugOrderHashTableKey(DrugOrder drugOrder) {
//		Date startDate = drugOrder.getStartDate();
//		Date endDate = drugOrder.getDiscontinuedDate();
//		String key = formatDate("yyyy-MM-dd", startDate);
//		if(drugOrder.getDiscontinued() && endDate != null) {
//			key += formatDate("-yyyy-MM-dd", endDate);
//			if(drugOrder.getDiscontinuedReason() != null)
//				key += "-" + drugOrder.getDiscontinuedReason().getConceptId();
//		}
//		return key;
//    }
    
    
    public List<ProphylaxisMapping> getProphylaxisEpisodes(){
    	Set<Concept> cSet = new HashSet<Concept>();
    	cSet.add(Context.getConceptService().getConcept(ConceptDictionary.DRUG_COTRIMOXAZOLE));
    	cSet.add(Context.getConceptService().getConcept(ConceptDictionary.DRUG_FLUCONAZOLE));
    	cSet.add(Context.getConceptService().getConcept(ConceptDictionary.DRUG_DAPSONE));
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
    
    
    //returns the model for the other medications section
    public List<DrugOrder> getOtherMedications(){
    	List<DrugOrder> ret = new ArrayList<DrugOrder>();
    	List<DrugOrder> dors = this.getAllPatientDrugOrders();
    	
    	for (DrugOrder dor : dors){
    		if (!usedDrugOrderIds.contains(dor.getOrderId())){
    			if (dor.getStartDate() == null)
	    			continue;
	    		Date endDate = dor.getDiscontinuedDate();
	    		if (endDate == null)
	    			endDate = dor.getAutoExpireDate();
	    		if (endDate == null || ((endDate != null && endDate.getTime() - dor.getStartDate().getTime() > (1000*60*60*24*30)))) // regimen that lasted more than 30 days, or there is no endDate specified
	    			ret.add(dor);
    		}
    	}
    	//add 4 extras:
    	ret.add(new DrugOrder());
    	ret.add(new DrugOrder());
    	ret.add(new DrugOrder());
    	ret.add(new DrugOrder());
    	return ret;
    }
    



}
