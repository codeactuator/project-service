package com.codeactuator.rocket;

import com.codeactuator.rocket.domain.Project;
import com.codeactuator.rocket.dto.ProjectDTO;
import com.codeactuator.rocket.dto.WorkforceDTO;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    private static final List<Project> projects = Arrays.asList(
            new Project("CSA"),
            new Project("GGA"),
            new Project("PPL"),
            new Project("OFAC")
    );

    public static void main(String args[]){
        System.out.println("Hello Project!");

        Repository repository = new ProjectRepository();
        Service service = new ProjectService(repository);


        //Consumer has a ACCEPT method with the object parameter as argument and return as VOID.
        service.findByProjectName("CSA")
                .ifPresent(new Consumer<ProjectDTO>() {
                    @Override
                    public void accept(ProjectDTO projectDTO) {
                        System.out.println(projectDTO.getName());
                    }
                });
        System.out.println("Consumer!");


        //Supplier has a GET method with no argument parameter and returns an object.
        service.findByProjectName("OFAC")
                .orElseGet(new Supplier<ProjectDTO>() {
                    @Override
                    public ProjectDTO get() {
                        return new ProjectDTO();
                    }
                });
        System.out.println("Supplier!");


        //Function has a APPLY method with the object parameter as argument.
        service.findByProjectName("PPL")
                .map(new Function<ProjectDTO, WorkforceDTO>() {
                    @Override
                    public WorkforceDTO apply(ProjectDTO projectDTO) {
                        return new WorkforceDTO();
                    }
                });
        System.out.println("Function!");


        //Predicate has a TEST method with object parameter as argument and boolean as return type.
        service.findByProjectName("OFAC")
                .filter(new Predicate<ProjectDTO>() {
                    @Override
                    public boolean test(ProjectDTO projectDTO) {
                        return false;
                    }
                });
        System.out.println("Predicate!");


        try {
            service.findByProjectName("OFAC")
                    .orElseThrow(new Supplier<Throwable>() {
                        @Override
                        public Throwable get() {
                            return new EntityNotFoundException("OFAC1");
                        }
                    });
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        System.out.println("orElseThrow");

    }


    //DAO Layer
    private interface Repository {
        Optional<Project> findByProjectName(String projectName);
    }

    private static class ProjectRepository implements Repository{

        public Optional<Project> findByProjectName(String projectName){
            return projects.stream()
                    .filter(project -> project.getName().equalsIgnoreCase(projectName))
                    .findFirst();
        }
    }


    //Service Layer
    private interface Service {
        Optional<ProjectDTO> findByProjectName(String projectName);
    }


    private static class ProjectService implements Service {

        private Repository projectRepository;

        public ProjectService(Repository projectRepository){
            this.projectRepository = projectRepository;
        }

        @Override
        public Optional<ProjectDTO> findByProjectName(String projectName) {
            return projectRepository.findByProjectName(projectName)
                    .map(project -> {
                        ProjectDTO projectDTO = new ProjectDTO();
                        projectDTO.setName(project.getName());
                        return projectDTO;
                    });
        }
    }
}
