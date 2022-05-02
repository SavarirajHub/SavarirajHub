package com.rfpio.user.service;

import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import com.rfpio.user.exception.UserExistException;
import com.rfpio.user.exception.UserNotFoundException;
import com.rfpio.user.model.DatabaseSequence;
import com.rfpio.user.model.User;
import com.rfpio.user.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepository userRepository;
	
	private MongoOperations mongoOperations;

    @Autowired
    public UserServiceImpl(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }
	
	public User save(User user) {
		User userdb = userRepository.findByEmail(user.getEmail());
		if(userdb == null) {
			user.setUserId(generateSequence(User.SEQUENCE_NAME));
			return userRepository.save(user);
		} else {
			throw new UserExistException(MessageFormat.format("Email {0} already exist", user.getEmail()));
		}
	}
	
	public User updateUser(int userId, User user) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User responseUser = null;
		if(optionalUser.isPresent()) {
			User dbUserObj = optionalUser.get();
			if(Objects.nonNull(user.getUserName()) && !user.getUserName().isBlank()) {
				dbUserObj.setUserName(user.getUserName());
			}
			if(Objects.nonNull(user.getAge()) && user.getAge() > 0) {
				dbUserObj.setAge(user.getAge());
			}
			if(Objects.nonNull(user.getAddress()) && !user.getAddress().isBlank()) {
				dbUserObj.setAddress(user.getAddress());
			}
			if(Objects.nonNull(user.getEmail()) && !user.getEmail().isBlank()) {
				dbUserObj.setEmail(user.getEmail());
			}
			responseUser = userRepository.save(dbUserObj);
		} else {
			throw new UserNotFoundException(MessageFormat.format("User Not Found with Id {0}", userId));
		}
		return responseUser;
		
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(int userId) {
		Optional<User> userOpt = userRepository.findById(userId);
		if(userOpt.isPresent()) {
			return userOpt.get();
		} else {
			throw new UserNotFoundException(MessageFormat.format("User Not Found with Id {0}", userId));
		}
		
	}
	
	public void deleteById(int userId) {
		findById(userId);
		userRepository.deleteById(userId);
	}

	public List<User> findByUserName(String userName) {
		List<User> userList = userRepository.findByUserName(userName);
		if(userList.size() > 0) {
			return userList;
		} else {
			throw new UserNotFoundException(MessageFormat.format("User Not Found with Name {0}", userName));
		}
		
	}

	public User findByEmail(String email) {
		User user = userRepository.findByEmail(email);
		if(user != null) {
			return user;
		} else {
			throw new UserNotFoundException(MessageFormat.format("User Not fount with email {0}", email));
		}
		
	}
	
	public long generateSequence(String seqName) {

        DatabaseSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }

}
