package Bean;

public class User {
    private String id;
    private String name;
    private String pwd;
    private String type;
    private int age;
    private String classes;

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

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public User(String name, String pwd, int age) {
        this.setName(name);
        this.setPwd(pwd);
        this.setAge(age);

    }


    public User(String id, String name, String pwd,int age) {
        this.setId(id);
        this.setName(name);
        this.setPwd(pwd);
        this.setAge(age);
    }

    public User(String id, String name, String pwd, String type, int age, String classes) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.type = type;
        this.age = age;
        this.classes = classes;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public User(){

    }

    public void showInfo() {
        System.out.println("ID:" + this.getId() + " Name:" + this.getName() + " Password:" + this.getPwd());
    }

}
