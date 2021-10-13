package user;

public class User {
    private final DatabaseConnection databaseConnection;

    public User(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public Integer sumData(String name, String password, int a, int b){
        boolean dcValue = databaseConnection.checkUserPass(name, password);

        if(dcValue){
            return a+b;
        }
        else{
            return null;
        }
    }
}
