package org.openmrs.module.rwandahivflowsheet.impl.pih;

import java.util.Date;

import org.openmrs.DrugOrder;
import org.openmrs.Order;
import org.openmrs.api.context.Context;

public class ProphylaxisMapping extends DrugOrder {
	
		DrugOrder drugOrder;
		
		public ProphylaxisMapping(DrugOrder dor){
			this.drugOrder = dor;
		}
		public ProphylaxisMapping(){}
		
		
		public DrugOrder getDrugOrder() {
			return drugOrder;
		}

		public void setDrugOrder(DrugOrder drugOrder) {
			this.drugOrder = drugOrder;
		}
		
		public boolean isToxicity(){
			if (drugOrder != null && drugOrder.getEffectiveStopDate() != null && drugOrder.getAction().equals(Order.Action.DISCONTINUE)
					&& drugOrder.getOrderReason().getConceptId().equals(ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_TOXICITY))
				return true;
			return false;
		}
		public boolean isAbandoned(){
			if (drugOrder != null && drugOrder.getEffectiveStopDate() != null && drugOrder.getAction().equals(Order.Action.DISCONTINUE)
					&& drugOrder.getOrderReason().getConceptId().equals(ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_ABANDONED))
				return true;
			return false;
		}
		public boolean isOutOfStock(){
			if (drugOrder != null && drugOrder.getEffectiveStopDate() != null && drugOrder.getAction().equals(Order.Action.DISCONTINUE)
					&& drugOrder.getOrderReason().getConceptId().equals(ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_OUT_OF_STOCK))
				return true;
			return false;
		}
		public boolean isCd4Improved(){
			if (drugOrder != null && drugOrder.getEffectiveStopDate() != null && drugOrder.getAction().equals(Order.Action.DISCONTINUE)
					&& drugOrder.getOrderReason().getConceptId().equals(ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_CD4_IMPROVEMENT))
				return true;
			return false;
		}
		public boolean isTermine(){
			if (drugOrder != null && drugOrder.getEffectiveStopDate() != null && drugOrder.getAction().equals(Order.Action.DISCONTINUE)
					&& drugOrder.getOrderReason().getConceptId().equals(ConceptDictionary.PROPHYLAXIS_REASON_FOR_STOPPING_TERMINE))
				return true;
			return false;
		}
		public boolean isReasonForStoppingOther(){
			if (drugOrder != null && drugOrder.getEffectiveStopDate() != null
					&& !isCd4Improved() && !isOutOfStock() && !isAbandoned() && !isToxicity())
				return true;
			return false;
		}
		
		public boolean getReasonForStoppingOther(){
			return isReasonForStoppingOther();
		}
		public String getDiscontinueReasonOther(){
			if (this.isReasonForStoppingOther())
				return drugOrder.getOrderReason().getBestName(Context.getLocale()).getName();
			return "";
		}
		
		public boolean isCotrimoxisole(){
			if (drugOrder != null && drugOrder.getConcept() != null 
					&& drugOrder.getConcept().getConceptId().equals(ConceptDictionary.DRUG_COTRIMOXAZOLE))
				return true;
			return false;
		}
		
		public boolean isFluconazole(){
			if (drugOrder != null && drugOrder.getConcept() != null 
					&& drugOrder.getConcept().getConceptId().equals(ConceptDictionary.DRUG_FLUCONAZOLE))
				return true;	
			return false;
		}	
		
		public boolean isIsoniazid(){
			if (drugOrder != null && drugOrder.getConcept() != null 
					&& drugOrder.getConcept().getConceptId().equals(ConceptDictionary.DRUG_ISONIAZID))
				return true;	
			return false;
		}
		
		public boolean isDapsone(){
			if (drugOrder != null && drugOrder.getConcept() != null 
					&& drugOrder.getConcept().getConceptId().equals(ConceptDictionary.DRUG_DAPSONE))
				return true;	
			return false;
			
		}
		public boolean isProphylaxisOther() {
			if (drugOrder != null && drugOrder.getConcept() != null 
					&& !isCotrimoxisole() && !isFluconazole() && !isDapsone())
				return true;
			return false;
		}
		
		public boolean getProphylaxisOther() {
			return this.isProphylaxisOther();
		}
		
		public String getProphylaxisTypeOther(){
			if (drugOrder != null && drugOrder.getConcept() != null 
					&& !isCotrimoxisole() && !isFluconazole() && !isDapsone())
				return drugOrder.getConcept().getBestName(Context.getLocale()).getName();
			return "";
		}
		
		public Date getStopDate(){
			if (drugOrder != null){
				if (drugOrder.getEffectiveStopDate() != null)
					return drugOrder.getEffectiveStopDate();
				if (drugOrder.getAutoExpireDate() != null)
					return drugOrder.getAutoExpireDate();
			}
			return null;
		}
		
		public Date getStartDate(){
			if (drugOrder != null)
				return drugOrder.getEffectiveStartDate();
			return null;
		}
	
}
