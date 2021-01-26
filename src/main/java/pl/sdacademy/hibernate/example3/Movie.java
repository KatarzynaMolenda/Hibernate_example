package pl.sdacademy.hibernate.example3;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String title;
    private int length;
    @ManyToOne(fetch = FetchType.LAZY)
    private Director director;
    @ManyToMany(mappedBy = "movies")
    private Set<Actor> actors = new HashSet<>();

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
