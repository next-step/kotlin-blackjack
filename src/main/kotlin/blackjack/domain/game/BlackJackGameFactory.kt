package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.gamer.Gamers
import blackjack.domain.gamer.PlayerInitProperty
import blackjack.domain.shuffle.Shuffler

class BlackJackGameFactory(
    private val shuffler: Shuffler<Card>,
) {

    fun create(playerInitProperties: List<PlayerInitProperty>): BlackJackGame {
        return BlackJackGame(
            shuffler = shuffler,
            gamers = Gamers.create(playerInitProperties),
        )
    }
}
