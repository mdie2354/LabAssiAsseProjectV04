package LabAssiAsseProjectV04Maven;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import LabAssiAsseProjectV04Maven.src.Domain.Student;
import LabAssiAsseProjectV04Maven.src.Repository.StudentRepo;
import LabAssiAsseProjectV04Maven.src.Service.ServiceStudent;
import LabAssiAsseProjectV04Maven.src.Validator.StudentValidator;
import LabAssiAsseProjectV04Maven.src.Validator.Validator;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
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
