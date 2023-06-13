# Microstream Micronaut Integration patterns

Patterns in using MicroStream within Micronaut.


# Basic

Basic integration usage.

Add the Micronaut Microstream integration dependency to the project.

````
    <dependency>
      <groupId>io.micronaut.microstream</groupId>
      <artifactId>micronaut-microstream</artifactId>
      <scope>compile</scope>
    </dependency>
````

If you add the dependency through the Micronaut Launch webpage or the command line, the annotations dependency is also added which is not needed in this basic example.


Define the Java class which will be used as a root object within MicroStream.  There is no specific Micronaut functionality required in this class.  In the example, the class is called `be.rubus.microstream.micronaut.example.database.Root`.

Make the configuration for MicroStream and the integration. This can be done in the _application.yml_ file or any other source that is supported by Micronaut.


````
microstream:
  storage:
    main:
      root-class: 'be.rubus.microstream.micronaut.example.database.Root'
      storage-directory: target/storage
````

Important here is that all StorageManagers that are maintained by Micronaut are named. And in the above example, we use the name `main` for the _StorageManager_.  We need to use this same name later on in the code so that the correct configuration is picked up.`

There are various ways to make use of the integration. In this example, we inject the `StorageManager` that is prepared by Micronaut in an abstract super-service class. We also make sure that the data are initialised and that the root can be accessed by any service class itself.


````
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
````

You can also find some information about the integration on this [Micronaut documentation page](https://micronaut-projects.github.io/micronaut-microstream/snapshot/guide/).

