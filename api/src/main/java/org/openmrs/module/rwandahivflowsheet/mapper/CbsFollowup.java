package org.openmrs.module.rwandahivflowsheet.mapper;

import org.openmrs.Concept;
import org.openmrs.Obs;

public interface CbsFollowup extends BaseObs {

	public abstract Obs getCbsDemographicChange();

	public abstract Obs getRiskFactorChange();

}