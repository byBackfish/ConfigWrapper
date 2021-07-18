# ConfigBacken
A simple java wrapper for my [configuration backend](https://github.com/byBackfish/ConfigBackend)


# Requirements

* You need to have a [Backend](https://github.com/byBackfish/ConfigBackend) running

# Usage

```java
  ConfigHandler configHandler = new ConfigHandler();
    configHandler.setUrl(/* REQUIRED */  "http://localhost:3333");
    configHandler.setApiKey(/* OPTIONAL */ "api-key");
    configHandler.getConfig("test").thenAccept((config -> {
      config.setValue("test", true);
      System.out.println("Completed! " + config.getValue("test"));
    }));
```


# Installation

***Maven**

[Jitpack](https://jitpack.io/#byBackfish/ConfigWrapper/-SNAPSHOT)
