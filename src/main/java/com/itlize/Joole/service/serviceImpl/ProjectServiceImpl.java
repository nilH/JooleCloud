package com.itlize.Joole.service.serviceImpl;

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.entity.ProjectProduct;
import com.itlize.Joole.repository.ProductRepository;
import com.itlize.Joole.repository.ProjectProductRepository;
import com.itlize.Joole.repository.ProjectRepository;
import com.itlize.Joole.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    ProjectRepository projectRep;

    @Autowired
    ProjectProductRepository ppRepository;




    @Override
    public Project findById(int projectId) {
        return projectRep.findById(projectId).orElse(null);
    }



    public int createProject(Project project)
    {
        if(projectRep.findByProjectName(project.getProjectName()).size()>0)
        {
            return -1;
        }

        project.setTimeCreated(LocalDateTime.now());

        return projectRep.save(project).getId();
    }

    public List<Project> findAllProject()
    {
        return projectRep.findAll();
    }

    public List<Project> findByName(String projectName)
    {
        return projectRep.findByProjectName(projectName);
    }

    public int updateProject(Project project, int projectId)
    {
        Project project1=projectRep.findById(projectId).orElse(null);

        if(project1 == null)
        {
            return 0;
        }

        project1.setProjectName(project.getProjectName());
        return 1;
    }

    public int deleteProject(Project project)
    {
        if(projectRep.findById(project.getId()).isEmpty())
        {
            return 0;
        }

        simpleDeleteProject(project);  // This is a method in this class

        return 1;
    }

    public int simpleDeleteProject(Project project)   // To the facilitate unit testing
    {
        projectRep.delete(project);
        for(ProjectProduct projectProduct:project.getProjectProduct()){
            ppRepository.delete(projectProduct);
        }
        return 1;
    }

    public int simpleDeleteProjectProduct(ProjectProduct pp) // To the facilitate unit testing
    {
        ppRepository.delete(pp);
        return 1;
    }

}
