package xyz.realraec.universityback.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import xyz.realraec.universityback.controller.StudentController;
import xyz.realraec.universityback.enumeration.Gender;
import xyz.realraec.universityback.model.Course;
import xyz.realraec.universityback.model.Degree;
import xyz.realraec.universityback.model.Professor;
import xyz.realraec.universityback.model.Student;
import xyz.realraec.universityback.repository.CourseRepository;
import xyz.realraec.universityback.repository.DegreeRepository;
import xyz.realraec.universityback.repository.ProfessorRepository;
import xyz.realraec.universityback.repository.StudentRepository;

import java.util.List;

@Configuration
public class Config {

    // To be picked up by Spring
    @Bean
    CommandLineRunner run(StudentRepository studentRepository, ProfessorRepository professorRepository,
                          CourseRepository courseRepository, DegreeRepository degreeRepository) {
        return args -> {

            Course courseAlgebra100 = new Course("Algebra 100");
            courseAlgebra100.setCode("C1");
            Course courseAlgebra200 = new Course("Algebra 200");
            Course courseAlgebra300 = new Course("Algebra 300");

            Course courseGeometry100 = new Course("Geometry 100");
            Course courseGeometry200 = new Course("Geometry 200");
            Course courseGeometry300 = new Course("Geometry 300");

            Course courseStatistics100 = new Course("Statistics 100");
            Course courseStatistics200 = new Course("Statistics 200");
            Course courseStatistics300 = new Course("Statistics 300");

            Course courseEnglish100 = new Course("English 100");
            courseEnglish100.setCode("C2");
            Course courseEnglish200 = new Course("English 200");
            Course courseEnglish300 = new Course("English 300");

            Course courseFrench100 = new Course("French 100");
            Course courseFrench200 = new Course("French 200");
            Course courseFrench300 = new Course("French 300");

            Course courseGerman100 = new Course("German 100");
            Course courseGerman200 = new Course("German 200");
            Course courseGerman300 = new Course("German 300");

            Course courseUSHistory100 = new Course("US History 100");
            Course courseUSHistory200 = new Course("US History 200");
            Course courseUSHistory300 = new Course("US History 300");

            Course courseUKHistory100 = new Course("UK History 100");
            Course courseUKHistory200 = new Course("UK History 200");
            Course courseUKHistory300 = new Course("UK History 300");

            Course courseFrenchHistory100 = new Course("French History 100");
            Course courseFrenchHistory200 = new Course("French History 200");
            Course courseFrenchHistory300 = new Course("French History 300");

            Course courseMethodology100 = new Course("Methodology 100");
            Course courseMethodology200 = new Course("Methodology 200");
            Course courseMethodology300 = new Course("Methodology 300");

            courseRepository.saveAll(List.of(
                    courseAlgebra100, courseAlgebra200, courseAlgebra300,
                    courseGeometry100, courseGeometry200, courseGeometry300,
                    courseStatistics100, courseStatistics200, courseStatistics300,
                    courseEnglish100, courseEnglish200, courseEnglish300,
                    courseFrench100, courseFrench200, courseFrench300,
                    courseGerman100, courseGerman200, courseGerman300,
                    courseUSHistory100, courseUSHistory200, courseUSHistory300,
                    courseUKHistory100, courseUKHistory200, courseUKHistory300,
                    courseFrenchHistory100, courseFrenchHistory200, courseFrenchHistory300,
                    courseMethodology100, courseMethodology200, courseMethodology300
            ));


            // -----------------------------------------------------------------------


            Degree degreeMaths100 = new Degree("Maths 100");
            degreeMaths100.setCode("D1");
            Degree degreeMaths200 = new Degree("Maths 200");
            Degree degreeMaths300 = new Degree("Maths 300");
            Degree degreeLanguages100 = new Degree("Languages 100");
            Degree degreeLanguages200 = new Degree("Languages 200");
            Degree degreeLanguages300 = new Degree("Languages 300");
            Degree degreeHistory100 = new Degree("History 100");
            degreeHistory100.setCode("D2");
            Degree degreeHistory200 = new Degree("History 200");
            Degree degreeHistory300 = new Degree("History 300");
            Degree degreePhysics100 = new Degree("Physics 100");
            Degree degreeLinguistics200 = new Degree("Linguistics 200");
            Degree degreeEconomics300 = new Degree("Economics 300");

            /*degreeMaths100.setCourses(new HashSet<>() {{
                add(courseAlgebra100);
                add(courseGeometry100);
                add(courseStatistics100);
            }});*/
            degreeMaths100.addCourse(courseAlgebra100);
            degreeMaths100.addCourse(courseGeometry100);
            degreeMaths100.addCourse(courseStatistics100);

            /*degreeMaths200.setCourses(new HashSet<>() {{
                add(courseAlgebra200);
                add(courseGeometry200);
                add(courseStatistics200);
                add(courseMethodology200);
            }});*/
            degreeMaths200.addCourse(courseAlgebra200);
            degreeMaths200.addCourse(courseGeometry200);
            degreeMaths200.addCourse(courseStatistics200);
            degreeMaths200.addCourse(courseMethodology200);

            /*degreeMaths300.setCourses(new HashSet<>() {{
                add(courseAlgebra300);
                add(courseGeometry300);
                add(courseStatistics300);
            }});*/
            degreeMaths300.addCourse(courseAlgebra300);
            degreeMaths300.addCourse(courseGeometry300);
            degreeMaths300.addCourse(courseStatistics300);

            /*degreeLanguages100.setCourses(new HashSet<>() {{
                add(courseEnglish100);
                add(courseFrench100);
                add(courseGerman100);
                add(courseMethodology100);
            }});*/
            degreeLanguages100.addCourse(courseEnglish100);
            degreeLanguages100.addCourse(courseFrench100);
            degreeLanguages100.addCourse(courseGerman100);
            degreeLanguages100.addCourse(courseMethodology100);

            /*degreeLanguages200.setCourses(new HashSet<>() {{
                add(courseEnglish200);
                add(courseFrench200);
                add(courseGerman200);
            }});*/
            degreeLanguages200.addCourse(courseEnglish200);
            degreeLanguages200.addCourse(courseFrench200);
            degreeLanguages200.addCourse(courseGerman200);

            /*degreeLanguages300.setCourses(new HashSet<>() {{
                add(courseEnglish300);
                add(courseFrench300);
                add(courseGerman300);
            }});*/
            degreeLanguages300.addCourse(courseEnglish300);
            degreeLanguages300.addCourse(courseFrench300);
            degreeLanguages300.addCourse(courseGerman300);

            /*degreeHistory100.setCourses(new HashSet<>() {{
                add(courseUSHistory100);
                add(courseUKHistory100);
                add(courseFrenchHistory100);
            }});*/
            degreeHistory100.addCourse(courseUSHistory100);
            degreeHistory100.addCourse(courseUKHistory100);
            degreeHistory100.addCourse(courseFrenchHistory100);

            /*degreeHistory200.setCourses(new HashSet<>() {{
                add(courseUSHistory200);
                add(courseUKHistory200);
                add(courseFrenchHistory200);
            }});*/
            degreeHistory200.addCourse(courseUSHistory200);
            degreeHistory200.addCourse(courseUKHistory200);
            degreeHistory200.addCourse(courseFrenchHistory200);

            /*degreeHistory300.setCourses(new HashSet<>() {{
                add(courseUSHistory300);
                add(courseUKHistory300);
                add(courseFrenchHistory300);
                add(courseMethodology300);
            }});*/
            degreeHistory300.addCourse(courseUSHistory300);
            degreeHistory300.addCourse(courseUKHistory300);
            degreeHistory300.addCourse(courseFrenchHistory300);
            degreeHistory300.addCourse(courseMethodology300);

            degreeRepository.saveAll(List.of(
                    degreeMaths100, degreeMaths200, degreeMaths300,
                    degreeLanguages100, degreeLanguages200, degreeLanguages300,
                    degreeHistory100, degreeHistory200, degreeHistory300,
                    degreePhysics100, degreeLinguistics200, degreeEconomics300
            ));


            // -----------------------------------------------------------------------


            Professor professor1 = new Professor("Fleming", "Adam", Gender.GENDER_MALE);
            professor1.setCode("P1");
            Professor professor2 = new Professor("Veal", "Ann", Gender.GENDER_FEMALE);
            professor2.setCode("P2");
            Professor professor3 = new Professor("Sugar", "Barry", Gender.GENDER_MALE);
            Professor professor4 = new Professor("Millington", "Presley", Gender.GENDER_MALE);
            Professor professor5 = new Professor("Alexander", "Allan", Gender.GENDER_MALE);
            Professor professor6 = new Professor("Knapp", "Dora", Gender.GENDER_FEMALE);
            Professor professor7 = new Professor("Plop", "Appa", Gender.GENDER_MALE);

            professorRepository.saveAll(List.of(
                    professor1, professor2, professor3, professor4, professor5, professor6, professor7
            ));


            // -----------------------------------------------------------------------


            courseAlgebra100.setProfessor(professor1);
            courseAlgebra200.setProfessor(professor2);
            courseAlgebra300.setProfessor(professor3);

            courseGeometry100.setProfessor(professor2);
            courseGeometry200.setProfessor(professor3);
            courseGeometry300.setProfessor(professor1);

            courseStatistics100.setProfessor(professor3);
            courseStatistics200.setProfessor(professor1);
            courseStatistics300.setProfessor(professor2);

            courseEnglish100.setProfessor(professor4);
            courseEnglish200.setProfessor(professor4);
            courseEnglish300.setProfessor(professor4);

            courseGerman100.setProfessor(professor5);
            courseGerman200.setProfessor(professor5);
            courseGerman300.setProfessor(professor5);

            courseFrench100.setProfessor(professor6);
            courseFrench200.setProfessor(professor6);
            courseFrench300.setProfessor(professor6);

            courseUKHistory100.setProfessor(professor4);
            courseUKHistory200.setProfessor(professor4);
            courseUKHistory300.setProfessor(professor4);

            courseUSHistory100.setProfessor(professor4);
            courseUSHistory200.setProfessor(professor4);
            courseUSHistory300.setProfessor(professor4);

            courseFrenchHistory100.setProfessor(professor6);
            courseFrenchHistory200.setProfessor(professor6);
            courseFrenchHistory300.setProfessor(professor6);

            courseMethodology100.setProfessor(professor7);
            courseMethodology200.setProfessor(professor7);
            courseMethodology300.setProfessor(professor7);


            courseRepository.saveAll(List.of(
                    courseAlgebra100, courseAlgebra200, courseAlgebra300,
                    courseGeometry100, courseGeometry200, courseGeometry300,
                    courseStatistics100, courseStatistics200, courseStatistics300,
                    courseEnglish100, courseEnglish200, courseEnglish300,
                    courseFrench100, courseFrench200, courseFrench300,
                    courseGerman100, courseGerman200, courseGerman300,
                    courseUSHistory100, courseUSHistory200, courseUSHistory300,
                    courseUKHistory100, courseUKHistory200, courseUKHistory300,
                    courseFrenchHistory100, courseFrenchHistory200, courseFrenchHistory300,
                    courseMethodology100, courseMethodology200, courseMethodology300
            ));


            // -----------------------------------------------------------------------


            Student student1 = new Student("Karla", "Travers", Gender.GENDER_FEMALE);
            Student student2 = new Student("Barry", "Rooney", Gender.GENDER_MALE);
            Student student3 = new Student("Prentice", "Nathanael", Gender.GENDER_MALE);
            Student student4 = new Student("Lane", "Patrick", Gender.GENDER_OTHER);
            Student student5 = new Student("Bolton", "Alexis", Gender.GENDER_FEMALE);
            Student student6 = new Student("Lara", "Adam", Gender.GENDER_MALE);
            Student student7 = new Student("Mccray", "Lily", Gender.GENDER_FEMALE);
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
            Student student24 = new Student("Murray", "Johnson", Gender.GENDER_MALE);
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
            student2.setMajorDegree(degreeMaths100);
            student2.setMinorDegree(degreeHistory100);
            student3.setCode("S2");
            student3.setMajorDegree(degreeHistory200);
            student3.setMinorDegree(degreeMaths200);
            student4.setMajorDegree(degreeEconomics300);
            student4.setMinorDegree(degreeHistory300);
            student5.setMajorDegree(degreeMaths100);
            student5.setMinorDegree(degreePhysics100);
            student6.setMajorDegree(degreeLanguages300);
            student6.setMinorDegree(degreeMaths300);
            student7.setMajorDegree(degreeMaths100);
            student7.setMinorDegree(degreeLanguages100);
            student8.setMajorDegree(degreeMaths100);
            student8.setMinorDegree(degreeLanguages100);
            student9.setMajorDegree(degreeLanguages200);
            student9.setMinorDegree(degreeHistory200);
            student10.setMajorDegree(degreeHistory200);
            student10.setMinorDegree(degreeMaths200);
            student11.setMajorDegree(degreeLanguages300);
            student11.setMinorDegree(degreeHistory300);
            student12.setMajorDegree(degreeMaths100);
            student12.setMinorDegree(degreeHistory100);
            student13.setMajorDegree(degreeLanguages300);
            student13.setMinorDegree(degreeMaths300);
            student14.setMajorDegree(degreePhysics100);
            student14.setMinorDegree(degreeLanguages100);
            student15.setMajorDegree(degreeLanguages100);
            student15.setMinorDegree(degreeMaths100);
            student16.setMajorDegree(degreeMaths100);
            student16.setMinorDegree(degreePhysics100);
            student17.setMajorDegree(degreeHistory200);
            student17.setMinorDegree(degreeLanguages200);
            student18.setMajorDegree(degreeMaths300);
            student18.setMinorDegree(degreeHistory300);
            student19.setMajorDegree(degreeMaths100);
            student19.setMinorDegree(degreePhysics100);
            student20.setMajorDegree(degreeLanguages300);
            student20.setMinorDegree(degreeHistory300);
            student21.setMajorDegree(degreeMaths100);
            student21.setMinorDegree(degreeLanguages100);
            student22.setMajorDegree(degreeMaths100);
            student22.setMinorDegree(degreeLanguages100);
            student23.setMajorDegree(degreeMaths100);
            student23.setMinorDegree(degreeLanguages100);
            student24.setMajorDegree(degreeLanguages200);
            student24.setMinorDegree(degreeMaths200);
            student25.setMajorDegree(degreeEconomics300);
            student25.setMinorDegree(degreeHistory300);
            student26.setMajorDegree(degreeMaths100);
            student26.setMinorDegree(degreePhysics100);
            student27.setMajorDegree(degreeEconomics300);
            student27.setMinorDegree(degreeMaths300);
            student28.setMajorDegree(degreeHistory100);
            student28.setMinorDegree(degreeLanguages100);
            student29.setMajorDegree(degreeMaths100);
            student29.setMinorDegree(degreeLanguages100);
            student30.setMajorDegree(degreeMaths100);
            student30.setMinorDegree(degreeHistory100);
            student31.setMajorDegree(degreeHistory200);
            student31.setMinorDegree(degreeMaths200);
            student32.setMajorDegree(degreeEconomics300);
            student32.setMinorDegree(degreeHistory300);
            student33.setMajorDegree(degreeMaths100);
            student33.setMinorDegree(degreePhysics100);
            student34.setMajorDegree(degreeLanguages300);
            student34.setMinorDegree(degreeMaths300);
            student35.setMajorDegree(degreeHistory100);
            student35.setMinorDegree(degreeLanguages100);
            student36.setMajorDegree(degreeMaths100);
            student36.setMinorDegree(degreeHistory100);
            student37.setMajorDegree(degreeLanguages100);
            student37.setMinorDegree(degreeHistory100);
            student38.setMajorDegree(degreeLinguistics200);
            student38.setMinorDegree(degreeMaths200);
            student39.setMajorDegree(degreeEconomics300);
            student39.setMinorDegree(degreeMaths300);
            student40.setMajorDegree(degreeHistory100);
            student40.setMinorDegree(degreePhysics100);
            student41.setMajorDegree(degreeEconomics300);
            student41.setMinorDegree(degreeMaths300);
            student42.setMajorDegree(degreeMaths100);
            student42.setMinorDegree(degreeLanguages100);
            student43.setMajorDegree(degreeMaths100);
            student43.setMinorDegree(degreeLanguages100);
            student44.setMajorDegree(degreeMaths100);
            student44.setMinorDegree(degreeHistory100);
            student45.setMajorDegree(degreeHistory200);
            student45.setMinorDegree(degreeMaths200);
            student46.setMajorDegree(degreeEconomics300);
            student46.setMinorDegree(degreeLanguages200);
            student47.setMajorDegree(degreeMaths100);
            student47.setMinorDegree(degreePhysics100);
            student48.setMajorDegree(degreeLanguages300);
            student48.setMinorDegree(degreeMaths300);
            student49.setMajorDegree(degreeMaths100);
            student49.setMinorDegree(degreeLanguages100);

            studentRepository.saveAll(List.of(
                    student1, student2, student3, student4, student5,
                    student6, student7, student8, student9, student10,
                    student11, student12, student13, student14, student15,
                    student16, student17, student18, student19, student20,
                    student21, student22, student23, student24, student25,
                    student26, student27, student28, student29, student30,
                    student31, student32, student33, student34, student35,
                    student36, student37, student38, student39, student40,
                    student41, student42, student43, student44, student45,
                    student46, student47, student48, student49
                    ));

        };
    }

}
