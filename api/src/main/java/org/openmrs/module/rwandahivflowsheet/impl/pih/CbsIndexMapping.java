package org.openmrs.module.rwandahivflowsheet.impl.pih;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.openmrs.Concept;
import org.openmrs.ConceptAnswer;
import org.openmrs.ConceptSet;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;

import org.openmrs.module.rwandahivflowsheet.mapper.*;

public class CbsIndexMapping extends ObsMapping implements Comparable<CbsIndexMapping>, CbsIndex {
	
	private Date obsDate = null;
	
	public CbsIndexMapping(Obs obs) {
		super(obs);
	}
	
	
	public Date getContactCodeDate(){
		if(!isEmr())
			return null;
		return getObs().getObsDatetime();
		
	}
	
//	public Date getObsDate() {
//
//		if(obsDate != null)
//		{
//			return obsDate;
//		}
//		return super.getObsDate();
//	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.CbsIndexMapper#getbsContactCode()
	 */
	private Obs getObsOfType(Integer conceptId) {
		if(getObs() == null)
			return null;

		if(getObs().getConcept() != null && getObs().getConcept().getConceptId().equals(conceptId)) {
			return getObs();
		}

		return null;
	}

	@Override
	public Obs getCbsEnrollementDate(){
		return getObsOfType( ConceptDictionary.CBS_ENROLLMENT_DATE );
	}

	@Override
	public Obs getCbsContactCode(){
		return getObsOfType( ConceptDictionary.CBS_CONTACT_CODE );
	}

	@Override
	public Obs getCbsContactAge() {
		return getObsOfType(ConceptDictionary.CBS_CONTACT_AGE);
	}

	@Override
	public int compareTo(CbsIndexMapping obj) {
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