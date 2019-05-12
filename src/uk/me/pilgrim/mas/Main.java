package uk.me.pilgrim.mas;

import uk.me.pilgrim.mas.agents.SuicidalAgent;
import uk.me.pilgrim.mas.enums.Actions;
import uk.me.pilgrim.mas.enums.States;
import uk.me.pilgrim.mas.interfaces.Action;
import uk.me.pilgrim.mas.interfaces.Agent;
import uk.me.pilgrim.mas.interfaces.State;
import uk.me.pilgrim.mas.system.Env;
import uk.me.pilgrim.mas.system.Run;
import uk.me.pilgrim.mas.system.System;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class Main {

    public static void main(String[] args){
        Function<Run,Double> taskSpec = (run) -> 1d;
        Env env = new Env(Set.of(States.values()), States.Initial, Main::Transformer);
        Agent agent = new SuicidalAgent();

        Map<Run, Double> runs = runTask(agent, env, taskSpec);
    }

    public static Map<Run, Double> runTask(Agent agent,  Env env, Function<Run, Double> taskSpec){
        Map<Run, Double> utility = new HashMap<>();

        System system = new System(agent, env);
        system.run().forEach(run -> {
            java.lang.System.out.println(run);
            utility.put(run, taskSpec.apply(run));
        });

        return utility;
    }

    private static Set<State> Transformer(Run run, Action action){
        if (action.equals(Actions.Die)) return Set.of(States.Dead);
        return Set.of();
    }
}