package classes;

import java.io.Serializable;

/**
 * Enumeration with organization type constants.
 */
public enum OrganizationType implements Serializable {
    COMMERCIAL,
    GOVERNMENT,
    TRUST;

    /**
     * Generates a beautiful list of enum string values.
     *
     * @return String with all enum values splitted by comma.
     */
    public static String nameList() {
        String nameList = "";
        for (OrganizationType organizationType : values()) {
            nameList += organizationType.name() + ", ";
        }
        return nameList.substring(0, nameList.length() - 2);
    }
}