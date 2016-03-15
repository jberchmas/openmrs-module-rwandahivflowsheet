package org.openmrs.module.rwandahivflowsheet.impl.pih;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.Obs;
import org.openmrs.module.rwandahivflowsheet.mapper.Problem;

public class ProblemMapping extends ObsMapping implements Comparable<ProblemMapping>, Problem {
	
	public Obs diagnosisOther;
	public List<Obs> diagnoses = new ArrayList<Obs>();
	
	public ProblemMapping(Obs obs) {
		super(obs);
		if(obs.getConcept() != null && ((obs.getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_CONSTRUCT) || obs.getConcept().getConceptId().equals(ConceptDictionary.PREVIOUS_DIAGNOSIS_CONSTRUCT)))) {
			for(Obs group : getObs().getGroupMembers()) {
				if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_DIAGNOSIS_OTHER)){
					diagnosisOther = group;
				}	
				if (!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_DIAGNOSIS) && group.getValueCoded() != null){
					diagnoses.add(group);
				}	
					
			}			
		} else if (obs.getConcept() != null && obs.getValueCoded() != null && obs.getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_DIAGNOSIS)) {
			diagnoses.add(obs);
		}	else if (obs.getConcept() != null && obs.getValueCoded() != null && obs.getConcept().getConceptId().equals(ConceptDictionary.PREVIOUS_DIAGNOSIS)) {
		    diagnoses.add(obs);
		}			
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ProblemMapper#getDiagnosis()
	 */
	@Override
	public Obs getDiagnosis() {
		if(!isEmr())
			return null;
		
// MLH No non-voided instances of this exist in the DB
//		if(obs.getConcept().getConceptId() == AdultHivFlowsheetFormData.ConceptId_PatientHadDiabities) {
//			if(obs.getValueCoded().getConceptId() == 1065)
//				return obs;
//		}
		
		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.CURRENT_OPPORTUNISTIC_INFECTION_OR_COMORBIDITY_CONFIRMED_OR_PRESUMED)) {
			if(getObs().getValueCoded().getConceptId().equals(ConceptDictionary.PATIENT_HAS_DIABETES) ||
					getObs().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_CARDIOVASCULAR_DISEASE)
				    ||   getObs().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_CEREBRAL_LYMPHOMA)			
				    ||   getObs().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_DEPRESSION)
				    ||   getObs().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_KAPOSIS_SARCOMA)
				    ||   getObs().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_NEUROLOGICAL_DEFICIT))
				return getObs();
		}
		
		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.PREVIOUS_DIAGNOSIS)) {
			//if(getObs().getValueCoded().getConceptId() == ConceptDictionary.DIABETES_MELLITUS)
			return getObs();
		}
		
		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_DIAGNOSIS)) {
			return getObs();
		}

