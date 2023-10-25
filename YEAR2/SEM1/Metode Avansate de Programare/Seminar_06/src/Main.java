import domain.User;
import repository.UserDBRepo;

public class Main {
    public static void main(String[] args) {
        String url = "";
        UserDBRepo userDBRepo = new UserDBRepo(url, "postgres", "postgres");
        User user = new User("flavius", "simbotin");
        userDBRepo.save(user);
        userDBRepo.findAll().forEach((System.out::println));

    }
}