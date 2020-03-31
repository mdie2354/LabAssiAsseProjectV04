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

    private TemeRepo temeRepo;
    private ServiceTeme serviceTeme;

    @Before
    public void setUp() {
        temeRepo = new TemeRepo(new TemeValidator(), "teme.txt");
        serviceTeme = new ServiceTeme(temeRepo);
    }

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

    @Test
    public void addStudent1()
    {
        Student st = new Student("100", "Alex", 934, "alex@email.com", "Professor Professor");
        Validator<Student> sv= new StudentValidator();
        StudentRepo repo = new StudentRepo(sv, "studenti.txt");
        ServiceStudent serviceSt = new ServiceStudent(repo);
        serviceSt.add(st);
        Student s = serviceSt.find("100");
        assertEquals("Alex", s.getNume());
    }

    @Test
    public void addStudent2()
    {
        Student st = new Student("101", "Alexandru", 934, "alex", "Professor professor");
        Validator<Student> sv= new StudentValidator();
        StudentRepo repo = new StudentRepo(sv, "studenti.txt");
        ServiceStudent serviceSt = new ServiceStudent(repo);
        try {
            serviceSt.add(st);
            Student s = serviceSt.find("101");
            assertEquals("Alexandru", s.getNume());
        }
        catch (Exception ex){
            assertEquals("\nEmail invalid", ex.getMessage());
        }
    }



}
