package com.leeharkness.mimir.module;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import com.leeharkness.mimir.MimirAction;
import com.leeharkness.mimir.mimiractions.*;

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
        public MimirAction provideLogoutAction(LogoutAction action) {
        return action;
    }

    @ProvidesIntoSet
    @Named("ActionSet")
    public MimirAction provideRegisterAction(RegisterAction action) {
        return action;
    }

    @ProvidesIntoSet
    @Named("ActionSet")
    public MimirAction provideRetrieveMessagesAction(RetrieveMessagesAction action) {
        return action;
    }

    @ProvidesIntoSet
    @Named("ActionSet")
    public MimirAction provideSendMessagesAction(SendMessageAction action) {
        return action;
    }

}
