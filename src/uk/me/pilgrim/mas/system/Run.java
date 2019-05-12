package uk.me.pilgrim.mas.system;

import uk.me.pilgrim.mas.interfaces.Action;
import uk.me.pilgrim.mas.interfaces.State;
import uk.me.pilgrim.mas.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Run {

    private final State initial;
    private final ArrayList<Pair<Action, State>> steps;

    public Run(State initial, List<Pair<Action, State>> steps){
        this.initial = initial;
        this.steps = (ArrayList<Pair<Action, State>>) steps;
    }

    public Run(State initial){
        this(initial, new ArrayList<>());
    }

    public Run(Run run){
        this(run.initial, (List<Pair<Action, State>>) run.steps.clone());
    }

    public void add(Pair<Action, State> step){
        steps.add(step);
    }

    public void add(Action action, State state){
        add(new Pair(action, state));
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Run{");
        sb.append(initial);
        steps.forEach(step -> sb.append(" -> " + step.getKey() + " -> " + step.getValue()));
        sb.append("}");
        return sb.toString();
    }

    public State getCurrentState() {
        if (steps.size() == 0) return initial;
        else return steps.get(steps.size()-1).getValue();
    }
}
