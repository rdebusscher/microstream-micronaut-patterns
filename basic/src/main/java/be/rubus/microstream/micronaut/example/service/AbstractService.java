package be.rubus.microstream.micronaut.example.service;

import be.rubus.microstream.micronaut.example.RootPreparation;
import be.rubus.microstream.micronaut.example.database.Locks;
import be.rubus.microstream.micronaut.example.database.Root;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import one.microstream.storage.types.StorageManager;

import javax.annotation.PostConstruct;

public abstract class AbstractService {

    @Inject
    protected Locks locks;

    @Inject
    @Named("main")
    protected StorageManager storageManager;

    @Inject
    private RootPreparation rootPreparation;

    protected Root root;

    @PostConstruct
    private void init() {
        rootPreparation.initialize(storageManager);
        root = (Root) storageManager.root();

    }

}
