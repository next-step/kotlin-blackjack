package blackjack.presentation

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Player

object BlackjackGame {

    private const val FIRST_CARD_DIVIDE_COUNT = 2

    fun start(
        players: List<Player>,
        cardsDeck: CardsDeck,
    ): List<Player> {
        players.forEach { player ->
            repeat(FIRST_CARD_DIVIDE_COUNT) {
                player.addCard(cardsDeck.divide())
            }
        }

        return players
    }

    fun addCard(
        player: Player,
        cardsDeck: CardsDeck
    ): Player {
        val card = cardsDeck.divide()

        player.addCard(card)

        return player
    }
}
