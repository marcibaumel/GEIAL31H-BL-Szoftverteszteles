package emberek;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class EmberLista {
    private List<String> emberek;

    public EmberLista() {
        this.emberek = new ArrayList<String>();
    }

    public void add(String addEmber){
        if(!emberek.contains(addEmber)){
            emberek.add(addEmber);
        }
    }

    public int getSize(){
        return emberek.size();
    }

    public boolean isEmpty(){
        return emberek.isEmpty();
    }

    public void removeAll(){
        emberek.clear();
    }

    public void remove(String removeEmber){
        if(!emberek.contains(removeEmber)){
            throw new NoSuchElementException();
        }else{
            emberek.remove(removeEmber);
        }
    }

    public boolean isInEmber(String name){
        return emberek.contains(name);
    }
}
