package uk.me.pilgrim.mas.agents;

import uk.me.pilgrim.mas.interfaces.*;
import uk.me.pilgrim.mas.system.Run;

import java.util.*;
import java.util.function.Supplier;

public abstract class AbstractReasoner implements Runnable{

    Set<Belief> beliefs = new HashSet();
    Set<Intention> intentions = new HashSet<>();
    Set<Action> actions = new HashSet<>();

    protected abstract void execute(Action action);

    @Override
    public void run(){
        while (true) loop();
    }

    public void loop(){
        Set<Percept> percepts = getPercepts();
        beliefs = brf(beliefs, percepts);
        Set<Intention> desires = options(beliefs, intentions);
        intentions = filter(beliefs, desires, intentions);
        Queue<Action> plan = plan(beliefs, intentions, actions);

        while (plan.isEmpty()){
            execute(plan.poll());
            percepts = getPercepts();
            beliefs = brf(beliefs, percepts);
            if (shouldReconsider(intentions, beliefs)){
                desires = options(beliefs, intentions);
                intentions = filter(beliefs, desires, intentions);
            }
            if (!isSound(plan, intentions, beliefs)){
                plan = plan(beliefs, intentions, actions);
            }
        }
    }

    protected abstract Set<Percept> getPercepts();
    protected abstract Set<Belief> brf(Set<Belief> beliefs, Set<Percept> percept);
    protected abstract Set<Intention> options(Set<Belief> beliefs, Set<Intention> intentions);
    protected abstract Set<Intention> filter(Set<Belief> beliefs, Set<Intention> desires, Set<Intention> intentions);
    protected abstract Queue<Action> plan(Set<Belief> beliefs, Set<Intention> intentions, Set<Action> actions);
    protected abstract boolean isSound(Queue<Action> plan, Set<Intention> intentions, Set<Belief> beliefs);
    protected abstract boolean shouldReconsider(Set<Intention> intentions, Set<Belief> beliefs);
}

