package blackjack

import blackjack.common.PlayerSummary
import blackjack.common.ScoreSummary
import blackjack.domain.DealerTurn
import blackjack.domain.PlayerTurn
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.score.Scores
import blackjack.view.InputView
import blackjack.view.InputViewImpl
import blackjack.view.OutputView
import blackjack.view.OutputViewImpl

object Blackjack {
    const val numberOfStartingCards = 2

    fun play(inputView: InputView, outputView: OutputView) {
        val deck = CardDeck()

        val dealer = Dealer(drawStartingCardsFromDeck(deck))
        val players = Players(
            inputView.getPlayerNames().map { Player(it, drawStartingCardsFromDeck(deck)) }
        )

        outputView.printStartingSummaries(players.toPlayerSummaries(PlayerSummary(dealer, true)))

        val scores = Scores.of(
            players = players.list.map { player ->
                PlayerTurn(player).play(
                    deck,
                    { inputView.getPlayerDecision(player.name) }
                ) { outputView.printPlayerSummary(PlayerSummary(player)) }
            },
            dealer = DealerTurn(dealer).play(deck) { outputView.printDealerSummary(PlayerSummary(dealer)) }
        )

        outputView.printFinalSummaries(players.toPlayerSummaries(PlayerSummary(dealer, false)))
        outputView.printScoreSummary(ScoreSummary(scores))
    }

    private fun drawStartingCardsFromDeck(deck: CardDeck): List<Card> = listOf(deck.drawCard(), deck.drawCard())

    private fun Players.toPlayerSummaries(dealerSummary: PlayerSummary): List<PlayerSummary> =
        listOf(dealerSummary) + list.map { PlayerSummary(it) }
}

fun main() {
    Blackjack.play(InputViewImpl, OutputViewImpl)
}
