package edu.mit.spacenet.io.gson.scenario;

import java.util.UUID;

import edu.mit.spacenet.data.ElementPreview;
import edu.mit.spacenet.domain.ClassOfSupply;
import edu.mit.spacenet.domain.Environment;
import edu.mit.spacenet.domain.element.ElementIcon;
import edu.mit.spacenet.domain.element.ElementType;
import edu.mit.spacenet.domain.element.I_State;

public class CrewMember extends Element {
	public Double availableTimeFraction;

	public static CrewMember createFrom(edu.mit.spacenet.domain.element.CrewMember element, Context context) {
		CrewMember e = new CrewMember();
		e.id = context.getUUID(element);
		e.templateId = context.getElementTemplateUUID(element);
		CrewMember template = (CrewMember) context.getObject(e.templateId);
		if(template == null) {
			e.name = element.getName();
			e.description = element.getDescription();
			e.accommodatationMass = element.getAccommodationMass();
			e.mass = element.getMass();
			e.volume = element.getVolume();
			e.classOfSupply = element.getClassOfSupply().getId();
			e.environment = element.getEnvironment().getName();
			if(element.getIconType() != element.getElementType().getIconType()) {
				e.icon = element.getIconType().getName();
			}
			e.availableTimeFraction = element.getAvailableTimeFraction();
		} else {
			if(!template.name.equals(element.getName())) {
				e.name = element.getName();
			}
			if(!template.description.equals(element.getDescription())) {
				e.description = element.getDescription();
			}
			if(!template.accommodatationMass.equals(element.getAccommodationMass())) {
				e.accommodatationMass = element.getAccommodationMass();
			}
			if(!template.mass.equals(element.getMass())) {
				e.mass = element.getMass();
			}
			if(!template.volume.equals(element.getVolume())) {
				e.volume = element.getVolume();
			}
			if(!template.classOfSupply.equals(element.getClassOfSupply().getId())) {
				e.classOfSupply = element.getClassOfSupply().getId();
			}
			if(!template.environment.equals(element.getEnvironment().getName())) {
				e.environment = element.getEnvironment().getName();
			}
			if((template.icon == null && element.getIconType() != element.getElementType().getIconType()) 
					|| (template.icon != null && !template.icon.equals(element.getIconType().getName()))) {
				e.icon = element.getIconType().getName();
			}
			if(!template.availableTimeFraction.equals(element.getAvailableTimeFraction())) {
				e.availableTimeFraction = element.getAvailableTimeFraction();
			}
		}
		e.states = State.createFrom(element.getStates(), context);
		e.currentState = context.getUUID(element.getCurrentState());
		e.parts = Part.createFrom(element.getParts(), context);
		return e;
	}
	
	@Override
	public edu.mit.spacenet.domain.element.CrewMember toSpaceNet(Context context) {
		edu.mit.spacenet.domain.element.CrewMember e = new edu.mit.spacenet.domain.element.CrewMember();
		e.setUid(context.getId(id, e));
		e.setTid(templateId == null ? context.getId(id, e) : context.getId(templateId));
		edu.mit.spacenet.domain.element.CrewMember template = (edu.mit.spacenet.domain.element.CrewMember) context.getObject(templateId);
		e.setName(name == null ? template.getName() : name);
		e.setDescription(description == null ? template.getDescription() : description);
		e.setAccommodationMass(accommodatationMass == null ? template.getAccommodationMass() : accommodatationMass);
		e.setMass(mass == null ? template.getMass() : mass);
		e.setVolume(volume == null ? template.getVolume() : volume);
		e.setClassOfSupply(classOfSupply == null ? template.getClassOfSupply() : ClassOfSupply.getInstance(classOfSupply));
		e.setEnvironment(environment == null ? template.getEnvironment() : Environment.getInstance(environment));
		if(icon == null && template != null && template.getIconType() != template.getElementType().getIconType()) {
			e.setIconType(template.getIconType());
		} else if(icon != null) {
			e.setIconType(ElementIcon.getInstance(icon));
		}
		e.setAvailableTimeFraction(availableTimeFraction == null ? template.getAvailableTimeFraction() : availableTimeFraction);

		e.setStates(State.toSpaceNet(e, states, context));
		e.setCurrentState((I_State) context.getObject(currentState));
		e.setParts(Part.toSpaceNet(parts, context));
		return e;
	}
	
	@Override
	public ElementPreview getPreview(Context context) {
		return new ElementPreview(context.getId(id), name, ElementType.CREW_MEMBER, ElementIcon.getInstance(icon));
	}
	
	@Override
	public CrewMember clone() {
		CrewMember e = new CrewMember();
		e.id = UUID.randomUUID();
		e.templateId = templateId;
		e.name = name;
		e.description = description;
		e.accommodatationMass = accommodatationMass;
		e.mass = mass;
		e.volume = volume;
		e.classOfSupply = classOfSupply;
		e.environment = environment;
		e.states = State.clone(states);
		for(int i = 0; i < states.size(); i++) {
			if(states.get(i).id.equals(currentState)) {
				e.currentState = e.states.get(i).id;
			}
		}
		e.parts = Part.clone(parts);
		e.icon = icon;
		e.availableTimeFraction = availableTimeFraction;
		return e;
	}
}
