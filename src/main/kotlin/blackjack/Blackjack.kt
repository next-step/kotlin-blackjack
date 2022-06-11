package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.view.InputView
import blackjack.view.OutputView

object Blackjack {
    private const val numberOfStartingCards = 2

    fun play() {
        val players: List<Player> = InputView.getPlayerNames().toPlayers()
        val deck = CardDeck()

        giveOutStartingCardsToPlayers(players, deck)
    }

    private fun giveOutStartingCardsToPlayers(players: List<Player>, deck: CardDeck) {
        players.forEach {
            it.drawCardFromDeck(deck, numberOfStartingCards)
        }

        OutputView.markStartOfNewParagraph()
        OutputView.printPlayerNamesAndNumberOfCardsDrawn(players.toPlayerNames(), numberOfStartingCards)
        OutputView.printPlayerSummaries(players.toPlayerSummaries())
    }

    private fun List<String>.toPlayers(): List<Player> = map { Player(it) }

    private fun List<Player>.toPlayerNames(): List<String> = map { it.name }

    private fun List<Player>.toPlayerSummaries(): List<PlayerSummary> = map { PlayerSummary(it) }

    private fun Player.drawCardFromDeck(deck: CardDeck, numberOfCards: Int = 1) {
        repeat(numberOfCards) {
            addCardToHand(deck.drawCard())
        }
    }
}

fun main() {
    Blackjack.play()
}
