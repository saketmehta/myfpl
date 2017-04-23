package club.myfpl.model;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 12:10 AM
 */
public enum ElementType {

    GKP(1L, "Goalkeeper", "GKP", "Goalkeepers", "GKP"),
    DEF(2L, "Defender", "DEF", "Defenders", "DEF"),
    MID(3L, "Midfielder", "MID", "Midfielders", "MID"),
    FWD(4L, "Forward", "FWD", "Forwards", "FWD");

    private Long   id;
    private String singularName;
    private String singularShortName;
    private String pluralName;
    private String pluralShortName;

    ElementType(Long id, String singularName, String singularShortName, String pluralName, String pluralShortName) {
        this.id = id;
        this.singularName = singularName;
        this.singularShortName = singularShortName;
        this.pluralName = pluralName;
        this.pluralShortName = pluralShortName;
    }

    public static ElementType getElementTypeFromId(Long id) {
        if (id.equals(1L)) {
            return ElementType.GKP;
        } else if (id.equals(2L)) {
            return ElementType.DEF;
        } else if (id.equals(3L)) {
            return ElementType.MID;
        } else if (id.equals(4L)) {
            return ElementType.FWD;
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSingularName() {
        return singularName;
    }

    public void setSingularName(String singularName) {
        this.singularName = singularName;
    }

    public String getSingularShortName() {
        return singularShortName;
    }

    public void setSingularShortName(String singularShortName) {
        this.singularShortName = singularShortName;
    }

    public String getPluralName() {
        return pluralName;
    }

    public void setPluralName(String pluralName) {
        this.pluralName = pluralName;
    }

    public String getPluralShortName() {
        return pluralShortName;
    }

    public void setPluralShortName(String pluralShortName) {
        this.pluralShortName = pluralShortName;
    }

}
