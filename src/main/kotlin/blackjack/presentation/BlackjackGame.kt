package blackjack.presentation

import blackjack.domain.card.Card
import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Player

object BlackjackGame {

    private const val FIRST_CARD_DIVIDE_COUNT = 2

    fun start(
        players: List<Player>,
        cardsDeck: CardsDeck,
    ): List<Player> {
        return players.map { player ->
            val cards = mutableListOf<Card>()

            repeat(FIRST_CARD_DIVIDE_COUNT) {
                cards.add(cardsDeck.divide())
            }

            player.copy(cards = cards)
        }
    }

    fun addCard(
        player: Player,
        cardsDeck: CardsDeck
    ): Player {
        val card = cardsDeck.divide()
        return player.copy(
            cards = player.cards + card
        )
    }
}
