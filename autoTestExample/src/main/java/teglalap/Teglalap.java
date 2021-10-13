package teglalap;

public class Teglalap {
    private int a;
    private int b;

    public Teglalap() {
        this.a = 10;
        this.b = 10;
    }

    public int szamitTeglalap(int a, int b){
        if(a != 0 && b !=0){
            return a*b;
        }
        else if(a == 0 || b == 0){
            throw new IllegalArgumentException();
        }
        
    }
}
