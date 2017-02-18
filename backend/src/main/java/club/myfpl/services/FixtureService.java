package club.myfpl.services;

import club.myfpl.model.Fixture;

import java.util.List;

/**
 * User: Saket
 * Date: 28/12/16
 * Time: 2:58 PM
 */
public interface FixtureService {
    List<Fixture> fetchCurrentFixtures();

    Fixture fetchFixture(long fixtureId);
}
