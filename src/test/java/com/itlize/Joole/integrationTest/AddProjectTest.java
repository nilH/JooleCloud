package com.itlize.Joole.integrationTest;

import com.itlize.Joole.entity.Project;
import com.itlize.Joole.repository.ProjectRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AddProjectTest {
    @Autowired
    ProjectRepository projectRepository;

    @Test
    public void addProject(){
        Project project=new Project();
        project.setProjectName("projectname");
        projectRepository.save(project);
    }
}
