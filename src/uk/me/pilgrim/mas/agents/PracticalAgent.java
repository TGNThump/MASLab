package uk.me.pilgrim.mas.agents;

import uk.me.pilgrim.mas.interfaces.*;
import uk.me.pilgrim.mas.system.Run;

import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;

public class PracticalAgent extends AbstractReasoner implements Agent {

    Queue<Set<Percept>> percepts = new ConcurrentLinkedQueue();
    Queue<Action> actions = new ConcurrentLinkedQueue();

    public PracticalAgent(){
        new Thread(this).start();
    }

    @Override
    public Action apply(Run run) {
        percepts.add(see(run));
        while (actions.isEmpty()) Thread.yield();
        return actions.poll();
    }

    protected Set<Percept> see(Run env){
        return Set.of();
    }

    @Override
    protected void execute(Action action) {
        actions.add(action);
    }

    @Override
    protected Set<Percept> getPercepts() {
        while (percepts.isEmpty()) Thread.yield();
        return percepts.poll();
    }

    @Override
    protected Set<Belief> brf(Set<Belief> beliefs, Set<Percept> percept) {
        return null;
    }

    @Override
    protected Set<Intention> options(Set<Belief> beliefs, Set<Intention> intentions) {
        return null;
    }

    @Override
    protected Set<Intention> filter(Set<Belief> beliefs, Set<Intention> desires, Set<Intention> intentions) {
        return null;
    }

    @Override
    protected Queue<Action> plan(Set<Belief> beliefs, Set<Intention> intentions, Set<Action> actions) {
        return null;
    }

    @Override
    protected boolean isSound(Queue<Action> plan, Set<Intention> intentions, Set<Belief> beliefs) {
        return false;
    }

    @Override
    protected boolean shouldReconsider(Set<Intention> intentions, Set<Belief> beliefs) {
        return false;
    }
}
