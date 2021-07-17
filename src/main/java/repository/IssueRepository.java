package repository;

import domain.Issue;
import domain.IssueComparator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class IssueRepository {
    private List<Issue> issues = new ArrayList<>();

    public void save(Issue issue) {

        issues.add(issue);

    }

    public List<Issue> getAllIssues() {
        return issues;
    }


    public List<Issue> getIssues(boolean isOpened) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (issue.getIsOpened() == isOpened) {
                result.add(issue);
            }

        }
        return result;
    }

    public List<Issue> filterBy(Predicate<Issue> predicate) {
        List<Issue> result = new ArrayList<>();
        for (Issue issue : issues) {
            if (predicate.test(issue)) {
                result.add(issue);
            }

        }
        return result;
    }

    public List<Issue> sortById() {
        List<Issue> result = new ArrayList<Issue>(issues);
        result.sort(new IssueComparator.IssueComparatorById());
        return result;
    }

    public List<Issue> sortByAuthor() {
        List<Issue> result = new ArrayList<Issue>(issues);
        result.sort(new IssueComparator.IssueComparatorByAuthor());
        return result;
    }

    public void updateIssue(int id, boolean isOpened) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                issue.setIsOpened(isOpened);
            }
        }

    }
}


