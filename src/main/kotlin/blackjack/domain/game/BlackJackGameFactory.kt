package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.gamer.PlayerNames
import blackjack.domain.shuffle.Shuffler

class BlackJackGameFactory(
    private val shuffler: Shuffler<Card>,
) {

    fun create(playerNames: PlayerNames): BlackJackGame {
        return BlackJackGame(
            shuffler = shuffler,
            playerNames = playerNames,
        )
    }
}
