package com.itlize.Joole.repository;


import com.itlize.Joole.entity.Project;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProjectRepositoryTest {

    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void addProject()
    {
        Project project = new Project();

        project.setProjectName("MyProject");

        projectRepository.save(project);

        assert(project.getId()!= null);
    }

    @Test
    public void findByProjectNameTest()
    {
        Project project = new Project();

        project.setProjectName("MyProject");

        projectRepository.save(project);

        List<Project> list = projectRepository.findByProjectName("MyProject");

        assert(list.get(0) != null);
    }

    @Test
    public void findByIdTest()
    {
        Optional<Project> project = projectRepository.findById(1);


        assert(project.isEmpty());
    }

}
