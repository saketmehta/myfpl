package club.myfpl.services.impl;

import club.myfpl.beans.Fixture;
import club.myfpl.daos.FixtureDAO;
import club.myfpl.services.FixtureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: Saket
 * Date: 28/12/16
 * Time: 2:59 PM
 */
@Service
public class FixtureServiceImpl implements FixtureService {
    private final FixtureDAO fixtureDAO;

    @Autowired
    public FixtureServiceImpl(FixtureDAO fixtureDAO) {
        this.fixtureDAO = fixtureDAO;
    }

    @Override
    public List<Fixture> fetchCurrentFixtures() {
        return fixtureDAO.getCurrentFixtures();
    }
}
