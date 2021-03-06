package com.leeharkness.mimir;

import com.leeharkness.mimir.mimiractions.ExitAction;
import com.leeharkness.mimir.mimiractions.LoginAction;
import com.leeharkness.mimir.mimiractions.RegisterAction;
import com.leeharkness.mimir.mimirsupport.MimirInputFacility;
import com.leeharkness.mimir.mimirsupport.MimirOutputFacility;
import com.leeharkness.mimir.mimirsupport.MimirSessionContext;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import javax.inject.Inject;

public class MimirApp {

    private MimirActions actionRegistry;
    private MimirUIElements mimirUIElements;

    @Inject
    public MimirApp(MimirActions actionRegistry) {
        this.actionRegistry = actionRegistry;
    }

    void run(@SuppressWarnings("unused") String[] args) {

        // Setup our home screen
        TextIO textIO = TextIoFactory.getTextIO();
        TextTerminal<?> terminal = textIO.getTextTerminal();
        MimirInputFacility mimirInputFacility = MimirInputFacility.builder().textIO(textIO).build();
        MimirOutputFacility mimirOutputFacility = MimirOutputFacility.builder().textTerminal(terminal).build();
        String input;
        ActionResult result = null;
        MimirSessionContext mimirSessionContext = MimirSessionContext.builder().build();

        mimirUIElements = MimirUIElements.builder()
                .prompt(">")
                .inputFacility(mimirInputFacility)
                .outputFacility(mimirOutputFacility)
                .build();

        // Login, or register and then login user
        boolean loggedIn = false;
        while (!loggedIn) {
            terminal.print("type 'register' or 'login' to start ('exit' or 'quit' to exit)");
            input = textIO.newStringInputReader().read(mimirUIElements.getPrompt());
            MimirAction action = actionRegistry.from(input);
            if (!isInitialAction(action)) {
                continue;
            }
            result = action.handle(input, mimirUIElements, mimirSessionContext);
            if (result.isTerminate()) {
                System.exit(result.getExitCode());
            }
            loggedIn = result.isLoggedIn();
        }

        mimirUIElements.setPrompt(mimirUIElements.getPrompt());

        // Main loop
        while(!result.isTerminate()) {
            // TODO: show messages here
            input = textIO.newStringInputReader().read(mimirUIElements.getPrompt());
            MimirAction action = actionRegistry.from(input);
            result = action.handle(input, mimirUIElements, mimirSessionContext);
            mimirUIElements.setPrompt(mimirUIElements.getPrompt());
        }

        System.exit(result.getExitCode());
    }

    /**
     * Determines whether this is an allowed initial action (register, login, exit)
     * @param action
     * @return
     */
    private boolean isInitialAction(MimirAction action) {
        return action instanceof RegisterAction || action instanceof LoginAction ||
                action instanceof ExitAction;
    }
}
