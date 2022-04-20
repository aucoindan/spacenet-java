package edu.mit.spacenet.io.gson.scenario;

import edu.mit.spacenet.domain.network.edge.EdgeType;

public class FlightEdge extends Edge {
	public String type = TYPE_MAP.inverse().get(EdgeType.FLIGHT);
	public double duration;
	public int max_crew;
	public double max_cargo;

	public static FlightEdge createFrom(edu.mit.spacenet.domain.network.edge.FlightEdge edge, Context context) {
		FlightEdge e = new FlightEdge();
		e.id = context.getUUID(edge);
		e.name = edge.getName();
		e.description = edge.getDescription();
		e.origin_id = context.getUUID(edge.getOrigin());
		e.destination_id = context.getUUID(edge.getDestination());
		e.duration = edge.getDuration();
		e.max_crew = edge.getMaxCrewSize();
		e.max_cargo = edge.getMaxCargoMass();
		return e;
	}
	
	public edu.mit.spacenet.domain.network.edge.FlightEdge toSpaceNet(Context context) {
		edu.mit.spacenet.domain.network.edge.FlightEdge e = new edu.mit.spacenet.domain.network.edge.FlightEdge();
		e.setTid(context.getId(id));
		e.setName(name);
		e.setDescription(description);
		e.setOrigin((edu.mit.spacenet.domain.network.node.Node) context.getObject(origin_id));
		e.setDestination((edu.mit.spacenet.domain.network.node.Node) context.getObject(destination_id));
		e.setDuration(duration);
		e.setMaxCrewSize(max_crew);
		e.setMaxCargoMass(max_cargo);
		return e;
	}
}
