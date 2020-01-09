package ubb.scs.map.ir.sem1.factory;

public interface Factory<E, T> {
    E create(T elementType);
}
