package club.myfpl.services.impl;

import club.myfpl.repositories.PlayerRepository;
import club.myfpl.services.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Saket
 * @since 23/04/17.
 */
@Service
public class PlayerServiceImpl implements PlayerService {


    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PlayerRepository getPlayerRepository() {
        return playerRepository;
    }

}
