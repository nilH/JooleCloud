package com.itlize.Joole.repository;

import com.itlize.Joole.entity.Project;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer> {

    List<Project> findByProjectName(String projectName);

    @Override
    Optional<Project> findById(Integer integer);
}
