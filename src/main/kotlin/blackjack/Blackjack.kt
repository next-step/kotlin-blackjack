package blackjack

import blackjack.common.PlayerSummary
import blackjack.common.ProfitSummary
import blackjack.domain.DealerTurn
import blackjack.domain.bet.Profits
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
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
            inputView.getPlayerProperties().map { Player(it.name, it.bet, drawStartingCardsFromDeck(deck)) }
        )

        outputView.printStartingSummaries(listOf(PlayerSummary(dealer, true)) + players.toPlayerSummaries())

        val profits = Profits.of(
            players = players.play(
                deck,
                { name -> inputView.getPlayerDecision(name) }
            ) { summary -> outputView.printPlayerSummary(summary) },
            dealer = DealerTurn(dealer).play(deck) { outputView.printDealerSummary(PlayerSummary(dealer)) }
        )

        outputView.printFinalSummaries(listOf(PlayerSummary(dealer, false)) + players.toPlayerSummaries())
        outputView.printProfitSummary(ProfitSummary(profits))
    }

    private fun drawStartingCardsFromDeck(deck: CardDeck): List<Card> = listOf(deck.drawCard(), deck.drawCard())
}

fun main() {
    Blackjack.play(InputViewImpl, OutputViewImpl)
}
