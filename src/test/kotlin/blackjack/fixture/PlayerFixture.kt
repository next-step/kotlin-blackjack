package blackjack.fixture

import blackjack.domain.player.Name
import blackjack.domain.player.Player
import blackjack.domain.trump.Cards

class PlayerFixture {

    companion object {
        private val PLAYER_NAME = Name.from("ssibongee")

        fun 플레이어_생성(cards: Cards): Player {
            return Player(PLAYER_NAME, cards)
        }
    }
}
