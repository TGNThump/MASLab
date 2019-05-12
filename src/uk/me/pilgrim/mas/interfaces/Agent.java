package uk.me.pilgrim.mas.interfaces;

import uk.me.pilgrim.mas.system.Run;

import java.util.function.Function;

@FunctionalInterface
public interface Agent extends Function<Run, Action> {
}
