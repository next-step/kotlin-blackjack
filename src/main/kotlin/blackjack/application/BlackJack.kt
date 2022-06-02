package blackjack.application

import blackjack.domain.card.CardDeck
import blackjack.domain.card.setupCardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class BlackJack private constructor(
    private val players: Players,
    private val cardDeck: CardDeck
) {

    fun ready() {
        players.ready(cardDeck)
    }

    fun play(player: Player) {
        player.hit(cardDeck)
    }

    companion object {
        fun setup(players: Players): BlackJack {
            val cardDeck = setupCardDeck {
                spade()
                diamond()
                heart()
                club()
            }
            return BlackJack(players, cardDeck)
        }
    }
}
