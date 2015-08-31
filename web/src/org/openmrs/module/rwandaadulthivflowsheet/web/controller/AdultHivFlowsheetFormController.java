package org.openmrs.module.rwandaadulthivflowsheet.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Patient;
import org.openmrs.api.PatientService;
import org.openmrs.api.context.Context;
import org.openmrs.module.rwandaadulthivflowsheet.web.MetadataHelper;
import org.openmrs.module.rwandaadulthivflowsheet.web.model.AdultHivFlowsheetFormData;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

/**
 * This controller displays a patient summary
 * 
 */
public class AdultHivFlowsheetFormController extends SimpleFormController {

    /** Logger for this class and subclasses */
    protected final Log log = LogFactory.getLog(getClass());

    @Override
    protected Map<String, Object> referenceData(HttpServletRequest request, Object obj, Errors err) throws Exception {
        Patient patient = (Patient) obj;
       //loads the big global property and sets final values for these
        MetadataHelper.setupConstants();    
        Map<String, Object> ret = new HashMap<String, Object>();
        //setup reference object
		ret.put("formData", new AdultHivFlowsheetFormData(patient));

        return ret;
    }

    @Override
    protected ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command,
            BindException exceptions) throws Exception {
        return new ModelAndView(new RedirectView(getSuccessView()));
    }

    @Override
    protected Object formBackingObject(HttpServletRequest request)
            throws Exception {

        log.debug("Entering formBackingObject");

        if (!Context.isAuthenticated())
            return new Patient();

        String patientId = request.getParameter("patientId");
        if (patientId == null)
            patientId = request.getParameter("patientId");
        log.debug("patientId: " + patientId);
        if (patientId == null)
            throw new ServletException(
                    "Integer 'patientId' is a required parameter");

        PatientService ps = Context.getPatientService();
        Patient patient = null;
        Integer id = null;

        try {
            id = Integer.valueOf(patientId);
            patient = ps.getPatient(id);
        } catch (NumberFormatException numberError) {
            log.warn("Invalid patientId supplied: '" + patientId + "'",
                    numberError);
        } catch (ObjectRetrievalFailureException noPatientEx) {
            log.warn("There is no patient with id: '" + patientId + "'",
                    noPatientEx);
        }

        if (patient == null)
            throw new ServletException("There is no patient with id: '"
                    + patientId + "'");

        return patient;
    }

        	
}
