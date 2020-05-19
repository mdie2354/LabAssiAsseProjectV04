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


public class IntegrationTesting {

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

    private Student addedStudent;
    private Teme addedTema;

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
    public void addStudent(){
        Student s = new Student("29", "Diana", 934, "diana@email.com", "Prof Prof");
        this.addedStudent = s;
        Student s2 = serviceStudent.add(s);
        assertEquals(s, s2);
    }

    @Test
    public void addStudentandTema(){
        addStudent();

        Teme tema = new Teme(29, "Tema22", 1, 8);
        this.addedTema = tema;
        Teme tema2 = serviceTeme.add(tema);
        assertEquals(tema, tema2);

    }

    @Test
    public void addStudentandTemaandGrade(){
        addStudentandTema();

        Map.Entry<String, Integer> id = new AbstractMap.SimpleEntry<>("22", 22);
        Nota nota = new Nota(id, this.addedStudent, this.addedTema, 9, 6);
        Nota nota2 = serviceNote.add(nota, "1");
        assertEquals(nota, nota2);

    }

}
