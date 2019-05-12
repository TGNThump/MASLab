package uk.me.pilgrim.mas.agents;

import uk.me.pilgrim.mas.interfaces.Action;
import uk.me.pilgrim.mas.interfaces.Agent;
import uk.me.pilgrim.mas.interfaces.Percept;
import uk.me.pilgrim.mas.interfaces.State;
import uk.me.pilgrim.mas.system.Env;
import uk.me.pilgrim.mas.system.Run;
import uk.me.pilgrim.mas.enums.Actions;
import uk.me.pilgrim.mas.enums.States;

import java.util.Set;
import java.util.function.Function;

public class SuicidalAgent implements Agent {
    // Percepts
    enum Percepts implements Percept{ HasDied }

    // Internal State
    boolean dead = false;

    @Override
    public Action apply(Run env) {
        Set<Percept> percepts = see(env);
        percepts.forEach(this::next);
        return action();
}

    public Set<Percept> see(Run env){
        if (env.getCurrentState().equals(States.Dead)) return Set.of(Percepts.HasDied);
        return Set.of();
    }

    public void next(Percept percept){
        if (percept.equals(Percepts.HasDied)) dead = true;
    }

    public Action action(){
        if (!dead) return Actions.Die;
        return null;
    }
}

