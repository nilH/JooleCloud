package com.itlize.Joole.service;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;

import java.util.List;

public interface ProjectService {




    Project findById(int projectId);
    //crud
    int createProject(Project project);
    List<Project> findAllProject();

    List<Project> findByName(String projectName);
    int updateProject(Project projuct, int projectId);
    int deleteProject(Project project);
}
