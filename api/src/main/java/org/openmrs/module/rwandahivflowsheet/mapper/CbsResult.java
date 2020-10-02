package org.openmrs.module.rwandahivflowsheet.mapper;

import org.openmrs.Concept;
import org.openmrs.Obs;

public interface CbsResult extends BaseObs {

	public abstract Obs getRecencyAssayResults();

	public abstract Obs getRecencyTestDate();
        
	public abstract Obs getCbsFinalRitaInconclusive();//CBS_FINAL_RITA_INCONCLUSIVE
	public abstract Obs getCbsFinalRitaResult();//CBS_FINAL_RITA_RECENCY_RESULT

}