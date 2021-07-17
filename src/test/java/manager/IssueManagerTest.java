package manager;

import domain.Issue;
import org.junit.jupiter.api.Test;
import repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class IssueManagerTest {
    IssueManager manager = new IssueManager(new IssueRepository());

    private Set<String> labelsSet1, labelsSet2, labelsSet3, labelsSet4, labelsSet5;
    private Issue issue1, issue2, issue3, issue4, issue5;

    private void initializeTestData() {
        labelsSet1 = new HashSet<>();
        labelsSet1.add("high-priority");
        labelsSet1.add("bug");

        labelsSet2 = new HashSet<>();
        labelsSet2.add("documentation");
        labelsSet2.add("high-priority");

        labelsSet3 = new HashSet<>();
        labelsSet3.add("documentation");
        labelsSet3.add("high-priority");

        labelsSet4 = new HashSet<>();
        labelsSet4.add("enhancement");
        labelsSet4.add("low-priority");

        labelsSet5 = new HashSet<>();
        labelsSet5.add("bug");
        labelsSet5.add("invalid");

        issue1 = new Issue(1, "problem1", "Ivan", "Viktor", "description", true, labelsSet1);
        issue2 = new Issue(2, "problem2", "Aleksandr", "Viktor", "description", true, labelsSet2);
        issue3 = new Issue(3, "problem3", "Viktor", "Ivan", "description", false, labelsSet3);
        issue4 = new Issue(4, "problem4", "Aleksandr", "Ivan", "description", false, labelsSet4);
        issue5 = new Issue(5, "problem5", "Ivan", "Aleksandr", "description", true, labelsSet5);
    }

    @Test
    public void ShouldAddIssue() {
        initializeTestData();
        manager.add(issue1);
        List<Issue> actual = manager.getOpenedIssues();

        assertEquals(actual.get(0), issue1);
    }

    @Test
    public void IfListNothing() {
        initializeTestData();
        List<Issue> actual = manager.getOpenedIssues();
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);

    }

    @Test
    public void ShouldShowOpenedIssues() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        List<Issue> actual = manager.getOpenedIssues();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue2);
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldntShowOpenedIssues() {
        initializeTestData();
        manager.add(issue3);
        manager.add(issue4);
        List<Issue> actual = manager.getOpenedIssues();
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldShowClosedIssues() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        List<Issue> actual = manager.getClosedIssues();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue3);
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldntShowClosedIssues() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue5);
        List<Issue> actual = manager.getClosedIssues();
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldSortMinToMaxId() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue5);
        manager.add(issue2);
        List<Issue> actual = manager.ShowSortById();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue2);
        expected.add(issue5);
        assertEquals(expected, actual);

    }

    @Test
    public void ShouldSortByAuthor() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue5);
        manager.add(issue2);
        List<Issue> actual = manager.ShowSortByAuthor();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue2);
        expected.add(issue1);
        expected.add(issue5);
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldCloseIssue() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue5);
        manager.add(issue2);
        manager.closeIssue(2);
        List<Issue> actual = manager.getClosedIssues();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue2);

        assertEquals(expected, actual);

    }

    @Test
    public void ShouldOpenIssue() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.OpenIssue(3);
        List<Issue> actual = manager.getOpenedIssues();
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue2);
        expected.add(issue3);
        assertEquals(expected, actual);

    }

    @Test
    public void ShouldFilterByAuthor() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        List<Issue> actual = manager.filterByAuthor("Ivan");
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue5);
        assertEquals(expected, actual);


    }

    @Test
    public void ShouldntFilterByIncorrectAuthor() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        List<Issue> actual = manager.filterByAuthor("Ksenia");
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);


    }

    @Test
    public void ShouldFilterByAssignee() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        List<Issue> actual = manager.filterByAssignee("Ivan");
        List<Issue> expected = new ArrayList<>();
        expected.add(issue3);
        expected.add(issue4);
        assertEquals(expected, actual);


    }

    @Test
    public void ShouldntFilterByIncorrectAssignee() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        List<Issue> actual = manager.filterByAssignee("Klavdia");
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);


    }

    @Test
    public void ShouldFilterByTwoLabels() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        Set<String> labelsSet = new HashSet<>();
        labelsSet.add("high-priority");
        labelsSet.add("bug");
        List<Issue> actual = manager.filterByLabel(labelsSet);
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        assertEquals(expected, actual);


    }

    @Test
    public void ShouldFilterByOneLabel() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        Set<String> labelsSet = new HashSet<>();
        labelsSet.add("high-priority");
        List<Issue> actual = manager.filterByLabel(labelsSet);
        List<Issue> expected = new ArrayList<>();
        expected.add(issue1);
        expected.add(issue2);
        expected.add(issue3);
        assertEquals(expected, actual);
    }

    @Test
    public void ShouldntFilterByIncorrectLabel() {
        initializeTestData();
        manager.add(issue1);
        manager.add(issue2);
        manager.add(issue3);
        manager.add(issue4);
        manager.add(issue5);
        Set<String> labelsSet = new HashSet<>();
        labelsSet.add("medium-priority");
        List<Issue> actual = manager.filterByLabel(labelsSet);
        List<Issue> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }
}