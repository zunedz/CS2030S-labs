abstract class Services implements Comparable<Services> {
    int computeFare(Request request);

    @Override
    public int compareTo(Services otherService) {
        return this.computeFare() - otherService.computeFare();
    }
}
