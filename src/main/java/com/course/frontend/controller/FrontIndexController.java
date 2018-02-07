package com.course.frontend.controller;

import com.course.controller.ServiceController;
import com.course.frontend.service.UserService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/frontend")
@Scope("prototype")
public class FrontIndexController extends ServiceController<UserService> {

    @RequestMapping(value = "/users/{name}")
    public Object users(@PathVariable("name") String name) throws Exception {
        return "frontend/users/" + name;
    }

    @RequestMapping(value = "/courses/{name}")
    public Object courses(@PathVariable("name") String name) throws Exception {
        return "frontend/courses/" + name;
    }

}
