package club.myfpl.beans;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 4:20 PM
 */
public class SequenceNumber {
    private String id;
    private long userSeqNumber;
    private long leagueSeqNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getUserSeqNumber() {
        return userSeqNumber;
    }

    public void setUserSeqNumber(long userSeqNumber) {
        this.userSeqNumber = userSeqNumber;
    }

    public long getLeagueSeqNumber() {
        return leagueSeqNumber;
    }

    public void setLeagueSeqNumber(long leagueSeqNumber) {
        this.leagueSeqNumber = leagueSeqNumber;
    }
}
