package com.artivisi.kampus.latihan.dao;

import com.artivisi.kampus.latihan.entity.UserProfile;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserProfileDao extends PagingAndSortingRepository<UserProfile,String> {

    UserProfile findByUserUsername(String username);

}
