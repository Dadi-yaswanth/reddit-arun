package com.reddit.service;


import com.reddit.entity.Community;
import com.reddit.entity.User;
import com.reddit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
     public void saveUser(User user){
         String displayName=user.getUsername().replaceAll(" ", "_");
         user.setDisplayName(displayName);
         userRepository.save(user);
     }

    public User isUsernameAndPasswordCorrect(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }

    public User getUserByID(Long userId) {
         return userRepository.findByUserId(userId);
    }
    public boolean saveUserPhoto(User user) throws IOException {
        try {
            if (user != null) {
                userRepository.save(user);
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return false;

    }

    public void addModeratedCommunity(User user, Community community) {
        Set<Community> moderatedCommunities = user.getCommunityModerators();
        moderatedCommunities.add(community);
        user.setCommunityModerators(moderatedCommunities);
        userRepository.save(user);
    }

    public void removeModeratedCommunity(User user, Community community) {
        Set<Community> moderatedCommunities = user.getCommunityModerators();
        moderatedCommunities.remove(community);
        user.setCommunityModerators(moderatedCommunities);
        userRepository.save(user);
    }
}
