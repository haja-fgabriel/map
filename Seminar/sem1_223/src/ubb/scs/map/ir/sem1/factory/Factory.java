package ubb.scs.map.ir.sem1.factory;

import ubb.scs.map.ir.sem1.container.Container;
import ubb.scs.map.ir.sem1.container.Strategy;

public interface Factory {
    Container createContainer(Strategy strategy);
}
