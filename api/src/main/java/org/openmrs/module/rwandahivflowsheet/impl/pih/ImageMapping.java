package org.openmrs.module.rwandahivflowsheet.impl.pih;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.openmrs.Concept;
import org.openmrs.Encounter;
import org.openmrs.Obs;
import org.openmrs.api.context.Context;
import org.openmrs.module.rwandahivflowsheet.mapper.Image;

public class ImageMapping extends ObsMapping implements Comparable<ImageMapping>, Image {
	
	public ImageMapping(Obs obs) {
		super(obs);
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ImageMapper#getDate()
	 */
	@Override
	public Date getDate() {
		
		if(getObs().getGroupMembers() != null) {
			for(Obs group : getObs().getGroupMembers()) {
				if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.DATE_OF_GENERAL_TEST))
					return group.getValueDatetime();
			}
		}
		if (getObs().getEncounter() != null && getObs().getEncounter().getEncounterDatetime() != null)
			return getObs().getEncounter().getEncounterDatetime();
		
		return getObsDate();
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ImageMapper#getImageType()
	 */
//	@Override
//	public Concept getImageType() {
//		if(!isEmr())
//			return null;
//				
//		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST)) {
//			return getObs().getConcept();
//		}
//
//		if(getObs().getValueCoded() != null && getObs().getValueCoded().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
//			return getObs().getValueCoded();
//		
//		if(getObs().getGroupMembers() == null)
//			return null;
//		
//		for(Obs group : getObs().getGroupMembers()) {
//			if (!group.isVoided()){
//				if(group.getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
//					return group.getConcept();
//				if(group.getValueCoded() != null && group.getValueCoded().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
//					return group.getValueCoded();
//				if(group.getValueCoded() != null && group.getValueCoded().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
//					return group.getValueCoded();
//			}
//		}
//		
//		Obs testsOrdered = null;
//		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.TESTS_ORDERED)) {
//			testsOrdered = getObs();
//		}
//
//		for(Obs group : getObs().getGroupMembers()) {
//			if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.TESTS_ORDERED)) {
//				testsOrdered = group;
//			}
//		}
//		
//		if(testsOrdered != null) {
//			for(Obs group : testsOrdered.getGroupMembers()) {
//				if (!group.isVoided()){
//					if(group.getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
//						return group.getValueCoded();
//					if(group.getConcept().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD))
//						return group.getValueCoded();
//				}
//			}			
//		}
//
//		Obs ret = null;
//		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.MEDICAL_IMAGE_CONSTRUCT)) {
//			for(Obs group : getObs().getGroupMembers()) {
//				if(!group.isVoided() && group.getValueCoded() != null && group.getConcept().getConceptId().equals(ConceptDictionary.TESTS_ORDERED)){
//					return group.getConcept();
//				}	
//			}
//		}
//
//		return null;
//	}
	
	
	public Obs getImageTypeObs() {
		if(!isEmr())
			return null;
				
		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST) || getObs().getConcept().getConceptId().equals(ConceptDictionary.CHEST_XRAY)) {
			return getObs();
		}

		if(getObs().getValueCoded() != null && ((getObs().getValueCoded().getConceptId().equals(ConceptDictionary.XRAY_CHEST) || getObs().getValueCoded().getConceptId().equals(ConceptDictionary.CHEST_XRAY))))
			return getObs();
		
		for(Obs group : getObs().getGroupMembers()) {
			if (!group.isVoided()){
				if(group.getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
					return group;
				if(group.getConcept().getConceptId().equals(ConceptDictionary.CHEST_XRAY))
					return group;
				if(group.getValueCoded() != null && group.getValueCoded().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
					return group;
				if(group.getValueCoded() != null && group.getValueCoded().getConceptId().equals(ConceptDictionary.CHEST_XRAY))
					return group;
				if(group.getConcept().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD))
					return group;
				if(group.getValueCoded() != null && group.getValueCoded().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD))
					return group;
			}
		}
		
		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.MEDICAL_IMAGE_CONSTRUCT)) {
			boolean testOrderedOther = false;
			for(Obs group : getObs().getGroupMembers()) {
				if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.TESTS_ORDERED) 
						&& group.getValueCoded() != null 
						&& group.getValueCoded().getConceptId().equals(ConceptDictionary.OTHER_NON_CODED)){
					testOrderedOther = true;
					break;
				}	
			}
			if (testOrderedOther){
				for(Obs group : getObs().getGroupMembers()) {
					if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.TESTS_ORDERED_NON_CODED)){
						return group;
					}	
				}
			}
		}
		
		Obs testsOrdered = null;
		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.TESTS_ORDERED)) {
			testsOrdered = getObs();
		}
		
		for(Obs group : getObs().getGroupMembers()) {
			if(!group.isVoided() && group.getConcept().getConceptId().equals(ConceptDictionary.TESTS_ORDERED)) {
				testsOrdered = group;
			}
		}
		
		if(testsOrdered != null) {
			for(Obs group : testsOrdered.getGroupMembers()) {
				if (!group.isVoided()){
					if(group.getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
						return group;
					if(group.getConcept().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD))
						return group;
					if(group.getValueCoded() != null && group.getValueCoded().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
						return group;
					if(group.getValueCoded() != null && group.getValueCoded().getConceptId().equals(ConceptDictionary.CHEST_XRAY))
						return group;
				}
			}			
		}

		return null;
	}
	
	public String getImageTypeString(){
		if (!isOther())
			return null;
		Obs o = getImageTypeObs();
		if (o != null){
			if (o.getConcept().getConceptId().equals(ConceptDictionary.TESTS_ORDERED_NON_CODED)){
				return o.getValueText();
			} else if (o.getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST) || o.getConcept().getConceptId().equals(ConceptDictionary.CHEST_XRAY) || o.getConcept().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD)) {
				return o.getConcept().getName().getName();
			} else if (o.getValueCoded() != null)
				try {
					return o.getValueCoded().getBestName(Context.getLocale()).getName();
				} catch (Exception ex){
					return o.getValueCoded().getName().getName();
				}
		}
		
		return null;
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ImageMapper#getComments()
	 */
	@Override
	public List<Obs> getComments() {
		if(!isEmr())
			return null;

		List<Obs> obsList = new LinkedList<Obs>();
		if(getObs().getGroupMembers() != null){
			//get the conceptAnswer from these guys
			for(Obs group : getObs().getGroupMembers()) {
				if (!group.isVoided()){
					if(group.getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
						obsList.add(group);
					else if(group.getConcept().getConceptId().equals(ConceptDictionary.SEVERITY_OF_CARDIOMEGALY))
						obsList.add(0, group);
					else if(group.getConcept().getConceptId().equals(ConceptDictionary.ASSESSMENT_COMMENTS))
						obsList.add(0, group);
					else if(group.getConcept().getConceptId().equals(ConceptDictionary.CLINICAL_IMPRESSION_COMMENTS))
						obsList.add(0, group);
					}
			}
		}
		
		if(getObs().getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST))
			obsList.add(getObs());
		
		return obsList;
	}
	
	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ImageMapper#isChestXRay()
	 */
	@Override
	public boolean isChestXRay() {
		Obs obs = getImageTypeObs();
		if (obs != null){
			if (obs.getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST) || ((obs.getValueCoded() != null && obs.getValueCoded().getConceptId().equals(ConceptDictionary.XRAY_CHEST))))
					return true;
			if (obs.getConcept().getConceptId().equals(ConceptDictionary.CHEST_XRAY) || ((obs.getValueCoded() != null && obs.getValueCoded().getConceptId().equals(ConceptDictionary.CHEST_XRAY))))
					return true;
			for (Obs otmp : obs.getGroupMembers()){
				if (otmp.getConcept().getConceptId().equals(ConceptDictionary.XRAY_CHEST) || ((otmp.getValueCoded() != null && otmp.getValueCoded().getConceptId().equals(ConceptDictionary.XRAY_CHEST))))
					return true;
				if (otmp.getConcept().getConceptId().equals(ConceptDictionary.CHEST_XRAY) || ((otmp.getValueCoded() != null && otmp.getValueCoded().getConceptId().equals(ConceptDictionary.CHEST_XRAY))))
					return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ImageMapper#isCTHead()
	 */
	@Override
	public boolean isCTHead() {
		Obs obs = getImageTypeObs();
		if (obs != null){
			if (obs.getConcept().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD) || ((obs.getValueCoded() != null && obs.getValueCoded().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD))))
					return true;

			for (Obs otmp : obs.getGroupMembers()){
				if (otmp.getConcept().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD) || ((otmp.getValueCoded() != null && otmp.getValueCoded().getConceptId().equals(ConceptDictionary.COMPUTED_TOMOGRAPHY_SCAN_HEAD))))
					return true;
			}
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see org.openmrs.module.rwandahivflowsheet.web.controller.ImageMapper#isOther()
	 */
	@Override
	public boolean isOther() {
		Obs test = getImageTypeObs();
		if(test == null)
			return false;
		
		return !((isChestXRay() || isCTHead()));
	}

	@Override
	public boolean isBlank() {
		return (super.isBlank() || ((getImageTypeObs() == null)));		
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		ImageMapping other = (ImageMapping) obj;
		if(getObs() == null || other.getObs() == null)
			return false;
		if(getDate() == null || other.getDate() == null || !getDate().equals(other.getDate()))
			return false;

		if(areObsDifferentValue(getImageTypeObs(), other.getImageTypeObs()))
			return false;
		
//		List<Obs> comments = getComments();
//		List<Obs> otherComments = other.getComments();
//		if(comments == null ^ otherComments == null)
//			return false;
//
//		if(comments != null) {
//
//			if(comments.size() != otherComments.size())
//				return false;
//
//			for(int index = 0; index < comments.size(); index++) {
//				if(areObsDifferentValue(comments.get(index), otherComments.get(index)))
//					return false;
//			}
//		}
	
		return true;
	}

	@Override
	public int compareTo(ImageMapping obj) {
		if (this == obj)
			return 0;
		if (obj == null)
			return -1;
		if(getDate() == null && obj.getDate() == null)
			return 0;
		if(getDate() == null)
			return 1;
		return getDate().compareTo(obj.getDate());
	}
	
//	@Override
//	public Encounter getEncounter() {
//		if (this.getObs() != null  && this.getObs().getEncounter() != null  && this.getObs().getEncounter().getForm() != null){
//					if (this.getObs().getEncounter().getForm().getFormId().equals(Integer.valueOf(ConceptDictionary.ADULT_IMAGE_FORM))
//							|| this.getObs().getEncounter().getForm().getFormId().equals(Integer.valueOf(ConceptDictionary.PEDI_IMAGE_FORM)))
//						return this.getObs().getEncounter();
//		}
//		return null;
//	}

}
