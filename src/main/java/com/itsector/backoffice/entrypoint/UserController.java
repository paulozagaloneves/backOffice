package com.itsector.backoffice.entrypoint;

import com.itsector.backoffice.domain.User;
import com.itsector.backoffice.entrypoint.vo.UserVo;
import com.itsector.backoffice.entrypoint.vo.request.CreateUserRequestVo;
import com.itsector.backoffice.entrypoint.vo.request.UpdateUserRequestVo;
import com.itsector.backoffice.usecase.users.*;
import com.itsector.backoffice.usecase.users.CreateUser.CreateUserRequest;
import com.itsector.backoffice.usecase.users.UpdateUser.UpdateUserRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final GetUserById getUserById;

    public UserController(GetAllUsers getAllUsers, CreateUser createUser, DeleteUser deleteUser, UpdateUser updateUser, GetUserById getUserById) {
        this.getAllUsers = getAllUsers;
        this.createUser = createUser;
        this.deleteUser = deleteUser;
        this.updateUser = updateUser;
        this.getUserById = getUserById;
    }

    @GetMapping
    public List<UserVo> getUsers() {
        return getAllUsers.execute().stream().map(UserController::modelToVo).collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<String> crateUser(@RequestBody CreateUserRequestVo request) {
        Integer id = createUser.execute(getCreateUserRequest(request));
        return new ResponseEntity<String>(String.format("user with id %d created", id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        return new ResponseEntity(deleteUser.execute(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody UpdateUserRequestVo request) {

        return new ResponseEntity<String>(updateUser.execute(getUpdateUserRequest(id, request)), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public UserVo getUser(@PathVariable Integer id) {
        return modelToVo(getUserById.execute(id));
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
