package blackjack

import blackjack.common.PlayerDecision
import blackjack.common.PlayerSummary
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerState
import blackjack.view.InputView
import blackjack.view.OutputView

object Blackjack {
    private const val numberOfStartingCards = 2

    fun play() {
        val players: List<Player> = InputView.getPlayerNames().list.toPlayers()
        val deck = CardDeck()

        giveOutStartingCardsToPlayers(players, deck)

        OutputView.markStartOfNewParagraph()

        players.forEach { player ->
            var playerState = PlayerState.of(player)

            while (playerState is PlayerState.Playing) {
                playerState = when (InputView.getPlayerDecision(player.name)) {
                    PlayerDecision.HIT -> playerState.hit(deck.drawCard())
                    PlayerDecision.STAND -> playerState.stand()
                }

                OutputView.printPlayerSummary(PlayerSummary(player))
            }
        }

        OutputView.markStartOfNewParagraph()
        OutputView.printPlayerSummaries(players.toPlayerSummaries(), true)
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
