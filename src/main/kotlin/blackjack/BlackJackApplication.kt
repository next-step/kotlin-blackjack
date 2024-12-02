package blackjack

import blackjack.domain.CardDeck
import blackjack.domain.CardsDeckGenerator
import blackjack.domain.Player
import blackjack.view.InputView
import blackjack.view.ResultView.printDistributedCardsText
import blackjack.view.ResultView.printPlayerCards
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

        hitByPlayers(players, cardDeck)
        printPlayersCards(players, isShownScore = true)
    }

    private fun hitByPlayers(
        players: List<Player>,
        cardDeck: CardDeck,
    ) {
        println()
        players.forEach { player ->
            hit(player, cardDeck)
        }
    }

    private fun hit(
        player: Player,
        cardDeck: CardDeck,
    ) {
        while (player.findEnabledMoreCard()) {
            val answerMoreCard = InputView.inputMoreCard(player.name)
            if (!answerMoreCard) break
            player.addCard(cardDeck.pickCard())
            printPlayerCards(player)
        }
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