//		if(obs.getConcept().getConceptId() == AdultHivFlowsheetFormData.ConceptId_Diabeties)
//			return obs;
			
		if(getObs().getGroupMembers() == null)
			return null;
		
		
		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_CONSTRUCT) || getObs().getConcept().getConceptId().equals(ConceptDictionary.PREVIOUS_DIAGNOSIS_CONSTRUCT)) {
			for(Obs group : getObs().getGroupMembers()) {
				if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_DIAGNOSIS)  && group.getValueCoded().getConceptId().equals(ConceptDictionary.OTHER_NON_CODED)){
					if (getDiagnosisOther() != null){
						return getDiagnosisOther();
					}	
				}	
			}			
		}
		
		Obs obsValue = null;
		for(Obs group : getObs().getGroupMembers()) {
			if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.PREVIOUS_DIAGNOSIS)) {
				//if(group.getValueCoded().getConceptId() == ConceptDictionary.DIABETES_MELLITUS)
					obsValue = group;
			}
			else if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_DIAGNOSIS)) {
				obsValue = group;
			}
		}
		
		
		return obsValue;
	}
	
	//HACK
	public boolean getShowOther(){
		if (getDiagnosis() != null && getDiagnosis().getValueCoded() != null &&
				((getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.OTHER_NON_CODED)
				|| 	getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.ASTHMA)
				|| getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.DIABETES)
				|| getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.EPILEPSY)
				|| getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.HEART_FAILURE)
				|| getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.RENAL_FAILURE)		
				)))
			return false;
		return true;
	}
	
	
	public Obs getDiagnosisOther() {
		return diagnosisOther;
	}

	public void setDiagnosisOther(Obs diagnosisOther) {
		this.diagnosisOther = diagnosisOther;
	}

	public boolean getDoNotShow(){
		if (getDiagnosis() != null && getDiagnosis().getValueCoded() != null && !((
				getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_CARDIOVASCULAR_DISEASE)
			    ||   getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_CEREBRAL_LYMPHOMA)			
			    ||   getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_DEPRESSION)
			    ||   getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_KAPOSIS_SARCOMA)
			    ||   getDiagnosis().getValueCoded().getConceptId().equals(ConceptDictionary.CURRENT_OI_NEUROLOGICAL_DEFICIT)
			    ||    getDiagnosis().getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_DIAGNOSIS_OTHER)
			    ||   getDiagnosis().getConcept().getConceptId().equals(ConceptDictionary.CHRONIC_CARE_DIAGNOSIS)
			    ||   getDiagnosis().getConcept().getConceptId().equals(ConceptDictionary.PREVIOUS_DIAGNOSIS)
				)))
			return true;
		return false;
	}

	
	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ProblemMapper#getComments()
	 */
	@Override
	public String getComments() {
		if(!isEmr())
			return "";
		
		if(getObs().getGroupMembers() == null)
			return null;
		
		for(Obs group : getObs().getGroupMembers()) {
			if (!group.isVoided()){
				if(group.getConcept().getConceptId().equals(ConceptDictionary.CLINICAL_IMPRESSION_COMMENTS))
					return group.getValueAsString(null);
				if(group.getConcept().getConceptId().equals(ConceptDictionary.ASSESSMENT_COMMENTS))
					return group.getValueAsString(null);
			}
		}
		return "";
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ProblemMapper#isAsthma()
	 */
	@Override
	public boolean isAsthma() {
		Obs obs = getDiagnosis();
		if(obs == null)
			return false;
		for (Obs o : diagnoses){
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.ASTHMA))
				return true;
		}
		if (obs.getValueCoded() == null)
			return false;
		return obs.getValueCoded().getConceptId().equals(ConceptDictionary.ASTHMA);
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ProblemMapper#isDiabetes()
	 */
	@Override
	public boolean isDiabetes() {
		Obs obs = getDiagnosis();
		if(obs == null )
			return false;
		for (Obs o : diagnoses){
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.DIABETES) || o.getValueCoded().getConceptId().equals(ConceptDictionary.PATIENT_HAS_DIABETES))
				return true;
		}
		if (obs.getValueCoded() == null)
			return false;
		return obs.getValueCoded().getConceptId().equals(ConceptDictionary.DIABETES) || obs.getValueCoded().getConceptId().equals(ConceptDictionary.PATIENT_HAS_DIABETES);
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ProblemMapper#isEpilepsy()
	 */
	@Override
	public boolean isEpilepsy() {
		Obs obs = getDiagnosis();
		if(obs == null)
			return false;
		for (Obs o : diagnoses){
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.EPILEPSY))
				return true;
		}
		if (obs.getValueCoded() == null)
			return false;
		return obs.getValueCoded().getConceptId().equals(ConceptDictionary.EPILEPSY);
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ProblemMapper#isHeartFailure()
	 */
	@Override
	public boolean isHeartFailure() {
		Obs obs = getDiagnosis();
		if(obs == null)
			return false;
		for (Obs o : diagnoses){
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.HEART_FAILURE)){
				return true;	
			}	
		}
		if (obs.getValueCoded() == null)
			return false;
		return obs.getValueCoded().getConceptId().equals(ConceptDictionary.HEART_FAILURE);
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ProblemMapper#isRenalFailure()
	 */
	@Override
	public boolean isRenalFailure() {
		Obs obs = getDiagnosis();
		if(obs == null)
			return false;
		for (Obs o : diagnoses){
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.RENAL_FAILURE))
				return true;
		}
		if (obs.getValueCoded() == null)
			return false;
		return obs.getValueCoded().getConceptId().equals(ConceptDictionary.RENAL_FAILURE);
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ProblemMapper#isOther()
	 */
	@Override
	public boolean isOther() {
		Obs obs = getDiagnosis();
		if(obs == null)
			return false;
		for (Obs o : diagnoses){
			if (o.getValueCoded().getConceptId().equals(ConceptDictionary.OTHER_NON_CODED))
				return true;
		}
		if (obs.getValueCoded() == null)
			return false;
		return !(isAsthma() || isDiabetes() || isEpilepsy() || isHeartFailure() || isRenalFailure());
	}

	@Override
	public boolean isBlank() {
		return super.isBlank() || getDiagnosis() == null;		
	}
	
	public boolean getIsOther(){
		return isOther();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ProblemMapping other = (ProblemMapping) obj;
		if(getObs() == null || other.getObs() == null)
			return false;
		//commented out because if chronic, only show once
//		if(getObsDate() == null || other.getObsDate() == null || !getObsDate().equals(other.getObsDate()))
//			return false;

		if(areObsDifferentValue(getDiagnosis(), other.getDiagnosis()))
			return false;

//		if(getComments() == null ^ other.getComments() == null)
//			return false;
//		if((getComments() != null && other.getComments() != null) && !getComments().equals(other.getComments()))
//			return false;
	
		return true;
	}

	@Override
	public int compareTo(ProblemMapping obj) {
		if (this == obj)
			return 0;
		if (obj == null)
			return -1;
		if(getObsDate() == null && obj.getObsDate() == null)
			return 0;
		if(getObsDate() == null)
			return 1;
		return getObsDate().compareTo(obj.getObsDate());
	}

}