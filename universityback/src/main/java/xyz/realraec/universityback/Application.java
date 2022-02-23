package xyz.realraec.universityback;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;
import xyz.realraec.universityback.repository.CourseRepository;
import xyz.realraec.universityback.repository.DegreeRepository;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.repository.StudentRepository;
import xyz.realraec.universityback.enumeration.Gender;

import java.util.HashSet;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // To be picked up by Spring
    @Bean
    CommandLineRunner run(StudentRepository studentRepository, ProfessorRepository professorRepository,
                          CourseRepository courseRepository, DegreeRepository degreeRepository) {
        return args -> {

            Course courseAlgebra100 = new Course("Algebra 100");
            courseAlgebra100.setCode("C1");
            courseRepository.save(courseAlgebra100);
            Course courseAlgebra200 = new Course("Algebra 200");
            courseRepository.save(courseAlgebra200);
            Course courseAlgebra300 = new Course("Algebra 300");
            courseRepository.save(courseAlgebra300);

            Course courseGeometry100 = new Course("Geometry 100");
            courseRepository.save(courseGeometry100);
            Course courseGeometry200 = new Course("Geometry 200");
            courseRepository.save(courseGeometry200);
            Course courseGeometry300 = new Course("Geometry 300");
            courseRepository.save(courseGeometry300);

            Course courseStatistics100 = new Course("Statistics 100");
            courseRepository.save(courseStatistics100);
            Course courseStatistics200 = new Course("Statistics 200");
            courseRepository.save(courseStatistics200);
            Course courseStatistics300 = new Course("Statistics 300");
            courseRepository.save(courseStatistics300);


            Course courseEnglish100 = new Course("English 100");
            courseEnglish100.setCode("C2");
            courseRepository.save(courseEnglish100);
            Course courseEnglish200 = new Course("English 200");
            courseRepository.save(courseEnglish200);
            Course courseEnglish300 = new Course("English 300");
            courseRepository.save(courseEnglish300);

            Course courseFrench100 = new Course("French 100");
            courseRepository.save(courseFrench100);
            Course courseFrench200 = new Course("French 200");
            courseRepository.save(courseFrench200);
            Course courseFrench300 = new Course("French 300");
            courseRepository.save(courseFrench300);

            Course courseGerman100 = new Course("German 100");
            courseRepository.save(courseGerman100);
            Course courseGerman200 = new Course("German 200");
            courseRepository.save(courseGerman200);
            Course courseGerman300 = new Course("German 300");
            courseRepository.save(courseGerman300);


            Course courseUSHistory100 = new Course("US History 100");
            courseRepository.save(courseUSHistory100);
            Course courseUSHistory200 = new Course("US History 200");
            courseRepository.save(courseUSHistory200);
            Course courseUSHistory300 = new Course("US History 300");
            courseRepository.save(courseUSHistory300);

            Course courseUKHistory100 = new Course("UK History 100");
            courseRepository.save(courseUKHistory100);
            Course courseUKHistory200 = new Course("UK History 200");
            courseRepository.save(courseUKHistory200);
            Course courseUKHistory300 = new Course("UK History 300");
            courseRepository.save(courseUKHistory300);

            Course courseFrenchHistory100 = new Course("French History 100");
            courseRepository.save(courseFrenchHistory100);
            Course courseFrenchHistory200 = new Course("French History 200");
            courseRepository.save(courseFrenchHistory200);
            Course courseFrenchHistory300 = new Course("French History 300");
            courseRepository.save(courseFrenchHistory300);


            Course courseMethodology100 = new Course("Methodology 100");
            courseRepository.save(courseMethodology100);
            Course courseMethodology200 = new Course("Methodology 200");
            courseRepository.save(courseMethodology200);
            Course courseMethodology300 = new Course("Methodology 300");
            courseRepository.save(courseMethodology300);


            // -----------------------------------------------------------------------


            Degree degreeMaths100 = new Degree("Maths 100");
            Degree degreeMaths200 = new Degree("Maths 200");
            Degree degreeMaths300 = new Degree("Maths 300");
            Degree degreeLanguages100 = new Degree("Languages 100");
            Degree degreeLanguages200 = new Degree("Languages 200");
            Degree degreeLanguages300 = new Degree("Languages 300");
            Degree degreeHistory100 = new Degree("History 100");
            Degree degreeHistory200 = new Degree("History 200");
            Degree degreeHistory300 = new Degree("History 300");


            degreeMaths100.setCode("D1");
            degreeMaths100.setCourses(new HashSet<>() {{
                add(courseAlgebra100);
                add(courseGeometry100);
                add(courseStatistics100);
            }});
            degreeRepository.save(degreeMaths100);
            degreeMaths200.setCourses(new HashSet<>() {{
                add(courseAlgebra200);
                add(courseGeometry200);
                add(courseStatistics200);
                add(courseMethodology200);
            }});
            degreeRepository.save(degreeMaths200);
            degreeMaths300.setCourses(new HashSet<>() {{
                add(courseAlgebra300);
                add(courseGeometry300);
                add(courseStatistics300);
            }});
            degreeRepository.save(degreeMaths300);

            degreeLanguages100.setCourses(new HashSet<>() {{
                add(courseEnglish100);
                add(courseFrench100);
                add(courseGerman100);
                add(courseMethodology100);
            }});
            degreeRepository.save(degreeLanguages100);

            degreeLanguages200.setCourses(new HashSet<>() {{
                add(courseEnglish200);
                add(courseFrench200);
                add(courseGerman200);
            }});
            degreeRepository.save(degreeLanguages200);

            degreeLanguages300.setCourses(new HashSet<>() {{
                add(courseEnglish300);
                add(courseFrench300);
                add(courseGerman300);
            }});
            degreeRepository.save(degreeLanguages300);

            degreeHistory100.setCode("D2");
            degreeHistory100.setCourses(new HashSet<>() {{
                add(courseUSHistory100);
                add(courseUKHistory100);
                add(courseFrenchHistory100);
            }});
            degreeRepository.save(degreeHistory100);

            degreeHistory200.setCourses(new HashSet<>() {{
                add(courseUSHistory200);
                add(courseUKHistory200);
                add(courseFrenchHistory200);
            }});
            degreeRepository.save(degreeHistory200);

            degreeHistory300.setCourses(new HashSet<>() {{
                add(courseUSHistory300);
                add(courseUKHistory300);
                add(courseFrenchHistory300);
                add(courseMethodology300);
            }});
            degreeRepository.save(degreeHistory300);


            Degree degreePhysics100 = new Degree("Physics 100");
            degreeRepository.save(degreePhysics100);
            Degree degreeLinguistics200 = new Degree("Linguistics 200");
            degreeRepository.save(degreeLinguistics200);
            Degree degreeEconomics300 = new Degree("Economics 300");
            degreeRepository.save(degreeEconomics300);


            // -----------------------------------------------------------------------


            Professor professor1 = new Professor("Fleming", "Adam", Gender.GENDER_MALE);
            professor1.setCode("P1");
            professorRepository.save(professor1);
            Professor professor2 = new Professor("Veal", "Ann", Gender.GENDER_FEMALE);
            professor2.setCode("P2");
            professorRepository.save(professor2);
            Professor professor3 = new Professor("Sugar", "Barry", Gender.GENDER_MALE);
            professorRepository.save(professor3);
            Professor professor4 = new Professor("Millington", "Presley", Gender.GENDER_MALE);
            professorRepository.save(professor4);
            Professor professor5 = new Professor("Alexander", "Allan", Gender.GENDER_MALE);
            professorRepository.save(professor5);
            Professor professor6 = new Professor("Knapp", "Dora", Gender.GENDER_FEMALE);
            professorRepository.save(professor6);
            Professor professor7 = new Professor("Plop", "Appa", Gender.GENDER_MALE);
            professorRepository.save(professor7);
            /*professorRepository.save(new Professor("Bluth", "Buster", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Riggo", "Georgette", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Bally", "George Michael", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Sanchez", "Kitty", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Blob", "Lindsay", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Austero", "Lucille", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Fluke", "Maeby", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Ruth", "Michael", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Holt", "Steve", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Flu", "Tobias", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Tate", "Larry", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Estate", "Larry", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Blob", "Samantha", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Stephens", "Adam", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Veal", "Libbi", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Humphrey", "Jon-Paul", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Wilcox", "Ernest", Gender.GENDER_MALE));
            professorRepository.save(new Professor("McManus", "Cristina", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("O'Sullivan", "Siraj", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Freeman", "Coco", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Blob", "Morgan", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Blake", "Wilma", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Ford", "Laila", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Ruth", "Noah", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Schofield", "Morris", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Boyle", "Jae", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Underwood", "Hamzah", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Estate", "Herman", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Potter", "Milana", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Pruitt", "Allen", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Veal", "Dania", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Aguirre", "Raymond", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Bluth", "Marlon", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Tang", "Alyce", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Leach", "Atlas", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Herring", "Wendy", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Zuniga", "Sumaiya", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Bentley", "Tamia", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Cresswell", "Paulina", Gender.GENDER_FEMALE));
            professorRepository.save(new Professor("Sandoval", "Justin", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Mercado", "Hayden", Gender.GENDER_MALE));
            professorRepository.save(new Professor("Magana", "Theo", Gender.GENDER_MALE));*/


            // -----------------------------------------------------------------------


            courseAlgebra100.setProfessor(professor1);
            courseRepository.save(courseAlgebra100);
            courseAlgebra200.setProfessor(professor2);
            courseRepository.save(courseAlgebra200);
            courseAlgebra300.setProfessor(professor3);
            courseRepository.save(courseAlgebra300);

            courseGeometry100.setProfessor(professor2);
            courseRepository.save(courseGeometry100);
            courseGeometry200.setProfessor(professor3);
            courseRepository.save(courseGeometry200);
            courseGeometry300.setProfessor(professor1);
            courseRepository.save(courseGeometry300);

            courseStatistics100.setProfessor(professor3);
            courseRepository.save(courseStatistics100);
            courseStatistics200.setProfessor(professor1);
            courseRepository.save(courseStatistics200);
            courseStatistics300.setProfessor(professor2);
            courseRepository.save(courseStatistics300);


            courseEnglish100.setProfessor(professor4);
            courseRepository.save(courseEnglish100);
            courseEnglish200.setProfessor(professor4);
            courseRepository.save(courseEnglish200);
            courseEnglish300.setProfessor(professor4);
            courseRepository.save(courseEnglish300);

            courseGerman100.setProfessor(professor5);
            courseRepository.save(courseGerman100);
            courseGerman200.setProfessor(professor5);
            courseRepository.save(courseGerman200);
            courseGerman300.setProfessor(professor5);
            courseRepository.save(courseGerman300);

            courseFrench100.setProfessor(professor6);
            courseRepository.save(courseFrench100);
            courseFrench200.setProfessor(professor6);
            courseRepository.save(courseFrench200);
            courseFrench300.setProfessor(professor6);
            courseRepository.save(courseFrench300);


            courseUKHistory100.setProfessor(professor4);
            courseRepository.save(courseUKHistory100);
            courseUKHistory200.setProfessor(professor4);
            courseRepository.save(courseUKHistory200);
            courseUKHistory300.setProfessor(professor4);
            courseRepository.save(courseUKHistory300);

            courseUSHistory100.setProfessor(professor4);
            courseRepository.save(courseUSHistory100);
            courseUSHistory200.setProfessor(professor4);
            courseRepository.save(courseUSHistory200);
            courseUSHistory300.setProfessor(professor4);
            courseRepository.save(courseUSHistory300);

            courseFrenchHistory100.setProfessor(professor6);
            courseRepository.save(courseFrenchHistory100);
            courseFrenchHistory200.setProfessor(professor6);
            courseRepository.save(courseFrenchHistory200);
            courseFrenchHistory300.setProfessor(professor6);
            courseRepository.save(courseFrenchHistory300);


            courseMethodology100.setProfessor(professor7);
            courseRepository.save(courseMethodology100);
            courseMethodology200.setProfessor(professor7);
            courseRepository.save(courseMethodology200);
            courseMethodology300.setProfessor(professor7);
            courseRepository.save(courseMethodology300);


            // -----------------------------------------------------------------------


            Student student1 = new Student("Karla","Travers",  Gender.GENDER_FEMALE);
            Student student2 = new Student("Barry","Rooney",  Gender.GENDER_MALE);
            Student student3 = new Student("Prentice", "Nathanael", Gender.GENDER_MALE);
            Student student4 = new Student("Lane", "Patrick", Gender.GENDER_OTHER);
            Student student5 = new Student("Bolton", "Alexis", Gender.GENDER_FEMALE);
            Student student6 = new Student("Lara", "Adam", Gender.GENDER_MALE);
            Student student7 = new Student("Mccray","Lily",  Gender.GENDER_FEMALE);
            Student student8 = new Student("Richards", "Devon", Gender.GENDER_OTHER);
            Student student9 = new Student("Hurley", "Rhona", Gender.GENDER_FEMALE);
            Student student10 = new Student("Finnegan", "Lindsay", Gender.GENDER_FEMALE);
            Student student11 = new Student("Mcgrath", "Lucille", Gender.GENDER_FEMALE);
            Student student12 = new Student("Dudley", "Anna-Marie", Gender.GENDER_FEMALE);
            Student student13 = new Student("David", "Brian", Gender.GENDER_MALE);
            Student student14 = new Student("Grant", "Taylor", Gender.GENDER_MALE);
            Student student15 = new Student("Casey", "Ace", Gender.GENDER_MALE);
            Student student16 = new Student("Cartwright", "Tom", Gender.GENDER_MALE);
            Student student17 = new Student("Fowler", "Aarav", Gender.GENDER_MALE);
            Student student18 = new Student("Coles", "Samantha", Gender.GENDER_FEMALE);
            Student student19 = new Student("Gates", "Gurpreet", Gender.GENDER_MALE);
            Student student20 = new Student("Stark", "Ann", Gender.GENDER_FEMALE);
            Student student21 = new Student("Hanson", "Cairon", Gender.GENDER_MALE);
            Student student22 = new Student("Leigh", "Balraj", Gender.GENDER_MALE);
            Student student23 = new Student("Rosario", "Zaara", Gender.GENDER_FEMALE);
            Student student24 = new Student("Murray","Johnson",  Gender.GENDER_MALE);
            Student student25 = new Student("Kaur", "Rosemary", Gender.GENDER_FEMALE);
            Student student26 = new Student("Kennedy", "Cherish", Gender.GENDER_FEMALE);
            Student student27 = new Student("Wall", "Whitney", Gender.GENDER_FEMALE);
            Student student28 = new Student("Cowan", "Bea", Gender.GENDER_FEMALE);
            Student student29 = new Student("Acevedo", "Cohan", Gender.GENDER_MALE);
            Student student30 = new Student("Magana", "Aamir", Gender.GENDER_MALE);
            Student student31 = new Student("Carlson", "Simon", Gender.GENDER_MALE);
            Student student32 = new Student("Hayward", "Ada", Gender.GENDER_MALE);
            Student student33 = new Student("Davis", "Derek", Gender.GENDER_MALE);
            Student student34 = new Student("Walker", "Haley", Gender.GENDER_FEMALE);
            Student student35 = new Student("Sharma", "Adam", Gender.GENDER_MALE);
            Student student36 = new Student("Nunez", "Layan", Gender.GENDER_FEMALE);
            Student student37 = new Student("Madden", "Lucien", Gender.GENDER_MALE);
            Student student38 = new Student("Mcclure", "Bronwen", Gender.GENDER_MALE);
            Student student39 = new Student("Cooke", "Mary", Gender.GENDER_FEMALE);
            Student student40 = new Student("Copeland", "Chaim", Gender.GENDER_MALE);
            Student student41 = new Student("Roberts", "Amber", Gender.GENDER_FEMALE);
            Student student42 = new Student("Boyd", "Marlie", Gender.GENDER_FEMALE);
            Student student43 = new Student("Guerrero", "Jaimee", Gender.GENDER_FEMALE);
            Student student44 = new Student("Lutz", "Dylan", Gender.GENDER_FEMALE);
            Student student45 = new Student("Talley", "Michael", Gender.GENDER_MALE);
            Student student46 = new Student("Little", "Tyson", Gender.GENDER_MALE);
            Student student47 = new Student("Ferry", "Cassius", Gender.GENDER_MALE);
            Student student48 = new Student("Goff", "Larry", Gender.GENDER_MALE);
            Student student49 = new Student("Philip", "Bradley", Gender.GENDER_MALE);


            student1.setCode("S1");
            student1.setMajorDegree(degreeMaths100);
            student1.setMinorDegree(degreeLanguages100);
            studentRepository.save(student1);
            student2.setMajorDegree(degreeMaths100);
            student2.setMinorDegree(degreeHistory100);
            studentRepository.save(student2);
            student3.setCode("S2");
            student3.setMajorDegree(degreeHistory200);
            student3.setMinorDegree(degreeMaths200);
            studentRepository.save(student3);
            student4.setMajorDegree(degreeEconomics300);
            student4.setMinorDegree(degreeHistory300);
            studentRepository.save(student4);
            student5.setMajorDegree(degreeMaths100);
            student5.setMinorDegree(degreePhysics100);
            studentRepository.save(student5);
            student6.setMajorDegree(degreeLanguages300);
            student6.setMinorDegree(degreeMaths300);
            studentRepository.save(student6);
            student7.setMajorDegree(degreeMaths100);
            student7.setMinorDegree(degreeLanguages100);
            studentRepository.save(student7);
            student8.setMajorDegree(degreeMaths100);
            student8.setMinorDegree(degreeLanguages100);
            studentRepository.save(student8);
            student9.setMajorDegree(degreeLanguages200);
            student9.setMinorDegree(degreeHistory200);
            studentRepository.save(student9);
            student10.setMajorDegree(degreeHistory200);
            student10.setMinorDegree(degreeMaths200);
            studentRepository.save(student10);
            student11.setMajorDegree(degreeLanguages300);
            student11.setMinorDegree(degreeHistory300);
            studentRepository.save(student11);
            student12.setMajorDegree(degreeMaths100);
            student12.setMinorDegree(degreeHistory100);
            studentRepository.save(student12);
            student13.setMajorDegree(degreeLanguages300);
            student13.setMinorDegree(degreeMaths300);
            studentRepository.save(student13);
            student14.setMajorDegree(degreePhysics100);
            student14.setMinorDegree(degreeLanguages100);
            studentRepository.save(student14);
            student15.setMajorDegree(degreeLanguages100);
            student15.setMinorDegree(degreeMaths100);
            studentRepository.save(student15);
            student16.setMajorDegree(degreeMaths100);
            student16.setMinorDegree(degreePhysics100);
            studentRepository.save(student16);
            student17.setMajorDegree(degreeHistory200);
            student17.setMinorDegree(degreeLanguages200);
            studentRepository.save(student17);
            student18.setMajorDegree(degreeMaths300);
            student18.setMinorDegree(degreeHistory300);
            studentRepository.save(student18);
            student19.setMajorDegree(degreeMaths100);
            student19.setMinorDegree(degreePhysics100);
            studentRepository.save(student19);
            student20.setMajorDegree(degreeLanguages300);
            student20.setMinorDegree(degreeHistory300);
            studentRepository.save(student20);
            student21.setMajorDegree(degreeMaths100);
            student21.setMinorDegree(degreeLanguages100);
            studentRepository.save(student21);
            student22.setMajorDegree(degreeMaths100);
            student22.setMinorDegree(degreeLanguages100);
            studentRepository.save(student22);
            student23.setMajorDegree(degreeMaths100);
            student23.setMinorDegree(degreeLanguages100);
            studentRepository.save(student23);
            student24.setMajorDegree(degreeLanguages200);
            student24.setMinorDegree(degreeMaths200);
            studentRepository.save(student24);
            student25.setMajorDegree(degreeEconomics300);
            student25.setMinorDegree(degreeHistory300);
            studentRepository.save(student25);
            student26.setMajorDegree(degreeMaths100);
            student26.setMinorDegree(degreePhysics100);
            studentRepository.save(student26);
            student27.setMajorDegree(degreeEconomics300);
            student27.setMinorDegree(degreeMaths300);
            studentRepository.save(student27);
            student28.setMajorDegree(degreeHistory100);
            student28.setMinorDegree(degreeLanguages100);
            studentRepository.save(student28);
            student29.setMajorDegree(degreeMaths100);
            student29.setMinorDegree(degreeLanguages100);
            studentRepository.save(student29);
            student30.setMajorDegree(degreeMaths100);
            student30.setMinorDegree(degreeHistory100);
            studentRepository.save(student30);
            student31.setMajorDegree(degreeHistory200);
            student31.setMinorDegree(degreeMaths200);
            studentRepository.save(student31);
            student32.setMajorDegree(degreeEconomics300);
            student32.setMinorDegree(degreeHistory300);
            studentRepository.save(student32);
            student33.setMajorDegree(degreeMaths100);
            student33.setMinorDegree(degreePhysics100);
            studentRepository.save(student33);
            student34.setMajorDegree(degreeLanguages300);
            student34.setMinorDegree(degreeMaths300);
            studentRepository.save(student34);
            student35.setMajorDegree(degreeHistory100);
            student35.setMinorDegree(degreeLanguages100);
            studentRepository.save(student35);
            student36.setMajorDegree(degreeMaths100);
            student36.setMinorDegree(degreeHistory100);
            studentRepository.save(student36);
            student37.setMajorDegree(degreeLanguages100);
            student37.setMinorDegree(degreeHistory100);
            studentRepository.save(student37);
            student38.setMajorDegree(degreeLinguistics200);
            student38.setMinorDegree(degreeMaths200);
            studentRepository.save(student38);
            student39.setMajorDegree(degreeEconomics300);
            student39.setMinorDegree(degreeMaths300);
            studentRepository.save(student39);
            student40.setMajorDegree(degreeHistory100);
            student40.setMinorDegree(degreePhysics100);
            studentRepository.save(student40);
            student41.setMajorDegree(degreeEconomics300);
            student41.setMinorDegree(degreeMaths300);
            studentRepository.save(student41);
            student42.setMajorDegree(degreeMaths100);
            student42.setMinorDegree(degreeLanguages100);
            studentRepository.save(student42);
            student43.setMajorDegree(degreeMaths100);
            student43.setMinorDegree(degreeLanguages100);
            studentRepository.save(student43);
            student44.setMajorDegree(degreeMaths100);
            student44.setMinorDegree(degreeHistory100);
            studentRepository.save(student44);
            student45.setMajorDegree(degreeHistory200);
            student45.setMinorDegree(degreeMaths200);
            studentRepository.save(student45);
            student46.setMajorDegree(degreeEconomics300);
            student46.setMinorDegree(degreeLanguages200);
            studentRepository.save(student46);
            student47.setMajorDegree(degreeMaths100);
            student47.setMinorDegree(degreePhysics100);
            studentRepository.save(student47);
            student48.setMajorDegree(degreeLanguages300);
            student48.setMinorDegree(degreeMaths300);
            studentRepository.save(student48);
            student49.setMajorDegree(degreeMaths100);
            student49.setMinorDegree(degreeLanguages100);
            studentRepository.save(student49);


            professorRepository.flush();
            studentRepository.flush();
            courseRepository.flush();
            degreeRepository.flush();



        };
    }

}
