package org.openmrs.module.rwandaadulthivflowsheet.mapper;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.ConceptSet;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.rwandaadulthivflowsheet.impl.pih.ConceptDictionary;

public class VisitGroup {
	
	private List<Visit> visits;
	
	public VisitGroup(List<Visit> row) {
		this.visits = row;
	}
	
	public boolean isEmr() {
		return visits.size() > 0;
	}
	
	public Date getDate() {
		if(isEmr())
			return visits.iterator().next().getDate();

		return null;
	}
	
	public Obs getWeight() {
		for(Visit visit : visits) {
			Obs obs = visit.getWeight();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public Obs getFunctionalAbilityOfThePatient() {
		for(Visit visit : visits) {
			Obs obs = visit.getFunctionalAbilityOfThePatient();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public Obs getOI() {
		for(Visit visit : visits) {
			Obs obs = visit.getOI();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public Obs getSTI() {
		for(Visit visit : visits) {
			Obs obs = visit.getSTI();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public Obs getTBScreening() {
		for(Visit visit : visits) {
			Obs obs = visit.getTBScreening();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public boolean isTBScreeningPositive() {
		Obs obs = getTBScreening();
		if(obs == null)
			return false;
		
		Concept concept = obs.getValueCoded();
		if(concept != null && concept.getId() == ConceptDictionary.POSITIVE)
			return true;
		
		return false;
	}
	
	public boolean isTBScreeningNegative() {
		Obs obs = getTBScreening();
		if(obs == null)
			return false;
		
		Concept concept = obs.getValueCoded();
		if(concept != null && concept.getId() == ConceptDictionary.NEGATIVE)
			return true;
		
		return false;
	}
	
	public Obs getFamilyPlanning() {
		for(Visit visit : visits) {
			Obs obs = visit.getFamilyPlanning();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public String getFamilyPlanningString(){
		Obs o = getFamilyPlanning();
		if (o != null && o.getValueCoded() != null){
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.ABSTINENCE))
				return "A";
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.CONDOMS))
				return "C";
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.NATURAL_FAMILY_PLANNING))
				return "PF"; 
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.NONE))
				return "R"; 
		}
		return "";
	}
	
	public Obs getPregnancyStatus() {
		for(Visit visit : visits) {
			Obs obs = visit.getPregnancyStatus();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public boolean isPregnancyStatusYes() {
		Obs obs = getPregnancyStatus();
		if(obs == null)
			return false;
		
		Concept concept = obs.getValueCoded();
		if(concept != null && concept.getId() == ConceptDictionary.YES)
			return true;
		
		return false;
	}
	
	public boolean isPregnancyStatusNo() {
		Obs obs = getPregnancyStatus();
		if(obs == null)
			return false;
		
		Concept concept = obs.getValueCoded();
		if(concept != null && concept.getId() == ConceptDictionary.NO)
			return true;
		
		return false;
	}
	
	public Obs getNumberOfMissedDosesInThePastMonth() {
		for(Visit visit : visits) {
			Obs obs = visit.getNumberOfMissedDosesInThePastMonth();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public String getNumberOfMissedDosesInThePastMonthString(){
		Obs o = getNumberOfMissedDosesInThePastMonth();
		if (o != null && o.getValueNumeric() != null){
			if (o.getValueNumeric() <= 3)
				return "Tres bon";
			if (o.getValueNumeric() > 3 && o.getValueNumeric() <=8)
				return "Bon";
			if (o.getValueNumeric() >=9)
				return "Faible";
		}
		return "";
	}
	
	public Obs getCreat() {
		for(Visit visit : visits) {
			Obs obs = visit.getCreat();
			if(obs != null)
				return obs;
		}
		return null;
	}
	
	public Obs getReasonForPoorAdherenceToAntiRetoroviralTherapy() {
		for(Visit visit : visits) {
			Obs obs = visit.getReasonForPoorAdherenceToAntiRetoroviralTherapy();
			if(obs != null)
				return obs;
		}
		return null;
	}
		
	public Encounter getEncounter() {
		if(visits == null || visits.size() == 0)
			return null;
		
		for(Visit obs : visits) {
			if(obs.getEncounter() != null)
				return obs.getEncounter();
		}
		
		return null;
	}
	
	public Obs getOpportunisticInfectionForDisplay(){
		Obs o = getOI();
		if (o != null && o.getConcept().getConceptId().equals(ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED) && o.getValueCoded() != null && ! ((
				o.getValueCoded().getConceptId().equals(ConceptDictionary.OTHER_NON_CODED)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.NONE)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_CARDIOVASCULAR_DISEASE)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_CEREBRAL_LYMPHOMA)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_DEPRESSION)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_KAPOSIS_SARCOMA)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_NEUROLOGICAL_DEFICIT)
		    )))
			return o;
		return null;
	}
	
	public boolean isOpportunisticInfectionTrue(){
		Obs o = getOI();
		if (o != null && o.getConcept().getConceptId().equals(ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED) && o.getValueCoded() != null && ! ((
				o.getValueCoded().getConceptId().equals(ConceptDictionary.OTHER_NON_CODED)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.NONE)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_CARDIOVASCULAR_DISEASE)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_CEREBRAL_LYMPHOMA)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_DEPRESSION)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_KAPOSIS_SARCOMA)
				|| o.getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_NEUROLOGICAL_DEFICIT)
		    )))
			return Boolean.TRUE;
		if (o != null && o.getConcept().getConceptId().equals(ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION) && o.getValueAsBoolean() != null && o.getValueAsBoolean().equals(true))
			return Boolean.TRUE;
		return false;
	}
	
	public boolean isOpportunisticInfectionFalse(){
		Obs o = getOI();
		if (o != null && o.getValueCoded() != null && o.getValueCoded().getConceptId().equals(ConceptDictionary.NONE))
			return Boolean.TRUE;
		if (o != null && o.getConcept().getConceptId().equals(ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION) && o.getValueAsBoolean() != null && o.getValueAsBoolean().equals(false))
			return Boolean.TRUE;
		return false;
	}
	
	public boolean isStiTrue(){
		Obs o = getOI();
		if (o != null &&  isOpportunisticInfectionTrue() && o.getValueCoded() != null && getOIsThatAreSTIs().contains(o.getValueCoded().getConceptId()))
			return true;
		return false;
	}
	
	public boolean isStiFalse(){
			//pass -- no point in putting any logic in here
		return false;
	}
	
	
	
	public String getFunctionalAbilityOfThePatientString() {
		Obs o =  getFunctionalAbilityOfThePatient();
		
		if (o != null && o.getValueCoded() != null){
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.NORMAL))
				return "A";
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.BEDRIDDEN))
				return "C";
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.ASSISTANCE_REQUIRED))
				return "B";
		}
		return "";
	}
	
	public static Set<Integer> getOIsThatAreSTIs(){
		Set<Integer> ret = new HashSet<Integer>();
		Concept c = Context.getConceptService().getConcept(ConceptDictionary.OIS_THAT_ARE_STIS);
		for (ConceptSet cs : c.getConceptSets()){
			ret.add(cs.getConcept().getConceptId());
		}
		return ret;
	}


}