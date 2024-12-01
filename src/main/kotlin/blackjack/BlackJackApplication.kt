package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.CardsDeckGenerator
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView.printDistributedCardsText
import blackjack.view.ResultView.printPlayersCards

fun main() {
    val blackJackApplication = BlackJackApplication()
    blackJackApplication.run()
}

class BlackJackApplication {
    fun run() {
        val players = InputView.inputPlayerNames().map { Player(it) }
        val cardDeck = CardDeck.from(CardsDeckGenerator().generate())

        printDistributedCardsText(players.map { it.name })
        distributeTwoCards(players, cardDeck)
        printPlayersCards(players)
    }

    private fun distributeTwoCards(
        players: List<Player>,
        cardDeck: CardDeck,
    ) {
        repeat(REPEAT_TIMES_TO_DISTRIBUTE) {
            distributeToPlayersCards(players, cardDeck)
        }
    }

    private fun distributeToPlayersCards(
        players: List<Player>,
        cardDeck: CardDeck,
    ) {
        players.forEach { player ->
            player.addCard(cardDeck.pickCard())
        }
    }

    companion object {
        private const val REPEAT_TIMES_TO_DISTRIBUTE = 2
    }
}
