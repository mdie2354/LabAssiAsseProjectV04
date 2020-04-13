package LabAssiAsseProjectV04Maven.src.Domain;

import java.util.Objects;

public class Student implements hasID<String> {

    private String idStudent;
    private String nume;
    private int grupa ;
    private String email;
    private String profesor;

    public Student (String id, String n, int gr, String e, String prof){
        this.idStudent=id;
        this.nume=n;
        this.grupa=gr;
        this.email=e;
        this.profesor=prof;
    }

    public String getID(){
        return idStudent;
    }
    public void setID(String id){
        this.idStudent=id;
    }
    public String getNume(){
        return nume;
    }
    public void setNume(String nume){
        this.nume=nume;
    }
    public int getGrupa(){
        return grupa;
    }
    public void setGrupa(int grupa){
        this.grupa=grupa;
    }
    public String getMail(){
        return email;
    }
    public void setMail(String mail){
        this.email=mail;
    }
    public String getProfesor(){
        return profesor;
    }
    public void setProfesor(String prof){
        this.profesor=prof;
    }
    public String toString(){
        return idStudent+' '+nume+' '+grupa+' '+email+' '+profesor;
    }
    public boolean equals(Object object2) {
        return object2 instanceof Student && this.email.equals(((Student) object2).email) && this.grupa==((Student) object2).grupa && this.idStudent.equals(((Student) object2).idStudent) && this.nume.equals(((Student) object2).nume) && this.profesor.equals(((Student) object2).profesor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idStudent, nume, grupa, email, profesor);
    }
}

