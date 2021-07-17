package manager;

import domain.Issue;
import repository.IssueRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public class IssueManager {
    private IssueRepository repository;

    public IssueManager(IssueRepository repository) {
        this.repository = repository;
    }

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

    public List<Issue> filterByLabel(Set<String> labels) {
        List<Issue> result = new ArrayList<Issue>();
        for (Issue issue : repository.getAllIssues()) {
            boolean isLabelMissed = false;
            for (String label : labels) {
                if (!issue.getLabels().contains(label)) {
                    isLabelMissed = true;
                }
            }
            if (!isLabelMissed) {
                result.add(issue);
            }

        }
        return result;
    }

    public List<Issue> ShowSortById() {
        return repository.sortById();
    }

    public List<Issue> ShowSortByAuthor() {
        return repository.sortByAuthor();
    }

    public void closeIssue(int id) {
        repository.updateIssue(id, false);
    }

    public void OpenIssue(int id) {
        repository.updateIssue(id, true);
    }

}