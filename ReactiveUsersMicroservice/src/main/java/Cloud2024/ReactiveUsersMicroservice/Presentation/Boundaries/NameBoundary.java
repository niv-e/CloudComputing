package Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries;

import java.io.Serializable;

public class NameBoundary implements Serializable {
    private String First;
    private String Last;

    public NameBoundary() {
    }

    public NameBoundary(String first, String last) {
        First = first;
        Last = last;
    }

    public String getFirst() {
        return First;
    }

    public void setFirst(String first) {
        First = first;
    }

    public String getLast() {
        return Last;
    }

    public void setLast(String last) {
        Last = last;
    }

    @Override
    public String toString() {
        return "Name{" +
                "First='" + First + '\'' +
                ", Last='" + Last + '\'' +
                '}';
    }
}
