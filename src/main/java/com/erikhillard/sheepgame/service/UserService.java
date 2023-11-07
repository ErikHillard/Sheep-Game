package com.erikhillard.sheepgame.service;

import com.erikhillard.sheepgame.dto.UserRequest;
import com.erikhillard.sheepgame.dto.UserResponse;
import com.erikhillard.sheepgame.exception.EmailAlreadyExistsException;
import com.erikhillard.sheepgame.exception.UserNotFoundException;
import com.erikhillard.sheepgame.model.User;
import com.erikhillard.sheepgame.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public void createUser(UserRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            log.warn("Email " + userRequest.getEmail() + " is already in use.");
            throw new EmailAlreadyExistsException(userRequest.getEmail());
        }
        User user = User.builder()
                .email(userRequest.getEmail())
                .answerList(new ArrayList<>())
                .build();
        userRepository.save(user);
        log.info("User id: {} email: {} is saved", user.getId(), user.getEmail());
    }

    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(this::mapToUserResponse).toList();
    }

    public UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .email(user.getEmail())
                .id(user.getId())
                .answers(user.getAnswerList())
                .build();
    }

    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    public UserResponse getUser(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return mapToUserResponse(userOptional.get());
        } else {
            String message = "Could not find user with id: " + userId;
            log.error(message);
            throw new UserNotFoundException(message);
        }
    }

    public User getUserObject(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.get();
        } else {
            String message = "Could not find user with id: " + userId;
            log.error(message);
            throw new UserNotFoundException(message);
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
