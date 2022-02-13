package user;

public interface DatabaseConnection {
    boolean checkUserPass(String name, String password);
}
