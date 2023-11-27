package blackjack.domain.component

import blackjack.domain.model.CardPool
import blackjack.domain.model.Cards
import blackjack.domain.model.Player
import blackjack.domain.model.PlayerName

class BlackjackGame private constructor(
    private val players: List<Player>,
    private val cardPool: CardPool
) {
    companion object {
        private const val INITIAL_CARD_COUNT = 2

        fun create(playerNames: List<PlayerName>): BlackjackGame {
            val cardPool = CardPool.create()
            val initialCards = cardPool
                .pickAndRemove(INITIAL_CARD_COUNT)
                .run { Cards.of(this) }
            val players = playerNames.map { Player(it, initialCards) }

            return BlackjackGame(players, cardPool)
        }
    }
}
