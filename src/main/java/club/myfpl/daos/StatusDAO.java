package club.myfpl.daos;

import club.myfpl.model.Status;

/**
 * User: Saket
 * Date: 25/11/16
 * Time: 3:36 AM
 */
public interface StatusDAO {
    long fetchCurrentEvent();

    void updateStatus(Status status);
}
