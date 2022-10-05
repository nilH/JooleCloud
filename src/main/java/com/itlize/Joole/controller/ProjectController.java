package com.itlize.Joole.controller;

//add product to project

import com.itlize.Joole.entity.Product;
import com.itlize.Joole.entity.Project;
import com.itlize.Joole.service.ProjectProductService;
import com.itlize.Joole.service.ProjectService;
import com.itlize.Joole.service.serviceImpl.ProjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectProductService projectProductService;
    @Autowired
    private ProjectService projectService;

    @PostMapping("/add_product_to_project")
    public ResponseEntity<?> addProductToProject(@RequestParam("product_id") Integer productId,@RequestParam("project_id") Integer projectId)
    {
        int result = projectProductService.addProductToProject(productId, projectId);
        if (result == 0) {
            return new ResponseEntity<>("{\"Project or Product Not exist!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);


    }

    @PostMapping("/delete_product_from_project")
    public ResponseEntity<?> deleteProductFromProject(@RequestParam("product_id") Integer productId,@RequestParam("project_id") Integer projectId)
    {
        int result = projectProductService.deleteProductFromProject(productId, projectId);
        if (result == 0) {
            return new ResponseEntity<>("{\"Project or Product Not exist!\"}", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get_product_from_project")
    public ResponseEntity<?> getProductFromProject(@RequestParam("project_id") int projectId)
    {
        List<Product> result = projectProductService.getProductFromProject(projectId);
        if (result == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/add_project")
    public ResponseEntity<?> addProject(@RequestBody Project project){
        Integer result = projectService.createProject(project);
        if (result == null) {
            return new ResponseEntity<>("{\"unknown mistake!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping(value = "/delete_project")
    public ResponseEntity<?> deleteProject(@RequestParam("project_id") int projectId){
        Project project1 = projectService.findById(projectId);
        if (project1 == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        projectService.deleteProject(project1);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(value = "/update_project")
    public ResponseEntity<?> updateProject(@RequestBody Project project,@RequestParam("project_id") Integer projectId){
        Project project1 = projectService.findById(projectId);
        if (project1 == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        projectService.updateProject(project, projectId);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping(value = "/findall_project")
    public ResponseEntity<?> findAllProject() {
        List<Project> result = projectService.findAllProject();
        if (result == null) {
            return new ResponseEntity<>("{\"Empty!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/findby_projectid")
    public ResponseEntity<?> findById(@RequestParam("project_id") Integer projectId){
        Project result = projectService.findById(projectId);
        if (result == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "/findby_projectname")
    public ResponseEntity<?> findByName(@RequestParam("project_name") String projectName){
        List<Project> result = projectService.findByName(projectName);
        if (result == null) {
            return new ResponseEntity<>("{\"Not exist!\"}",HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
