package Bean;

public class Student {
    private int student_id;
    private String student_name;
    private int student_age;
    private String student_sex;
    private int team_id;

    public Student(String student_name, int student_age, String student_sex, int team_id) {
        this.student_name = student_name;
        this.student_age = student_age;
        this.student_sex = student_sex;
        this.team_id = team_id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public String getStudent_name() {
        return student_name;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public int getStudent_age() {
        return student_age;
    }

    public void setStudent_age(int student_age) {
        this.student_age = student_age;
    }

    public String getStudent_sex() {
        return student_sex;
    }

    public void setStudent_sex(String student_sex) {
        this.student_sex = student_sex;
    }

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }
}
