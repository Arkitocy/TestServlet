package Bean;

import Comment.KeyUtils;

public class Toy {
    private String id;
    private String name;
    private int amount;

    public Toy() {
    }

    public Toy(String name, int amount) {
        this.id = KeyUtils.genUniqueKey();
        this.name = name;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String show(){
        String rs = getId()+getName()+getAmount();
        return rs;
    }
}
