package domain;

import java.util.Comparator;

public class IssueComparator {

    public static class IssueComparatorById implements Comparator<Issue> {

        @Override
        public int compare(Issue o1, Issue o2) {
            return o1.getId()- o2.getId();
        }
    }

    public static class IssueComparatorByAuthor implements Comparator<Issue> {

        @Override
        public int compare(Issue o1, Issue o2) {
            return o1.getAuthor().compareTo(o2.getAuthor());
        }
    }
}
