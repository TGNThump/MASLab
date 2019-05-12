package uk.me.pilgrim.mas.system;

import uk.me.pilgrim.mas.interfaces.Action;
import uk.me.pilgrim.mas.interfaces.Agent;
import uk.me.pilgrim.mas.interfaces.State;

import java.util.HashSet;
import java.util.Set;

public class System {

    private final Agent agent;
    private final Env env;

    private Set<Run> runs = new HashSet();

    public System(Agent agent, Env env) {
        this.agent = agent;
        this.env = env;
    }

    public Set<Run> run() {
        run(new Run(env.getInitial()));
        return runs;
    }

    private void run(Run input){
        Action action = agent.apply(input);
        Set<State> states = env.transform(input, action);

        if (states.size() == 0){
            runs.add(input);
        }
        else {
            for (State state : states){
                Run output = new Run(input);
                output.add(action, state);

                run(output);
            }
        }
    }
}
