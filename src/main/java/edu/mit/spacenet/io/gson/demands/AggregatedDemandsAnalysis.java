package edu.mit.spacenet.io.gson.demands;

import java.util.ArrayList;
import java.util.List;

public class AggregatedDemandsAnalysis {
  public List<NodeDemand> nodes = new ArrayList<NodeDemand>();
  public List<EdgeDemand> edges = new ArrayList<EdgeDemand>();

  public static AggregatedDemandsAnalysis createFrom(
      edu.mit.spacenet.simulator.DemandSimulator sim) {
    AggregatedDemandsAnalysis o = new AggregatedDemandsAnalysis();
    o.nodes = NodeDemand.createFrom(sim.getAggregatedNodeDemands());
    o.edges = EdgeDemand.createFrom(sim.getAggregatedEdgeDemands());
    return o;
  }
}
