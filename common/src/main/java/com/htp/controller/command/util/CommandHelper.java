package com.htp.controller.command.util;

import com.htp.controller.command.impl.admin.*;
import com.htp.controller.command.impl.reservation.LoginCommand;
import com.htp.controller.command.impl.reservation.LogoutCommand;
import com.htp.controller.command.impl.reservation.RegistrationCommand;
import com.htp.controller.command.impl.user.AddNewNumberCommand;
import com.htp.controller.command.impl.user.AddToFavoriteCommand;
import com.htp.controller.command.impl.user.PhoneBookTableCommand;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandHelper {


    private static final String ATTRIBUTE_COMMAND = "command";
    private static final String DASH = "-";
    private static final String UNDERSCORE = "_";

    private final Map<CommandName, CommandInterface> commands = new HashMap<>();

    public CommandHelper() {
        commands.put(CommandName.AUTORIZATION, LoginCommand.getInstance());
        commands.put(CommandName.LOGOUT, LogoutCommand.getInstance());
        commands.put(CommandName.REGISTRATION, RegistrationCommand.getInstance());
        commands.put(CommandName.BLOCK, BlockCommand.getInstance());
        commands.put(CommandName.PHONEBOOKTABLE, PhoneBookTableCommand.getInstance());
        commands.put(CommandName.ADD_TO_FAVORITE, AddToFavoriteCommand.getInstance());
        commands.put(CommandName.ADD_NEW_NUMBER, AddNewNumberCommand.getInstance());
        commands.put(CommandName.ADMIN_USER_TABLE, AdminUserTableCommand.getInstance());
        commands.put(CommandName.ADMIN_GO_TO_EDIT, AdminGoToEditCommand.getInstance());
        commands.put(CommandName.ADMIN_SHOW_USER, AdminShowUserCommand.getInstance());
        commands.put(CommandName.CHANGE_STATUS, ChangeStatusCommand.getInstance());
        commands.put(CommandName.EDIT_USER_FOR_ADMIN, AdminEditUserCommand.getInstance());
        commands.put(CommandName.DELETE_USER, AdminDeleteUserCommand.getInstance());
        commands.put(CommandName.ADMIN_PHONES_TABLE, AdminPhoneTableCommand.getInstance());
        commands.put(CommandName.ADMIN_GO_TO_EDIT_PHONES, AdminGoToEditPhonesCommand.getInstance());


    }


    /**
     * Method determines by request of which command is needed and returns the command object
     *
     * @param request HttpServletRequest
     * @return command object if command exists in map, else return null
     */
    public CommandInterface getCommand(HttpServletRequest request) {
        String commandName = request.getParameter(ATTRIBUTE_COMMAND);
        if (commandName != null) {
            CommandName name = CommandName.valueOf(commandName.toUpperCase().replace(DASH, UNDERSCORE));
            return commands.get(name);
        } else {
            return null;
        }
    }


    private enum CommandName {
        AUTORIZATION, LOGOUT, REGISTRATION, BLOCK, PHONEBOOKTABLE, ADD_NEW_NUMBER, ADD_TO_FAVORITE, ADMIN_USER_TABLE, ADMIN_GO_TO_EDIT, ADMIN_SHOW_USER, CHANGE_STATUS, EDIT_USER_FOR_ADMIN, DELETE_USER, ADMIN_PHONES_TABLE, ADMIN_GO_TO_EDIT_PHONES
    }

}
