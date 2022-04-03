# University ERP

Java/JavaScript web app that lets you organize and manage a university and all its students, professors, courses, and degrees, and how they interact with one another.

---

Bootstrap table with external plugins and custom "table-building" features, being able to navigate between the different steps of the history created along the way:
![](https://i.ibb.co/chQ3NvX/1.png)
![](https://i.ibb.co/0hJVbzZ/2.png)
![](https://i.ibb.co/fVg1d7c/3.png)

Detail pages for individual entries, with a simplified table row and a "detail" section filled with all the available information about the given entry:
![](https://i.ibb.co/zS84X6r/4.png)

Dashboard with real-time statistics (updated through custom SQL queries) about the four entities: students, professors, courses, and degrees:
![](https://i.ibb.co/P9RvBfD/7.png)
![](https://i.ibb.co/ZmRZ5xX/8.png)

Custom right-click context menu to switch easily between different entities (without having to look it up through its id):
![](https://i.ibb.co/TbPvX93/6.png)

Modals when confirmation or additional information are required, with fields being loaded from information fetched from the server when needed:
![](https://i.ibb.co/brkPppB/5.png)

Common navbar and buttons shared across all pages, updating depending on what page the user is currently visiting, responsive for smaller devices
![](https://i.ibb.co/TPqZqYm/9.png)
![](https://i.ibb.co/4SnSR7M/11.png)

First welcome pages:
![](https://i.ibb.co/TRpjcCx/image.png)
![](https://i.ibb.co/tszTzvG/0-5.png)

---

First steps of the project:
- Making of a use case diagram, an activity diagram, a class diagram beforehand, as well as a visual mock-up of what the app would look like, in order to be able to present the project to a hypothetical client and discuss it with other members of the team:
  - [https://drive.google.com/file/d/1s87q9fLD7d9buZooE2Q2-oIvl348ryh0/view?usp=sharing](https://drive.google.com/file/d/1s87q9fLD7d9buZooE2Q2-oIvl348ryh0/view?usp=sharing) - 
  - [https://drive.google.com/file/d/1v2t6FLghkuFnSTi0ER4b6JMierMmR7OB/view?usp=sharing](https://drive.google.com/file/d/1v2t6FLghkuFnSTi0ER4b6JMierMmR7OB/view?usp=sharing) - 
  - [https://drive.google.com/file/d/1xnBsAxRic0tH6Oov_dqqZ7V_GJUTUEZ5/view?usp=sharing](https://drive.google.com/file/d/1xnBsAxRic0tH6Oov_dqqZ7V_GJUTUEZ5/view?usp=sharing) - 
  - [https://i.ibb.co/6tfcWqS/Welcome-Screen.png](https://i.ibb.co/6tfcWqS/Welcome-Screen.png) - 
  - [https://i.ibb.co/Pc0T9dJ/Choosing-Screen.png](https://i.ibb.co/Pc0T9dJ/Choosing-Screen.png) -  
  - [https://i.ibb.co/WPsYqbH/Professors-list.png](https://i.ibb.co/WPsYqbH/Professors-list.png) - 
  - [https://i.ibb.co/m45VLCP/Professor-item.png](https://i.ibb.co/m45VLCP/Professor-item.png)

- Made the whole back-end part of the app using nothing but Java, Hibernate, JPA, JDBC -- see also what the final class diagram looks like in comparison with the first draft:
  - [https://i.ibb.co/RNJY6jz/University-Class-Diagram.png](https://i.ibb.co/RNJY6jz/University-Class-Diagram.png)

- Made unit tests covering all of the code:
  - [https://i.ibb.co/CQsNNPy/University-Code-Coverage.png](https://i.ibb.co/CQsNNPy/University-Code-Coverage.png)

- Configured and generated the JavaDoc:
  - [https://drive.google.com/drive/folders/1XnfOLYgxNDvuJ9I9mVGz4tz_qQAgNxuG?usp=sharing](https://drive.google.com/drive/folders/1XnfOLYgxNDvuJ9I9mVGz4tz_qQAgNxuG?usp=sharing)

- Remade the whole back taking inspiration from the first attempt this time using Spring Boot, and made the front with new technologies (see the results at the top).
- Additional books used:
  - CLEAN CODE by Robert Cecil Martin
  - THE CLEAN CODER by Robert Cecil Martin