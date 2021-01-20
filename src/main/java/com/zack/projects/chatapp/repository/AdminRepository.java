package com.zack.projects.chatapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zack.projects.chatapp.model.Admin;

public interface AdminRepository extends JpaRepository<Admin, String>{

}
