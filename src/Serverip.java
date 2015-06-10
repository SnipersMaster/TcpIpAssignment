/**
 * Created by androiddevelopment on 6/10/15.
 */
public enum Serverip {
    SERVERIP("172.16.253.242"),
    ClientIP("172.16.253.242")
    ;
//
    private final String text;
//
//    /**
//     * @param text
//     */
Serverip(final String text) {
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
