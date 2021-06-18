package domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Issue implements Comparable<Issue>{
    int id;
    String title;
    String author;
    String assignee;
    String label;
    String description;
    Boolean isOpened;

Set <String> labels;

    @Override
    public int compareTo(Issue o) {
        return this.id-o.id;
    }

}



