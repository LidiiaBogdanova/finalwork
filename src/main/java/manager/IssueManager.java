package manager;
import domain.Issue;
import repository.IssueRepository;

import java.util.List;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;


    public void add(Issue issue) {
        repository.save(issue);
    }


    public List<Issue> getOpenedIssues() {
        return repository.getIssues(true);
    }

    public List<Issue> getClosedIssues() {
        return repository.getIssues(false);

    }

    public List<Issue> filterByAuthor(String author) {
        return repository.filterBy(issue -> issue.getAuthor().contains(author));

    }

    public List<Issue> filterByAssignee(String assignee) {
        return repository.filterBy(issue -> issue.getAssignee().contains(assignee));
    }

    public List<Issue> filterByLabel(String label) {
        return repository.filterBy(issue -> issue.getLabels().contains(label));
    }
    public void closeIssue(int id){
        repository.updateIssue(id, false);
    }

}