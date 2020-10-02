package org.openmrs.module.rwandahivflowsheet.mapper;

import org.openmrs.Concept;
import org.openmrs.Obs;

public interface CbsContact extends BaseObs {

	public abstract Obs getcbsContactCode();//CBS_CONTACT_CODE

	public abstract Obs getcbsContactAge();//CBS_CONTACT_AGE
	public abstract Obs getcbsContactgender();//CBS_CONTACT_GENDER
	public abstract Obs getcbsContactNotified();//CBS_CONTACT_NOTIFIER

}