package com.leeharkness.mimir.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import com.leeharkness.mimir.MimirAction;
import com.leeharkness.mimir.mimiractions.DefaultAction;
import com.leeharkness.mimir.mimiractions.ExitAction;
import com.leeharkness.mimir.mimiractions.LoginAction;
import com.leeharkness.mimir.mimiractions.RegisterAction;

public class MimirModule extends AbstractModule {

    @ProvidesIntoSet
    @Named("ActionSet")
    public MimirAction provideDefaultAction(DefaultAction action) {
        return action;
    }

    @ProvidesIntoSet
    @Named("ActionSet")
    public MimirAction provideExitAction(ExitAction action) {
        return action;
    }

    @ProvidesIntoSet
    @Named("ActionSet")
    public MimirAction provideLoginAction(LoginAction action) {
        return action;
    }

    @ProvidesIntoSet
    @Named("ActionSet")
        public MimirAction provideRegisterAction(RegisterAction action) {
        return action;
    }

}
