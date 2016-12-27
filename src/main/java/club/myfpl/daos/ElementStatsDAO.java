package club.myfpl.daos;

import club.myfpl.beans.ElementStats;

import java.util.List;

/**
 * User: Saket
 * Date: 15/12/16
 * Time: 6:21 PM
 */
public interface ElementStatsDAO {
    void updateElementStats(List<ElementStats> elementStatsList);
}
