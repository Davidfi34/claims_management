package com.claims_management.user.service;

import com.claims_management.infra.errors.IntegrityValidation;
import com.claims_management.user.Dto.UpdateUser;
import com.claims_management.user.Dto.UserRequest;
import com.claims_management.user.Dto.UserResponse;
import com.claims_management.user.model.User;
import com.claims_management.user.repository.UsersRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImp implements UserService{

    private final UsersRepository usersRepository;

    public UserServiceImp(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public UserResponse saveUser(UserRequest userRequest) {
        User user = new User(null,userRequest.firstname(),userRequest.lastname());
        return new UserResponse(usersRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) {
        Optional<User> userOptional = usersRepository.findById(id);
        if (userOptional.isPresent()) return new UserResponse(userOptional.get());
        throw new IntegrityValidation("User not found");
    }

    @Override
    public Page<UserResponse> getAllUser(int numberPage) {
        int pageSize = 10;
        Pageable pageable = PageRequest.of(numberPage, pageSize);
        return usersRepository.findAll(pageable).map(UserResponse::new);
    }

    @Override
    public UserResponse updateUser(UpdateUser updateUser) {
        Optional<User> userOptional = usersRepository.findById(updateUser.id());
        if (userOptional.isPresent()){
            User user = new User(updateUser.id(),updateUser.firstname(),updateUser.lastname());
            return new UserResponse(usersRepository.save(user));
        }
        throw new IntegrityValidation("User not found");
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userOptional = usersRepository.findById(id);
        if (!userOptional.isPresent()) throw new IntegrityValidation("User not found");
        usersRepository.deleteById(id);
    }

}