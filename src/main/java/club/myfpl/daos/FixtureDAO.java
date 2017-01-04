package club.myfpl.daos;

import club.myfpl.model.Fixture;

import java.util.List;

/**
 * User: Saket
 * Date: 15/12/16
 * Time: 6:15 PM
 */
public interface FixtureDAO {
    void updateFixtures(List<Fixture> fixtures);

    List<Fixture> getCurrentFixtures();

    Fixture fetchFixture(long fixtureId);
}
