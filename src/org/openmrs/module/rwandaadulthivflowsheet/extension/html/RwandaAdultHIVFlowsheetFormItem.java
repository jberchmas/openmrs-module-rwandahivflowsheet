package org.openmrs.module.rwandaadulthivflowsheet.extension.html;

import org.openmrs.module.web.extension.LinkExt;


public class RwandaAdultHIVFlowsheetFormItem extends LinkExt {
	
	
	/**
	 * @return the message code for this link
	 */
	@Override
	public String getLabel() {
		return "Adult HIV Flowsheet";
	}
	
	/**
	 * @return the url to go to
	 */
	@Override
	public String getUrl() {
		return "module/rwandaadulthivflowsheet/adulthivflowsheet.form";
	}
	
	/**
     * Returns the required privilege in order to see this section.  Can be a 
     * comma delimited list of privileges.  
     * If the default empty string is returned, only an authenticated 
     * user is required
     * 
     * @return Privilege string
     */
	@Override
	public String getRequiredPrivilege() {
        return "View Patients";
    }
	
}
