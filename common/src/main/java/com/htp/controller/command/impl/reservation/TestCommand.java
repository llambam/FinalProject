package com.htp.controller.command.impl.reservation;

import com.htp.controller.command.util.CommandException;
import com.htp.controller.command.util.CommandInterface;
import com.htp.domain.to.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

public class TestCommand implements CommandInterface {
    private static final String ACTION = "action";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private TestCommand() {
    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new TestCommand();
    }


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        User[] user = new User[5];
//        user[0]=new User(5L,"ss","asdsa","login","pass","454545","sdsd@asd",0,"2012-12-12");
//        user[1]=new User(6L,"ss","asdsa","login","pass","454545","sdsd@asd",0,"2012-12-12");
//        user[2]=new User(7L,"ss","asdsa","login","pass","454545","sdsd@asd",0,"2012-12-12");
//        user[3]=new User(8L,"ss","asdsa","login","pass","454545","sdsd@asd",0,"2012-12-12");
//        user[4]=new User(9L,"ss","asdsa","login","pass","454545","sdsd@asd",0,"2012-12-12");
        List<User> users = Arrays.asList(user);

        request.setAttribute("testList", users);
        request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
        return "/";
    }
}