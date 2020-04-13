package LabAssiAsseProjectV04Maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import LabAssiAsseProjectV04Maven.src.Domain.Student;
import LabAssiAsseProjectV04Maven.src.Domain.Teme;
import LabAssiAsseProjectV04Maven.src.Repository.StudentRepo;
import LabAssiAsseProjectV04Maven.src.Repository.TemeRepo;
import LabAssiAsseProjectV04Maven.src.Service.ServiceStudent;
import LabAssiAsseProjectV04Maven.src.Service.ServiceTeme;
import LabAssiAsseProjectV04Maven.src.Validator.StudentValidator;
import LabAssiAsseProjectV04Maven.src.Validator.TemeValidator;
import LabAssiAsseProjectV04Maven.src.Validator.ValidationException;
import LabAssiAsseProjectV04Maven.src.Validator.Validator;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for simple App.
 */
public class AppTest
{

    /* Lab 3 - In class*/

    private StudentRepo studentRepo;
    ServiceStudent serviceStudent;

    private TemeRepo temeRepo;
    private ServiceTeme serviceTeme;

    @Before
    public void setUp() {
        studentRepo = new StudentRepo(new StudentValidator(), "studenti.txt");
        serviceStudent = new ServiceStudent(studentRepo);
        temeRepo = new TemeRepo(new TemeValidator(), "teme.txt");
        serviceTeme = new ServiceTeme(temeRepo);
    }

    /**
     * WBT
     */
    @Test
    public void test_invalidAssignment() {
        Teme tema1 = new Teme(-1, "", -1, -2);
        try {
            serviceTeme.add(tema1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nID invalid\nDeadline invalid\nSaptamana in care tema a fost primita este invalida"));
        }
    }

    @Test
    public void test_validAssignment() {
        Teme tema1 = new Teme(1, "Tema1", 1, 2);
        try {
            serviceTeme.add(tema1);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nID invalid\nDeadline invalid\nSaptamana in care tema a fost primita este invalida"));
        }
    }


    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        assertTrue( true );
    }

    /**
     * BBT
     */
    @Test
    public void addStudent1()
    //EC:1,3,5,7,9,11,13,16,19,22
    {
        Student st = new Student("100", "Alex", 934, "alex@email.com", "Professor Professor");
        Student st2 = serviceStudent.add(st);
        //Student s = serviceStudent.find("100");
        assertEquals(st, st2);
    }

    @Test
    public void addStudent2()
    {
        //EC:6, 18; BVA 3
        Student st = new Student("101", "Alexandru", 943, "alex", "Professor professor");
        try {
            serviceStudent.add(st);
            Student s = serviceStudent.find("101");
            assertEquals("Alexandru", s.getNume());
        }
        catch (Exception ex){
            assertEquals("\nGrupa invalida\nEmail invalid", ex.getMessage());
        }
    }

    @Test
    public void addStudent3(){
        //EC:12,17; BVA 1
        Student st = new Student("", "Alex", 902, "alex@email.com", "Prof Prof");
        try {
            serviceStudent.add(st);
        }
        catch (Exception ex){
            assertEquals("\nID invalid\nGrupa invalida", ex.getMessage());
        }
    }

    @Test
    public void addStudent4(){
        //EC:10, 15, 21; BVA 2, 4
        Student st = new Student(null, "Alex", 938, "alex@email.com", "Prof Prof");
        try {
            serviceStudent.add(st);
        }
        catch (Exception ex){
            assertEquals("\nID invalid\nGrupa invalida", ex.getMessage());
        }
    }

    @Test
    public void addStudent5(){
        //EC:8,14,20; BVA 2, 4
        Student st = new Student("abc", "Alex", 110, "alex@email.com", "Prof Prof");
        try {
            serviceStudent.add(st);
        }
        catch (Exception ex){
            assertEquals("\nID invalid\nGrupa invalida", ex.getMessage());
        }
    }

    @Test
    public void addStudent6(){
        //BVA 2,3, 4
        Student st = new Student("90", "Alex", 111, "alex@email.com", "Prof Prof");
        Student st2 = serviceStudent.add(st);
        assertEquals(st, st2);
    }

    @Test
    public void addStudent7(){
        //BVA 2, 4
        Student st = new Student("91", "Alex", 112, "alex@email.com", "Prof Prof");
        Student st2 = serviceStudent.add(st);
        assertEquals(st, st2);
    }

    @Test
    public void addStudent8(){
        //BVA 2, 4
        Student st = new Student("92", "Alex", 936, "alex@email.com", "Prof Prof");
        Student st2 = serviceStudent.add(st);
        assertEquals(st, st2);

    }

    @Test
    public void addStudent9(){
        //BVA 2, 3, 4
        Student st = new Student("93", "Alex", 937, "alex@email.com", "Prof Prof");

        Student st2 = serviceStudent.add(st);
        assertEquals(st, st2);
    }


    @Test
    public void addStudent10() {
        //BVA 3
        Student st = new Student("94", "Alex", 902, "alex@email.com", "Prof Prof");
        try {
            serviceStudent.add(st);
        }
        catch (Exception ex){
            assertEquals("\nGrupa invalida", ex.getMessage());
        }
    }

    @Test
    public void addStudent11() {
        //BVA 3
        Student st = new Student("95", "Alex", 922, "alex@email.com", "Prof Prof");

        Student st2 = serviceStudent.add(st);
        assertEquals(st, st2);
    }

    @Test
    public void addStudent12() {
        //BVA 1
        Student st = new Student("5", "Alex", 922, "alex@email.com", "Prof Prof");

        Student st2 = serviceStudent.add(st);
        assertEquals(st, st2);
    }




    }
