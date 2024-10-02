package org.styly.arcanus.component;

import java.util.Objects;
// A record example
public class ItemMana {
    private int stored;
    private final int limit;

    public ItemMana(int stored,int limit){
        this.limit=limit;
        this.stored=stored;
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.limit,this.stored);
    }
    @Override
    public boolean equals(Object obj) {
        if(obj==this){
            return true;
        } else {
            return obj instanceof ItemMana ex
                    && this.stored == ex.stored
                    && this.limit == ex.limit;
        }
    }
    public int getMana(){
        return stored;
    }
    public int getLimit(){
        return limit;
    }
}
