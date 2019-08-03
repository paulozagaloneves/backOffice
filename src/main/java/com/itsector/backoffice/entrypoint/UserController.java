package com.itsector.backoffice.entrypoint;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.entrypoint.vo.UserVo;
import com.itsector.backoffice.entrypoint.vo.request.CreateUserRequestVo;
import com.itsector.backoffice.entrypoint.vo.request.UpdateUserRequestVo;
import com.itsector.backoffice.usecase.users.CreateUser;
import com.itsector.backoffice.usecase.users.CreateUser.CreateUserRequest;
import com.itsector.backoffice.usecase.users.DeleteUser;
import com.itsector.backoffice.usecase.users.GetAllUsers;
import com.itsector.backoffice.usecase.users.UpdateUser;
import com.itsector.backoffice.usecase.users.UpdateUser.UpdateUserRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final GetAllUsers getAllUsers;
    private final CreateUser createUser;
    private final DeleteUser deleteUser;
    private final UpdateUser updateUser;

    public UserController(GetAllUsers getAllUsers, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser) {
        this.getAllUsers = getAllUsers;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
    }

    @GetMapping
    public List<UserVo> getUsers() {
        return getAllUsers.execute().stream().map(UserController::modelToVo).collect(Collectors.toList());
    }

    @PostMapping
    public Integer crateUser(@RequestBody CreateUserRequestVo request) {
        return createUser.execute(getCreateUserRequest(request));
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        return deleteUser.execute(id);
    }

    @PutMapping("/{id}")
    public String updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequestVo request) {
        return updateUser.execute(getUpdateUserRequest(id, request));
    }

    private CreateUserRequest getCreateUserRequest(CreateUserRequestVo request) {
        return CreateUserRequest.builder()
                .userName(request.getUserName())
                .name(request.getName())
                .password(request.getPassword()).build();
    }

    private UpdateUserRequest getUpdateUserRequest(Integer id, UpdateUserRequestVo request){
        return UpdateUserRequest.builder()
                .id(id)
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
