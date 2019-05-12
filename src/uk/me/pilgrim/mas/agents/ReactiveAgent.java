package uk.me.pilgrim.mas.agents;

import uk.me.pilgrim.mas.interfaces.Action;
import uk.me.pilgrim.mas.interfaces.Agent;
import uk.me.pilgrim.mas.system.Run;
import uk.me.pilgrim.mas.enums.Actions;
import uk.me.pilgrim.mas.enums.States;

public class ReactiveAgent implements Agent {

    @Override
    public Action apply(Run run) {
        if (run.getCurrentState().equals(States.Initial)) return Actions.Die;
        return null;
    }
}
