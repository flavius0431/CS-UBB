import Domain.Validator.StudentValidator;
import Repository.StudentFileRepository;

public class TestStudent {
    public static void main(String[] args){
        StudentFileRepository studentFileRepository = new StudentFileRepository(new StudentValidator(),"data/students.txt");
        studentFileRepository.findAll().forEach();
    }
}
