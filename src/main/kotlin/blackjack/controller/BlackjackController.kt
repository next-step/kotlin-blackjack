package blackjack.controller

import blackjack.domain.Dealer
import blackjack.domain.Players
import blackjack.view.ConsoleInput

const val INITIAL_CARD_COUNT = 2

class BlackjackController {
    fun playGame() {
        val players = ConsoleInput().inputPlayers()
        val dealer = Dealer()
    }

    private fun initialCard(players: Players, dealer: Dealer) {
        dealer.shuffle()

        players.list.forEach { player ->
            repeat(INITIAL_CARD_COUNT) {
                player.cards.add(dealer.divede())
            }
        }
    }
}

fun main() {
    BlackjackController().playGame()
}
