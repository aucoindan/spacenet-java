package edu.mit.spacenet.io.gson.scenario;

import java.time.Duration;
import java.time.Period;
import java.util.List;
import java.util.UUID;

import org.threeten.extra.PeriodDuration;

public class CreateElements extends Event {

  protected List<UUID> elements;
  protected UUID container;

  public static CreateElements createFrom(edu.mit.spacenet.simulator.event.CreateEvent event,
      Context context) {
    CreateElements e = new CreateElements();
    e.name = event.getName();
    e.mission_time = PeriodDuration.of(Period.ofDays((int) event.getTime()),
        Duration.ofSeconds((long) ((event.getTime() - (int) event.getTime()) * 24 * 60 * 60)));
    e.priority = event.getPriority();
    e.location = context.getJsonIdFromJavaObject(event.getLocation());
    e.elements = context.getJsonIdsFromJavaObjects(event.getElements());
    e.container = context.getJsonIdFromJavaObject(event.getContainer());
    return e;
  }

  @Override
  public edu.mit.spacenet.simulator.event.CreateEvent toSpaceNet(Context context) {
    edu.mit.spacenet.simulator.event.CreateEvent e =
        new edu.mit.spacenet.simulator.event.CreateEvent();
    e.setName(name);
    e.setTime(mission_time.getPeriod().getDays()
        + mission_time.getDuration().getSeconds() / (24 * 60 * 60d));
    e.setPriority(priority);
    e.setLocation(
        (edu.mit.spacenet.domain.network.Location) context.getJavaObjectFromJsonId(location));
    e.setElements(Element.toSpaceNetViaId(elements, context));
    e.setContainer(
        (edu.mit.spacenet.domain.I_Container) context.getJavaObjectFromJsonId(container));
    return e;
  }

}
