package com.itsector.backoffice.entrypoint;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.entrypoint.vo.UserVo;
import com.itsector.backoffice.entrypoint.vo.request.CreateUserRequestVo;
import com.itsector.backoffice.usecase.users.CreateUser;
import com.itsector.backoffice.usecase.users.CreateUser.CreateUserRequest;
import com.itsector.backoffice.usecase.users.GetAllUsers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final GetAllUsers getAllUsers;
    private final CreateUser createUser;

    public UserController(GetAllUsers getAllUsers, CreateUser createUser) {
        this.getAllUsers = getAllUsers;
        this.createUser = createUser;
    }

    @GetMapping
    public List<UserVo> getUsers() {
        return getAllUsers.execute().stream().map(UserController::modelToVo).collect(Collectors.toList());
    }

    @PostMapping
    public Integer crateUser(@RequestBody CreateUserRequestVo request) {
        return createUser.execute(getCreateUser(request));
    }

    private CreateUserRequest getCreateUser(@RequestBody CreateUserRequestVo request) {
        return CreateUserRequest.builder()
                .userName(request.getUserName())
                .name(request.getName())
                .password(request.getPassword()).build();
    }

    private static UserVo modelToVo(final User user) {
        if (user == null) {
            return null;
        }
        return new UserVo(user);
    }
}
