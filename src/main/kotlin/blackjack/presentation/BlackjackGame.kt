package blackjack.presentation

import blackjack.domain.card.CardsDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object BlackjackGame {

    private const val FIRST_CARD_DIVIDE_COUNT = 2

    fun start(
        players: List<Player>,
        cardsDeck: CardsDeck,
    ): Players {
        val dealer = Dealer()
        (listOf(dealer.dealer) + players.map { it.player }).forEach { player ->
            repeat(FIRST_CARD_DIVIDE_COUNT) {
                player.addCard(cardsDeck.divide())
            }
        }

        return Players(
            dealer = dealer,
            guest = players
        )
    }

    fun addCard(
        player: Participant,
        cardsDeck: CardsDeck
    ): Participant {
        val card = cardsDeck.divide()

        player.addCard(card)

        return player
    }

    fun match(players: Players) {
        players.match()
    }
}
