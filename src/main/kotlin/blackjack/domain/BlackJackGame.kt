package blackjack.domain

import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.player.Players

class BlackJackGame private constructor(
    val players: Players,
    val cardDeck: CardDeck
) {
    companion object {
        fun setup(players: Players): BlackJackGame {
            val cardDeck = setupCardDeck {
                spade()
                diamond()
                heart()
                club()
            }
            return BlackJackGame(players, cardDeck)
        }
    }
}
