package uk.me.pilgrim.mas.system;

import uk.me.pilgrim.mas.interfaces.Action;
import uk.me.pilgrim.mas.interfaces.State;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

public class Env {

    final private Set<State> states;
    final private State initial;
    final private BiFunction<Run, Action, Set<State>> transformer;

    public Env(Set<State> e, State e0, BiFunction<Run, Action, Set<State>> t) {
        this.states = e;
        this.initial = e0;
        this.transformer = t;
    }

    public State getInitial() {
        return initial;
    }

    public Set<State> transform(Run run, Action action) {
        if (action == null) return Set.of();
        Set<State> states = transformer.apply(run, action);
        for (State state : difference(states, this.states)) throw new IllegalStateException("Illegal State: " + state);
        return states;
    }

    private <E> Set<E> difference(Set<E> a, Set<E> b){
        Set<E> diff = new HashSet<>();
        diff.addAll(a);
        diff.removeAll(b);
        return diff;
    }
}
