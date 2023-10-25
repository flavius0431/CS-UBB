package Repository;

import Domain.Student;
import Domain.Validator.StudentValidator;

import java.nio.file.Path;
import java.nio.file.Paths;

public class StudentFileRepository extends InMemoryRepository<Long, Student> {
    private String filename;

    public StudentFileRepository(StudentValidator validator, String filename) {
        super(validator);
        this.filename = filename;
        loadData();
    }
    private void loadData(){
        Path path = Paths.get(filename);

    }
}
