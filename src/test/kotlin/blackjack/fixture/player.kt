package blackjack.fixture

import blackjack.domain.OpenCards
import blackjack.domain.Player
import blackjack.domain.PlayerName

class BlackJackFixture {
    companion object ofPlayer {
        val BURST_PLAYER: Player
        val NOT_BURST_PLAYER_17: Player
        init {
            BURST_PLAYER = Player.of(PlayerName.from("burst"), OpenCards(KING_HEART, KING_DIAMOND))
            BURST_PLAYER.hit(SEVEN_HEART)

            NOT_BURST_PLAYER_17 = Player.of(PlayerName.from("not_burst"), OpenCards(KING_HEART, SEVEN_HEART))
        }
    }
}


