/**
 * Created by androiddevelopment on 6/10/15.
 */
public enum IPs {
    SERVERIP("172.16.253.242"),
    ClientIP("172.18.88.42")
    ;
//
    private final String text;
//
//    /**
//     * @param text
//     */
IPs(final String text) {
        this.text = text;
    }
//
//    /* (non-Javadoc)
//     * @see java.lang.Enum#toString()
//     */
    @Override
    public String toString() {
        return text;
    }
}
