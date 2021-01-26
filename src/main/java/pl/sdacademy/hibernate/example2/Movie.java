package pl.sdacademy.hibernate.example2;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private int length;
    @ManyToOne
    private Director director;

    public Movie(String title, int length, Director director) {
        this.title = title;
        this.length = length;
        this.director = director;
    }

    public Movie() {
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getLength() {
        return length;
    }

    public Director getDirector() {
        return director;

    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void setDirector(Director director) {
        this.director = director;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", length=" + length +
                ", director=" + director +
                '}';
    }
}
