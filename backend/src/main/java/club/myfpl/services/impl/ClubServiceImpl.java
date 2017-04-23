package club.myfpl.services.impl;

import club.myfpl.repositories.ClubRepository;
import club.myfpl.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Saket
 * @since 23/04/17.
 */
@Service
public class ClubServiceImpl implements ClubService {

    private final ClubRepository clubRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public ClubRepository getClubRepository() {
        return clubRepository;
    }

}
