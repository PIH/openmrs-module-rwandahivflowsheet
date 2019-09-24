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

public class CbsResultMapping extends ObsMapping implements Comparable<CbsResultMapping>, CbsResult {
	
	private Date obsDate = null;
	
	public CbsResultMapping(Obs obs) {
		super(obs);
	}
	
	
	public Date getRecencyAssayResultsDate(){
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
	public Obs getRecencyAssayResults(){
		return getObsOfType( ConceptDictionary.RECENCY_ASSAY_RESULTS );
	}

	@Override
	public Obs getRecencyTestDate() {
		return getObsOfType(ConceptDictionary.RECENCY_TEST_DATE);
	}

	@Override
	public int compareTo(CbsResultMapping obj) {
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

