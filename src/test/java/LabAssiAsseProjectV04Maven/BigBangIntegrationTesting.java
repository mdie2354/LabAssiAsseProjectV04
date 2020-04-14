package LabAssiAsseProjectV04Maven;

import LabAssiAsseProjectV04Maven.src.Domain.Nota;
import LabAssiAsseProjectV04Maven.src.Domain.Student;
import LabAssiAsseProjectV04Maven.src.Domain.Teme;
import LabAssiAsseProjectV04Maven.src.Repository.NoteRepo;
import LabAssiAsseProjectV04Maven.src.Repository.StudentRepo;
import LabAssiAsseProjectV04Maven.src.Repository.TemeRepo;
import LabAssiAsseProjectV04Maven.src.Service.ServiceNote;
import LabAssiAsseProjectV04Maven.src.Service.ServiceStudent;
import LabAssiAsseProjectV04Maven.src.Service.ServiceTeme;
import LabAssiAsseProjectV04Maven.src.Validator.NotaValidator;
import LabAssiAsseProjectV04Maven.src.Validator.StudentValidator;
import LabAssiAsseProjectV04Maven.src.Validator.TemeValidator;
import LabAssiAsseProjectV04Maven.src.Validator.ValidationException;
import org.junit.Before;
import org.junit.Test;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.is;

public class BigBangIntegrationTesting {

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {

        assertTrue( true );
    }

    private StudentRepo studentRepo;
    private ServiceStudent serviceStudent;

    private TemeRepo temeRepo;
    private ServiceTeme serviceTeme;

    private NoteRepo noteRepo;
    private ServiceNote serviceNote;

    @Before
    public void setUp(){
        studentRepo = new StudentRepo(new StudentValidator(), "studenti.txt");
        serviceStudent = new ServiceStudent(studentRepo);
        temeRepo = new TemeRepo(new TemeValidator(), "teme.txt");
        serviceTeme = new ServiceTeme(temeRepo);
        noteRepo = new NoteRepo(new NotaValidator());
        serviceNote = new ServiceNote(noteRepo);
    }

    @Test
    public void addGrade() {
        Student s = new Student("1", "Alex", 934, "alex@email.com", "Prof Prof");
        Teme t = new Teme(100, "Tema5", 5, 7);
        Map.Entry<String, Integer> id = new AbstractMap.SimpleEntry<>("1", 100);
        Nota nota = new Nota(id, s, t, 10, 8);
        Nota nota2 = serviceNote.add(nota, "1");
        assertEquals(nota, nota2);
    }

    @Test
    public void addStudent(){
        Student s = new Student("22", "Diana", 934, "diana@diana", "Prof Prof");
        try {
            serviceStudent.add(s);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nEmail invalid"));
        }
    }

    @Test
    public void addTeme(){
        Teme tema = new Teme(0, "Tema2", 1, 2);
        try {
            serviceTeme.add(tema);
        } catch (ValidationException validationException) {
            assertThat(validationException.getMessage(), is("\nID invalid"));
        }
    }

    @Test
    public void BigBang(){
        Student student = new Student("200", "Patricia", 934, "patri@email.com", "Prof Prof");
        Teme teme = new Teme(200, "Tema200", 5, 9);
        Map.Entry<String, Integer> id = new AbstractMap.SimpleEntry<>("200", 200);
        Nota nota = new Nota(id, student, teme, 10, 7);
        Student student1 = serviceStudent.add(student);
        assertEquals(student, student1);

        Teme teme1 = serviceTeme.add(teme);
        assertEquals(teme, teme1);

        Nota nota1 = serviceNote.add(nota, "2");
        assertEquals(nota, nota1);
    }

}
