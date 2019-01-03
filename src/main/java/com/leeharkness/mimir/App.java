package com.leeharkness.mimir;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.leeharkness.mimir.module.MimirModule;

public class App {
    public static void main( String[] args ) {
        Injector injector = Guice.createInjector(new MimirModule());
        MimirApp app = injector.getInstance(MimirApp.class);
        app.run(args);
    }
}
