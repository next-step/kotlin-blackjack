package blackjack.domain

import blackjack.Fetcher
import blackjack.Printer
import blackjack.common.PlayerDecision
import blackjack.common.PlayerSummary
import blackjack.common.ScoreSummary
import blackjack.domain.card.Card
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.score.Scores

object Blackjack {
    const val numberOfStartingCards = 2

    fun play(
        playerNameFetcher: Fetcher<Unit, List<String>>,
        playerDecisionFetcher: Fetcher<String, PlayerDecision>,
        startingSummariesPrinter: Printer<List<PlayerSummary>>,
        playerSummaryPrinter: Printer<PlayerSummary>,
        dealerSummaryPrinter: Printer<PlayerSummary>,
        finalSummariesPrinter: Printer<List<PlayerSummary>>,
        finalScoresPrinter: Printer<ScoreSummary>
    ) {
        val deck = CardDeck()

        val dealer = Dealer(drawStartingCardsFromDeck(deck))
        val players = Players(
            playerNameFetcher.fetch(Unit).map { Player(it, drawStartingCardsFromDeck(deck)) }
        )

        startingSummariesPrinter.print(players.toPlayerSummaries(PlayerSummary(dealer, true)))

        val scores = Scores.of(
            players = players.list.map { player ->
                PlayerTurn(player).play(
                    deck,
                    { playerDecisionFetcher.fetch(player.name) }
                ) { playerSummaryPrinter.print(PlayerSummary(player)) }
            },
            dealer = DealerTurn(dealer).play(deck) { dealerSummaryPrinter.print(PlayerSummary(dealer)) }
        )

        finalSummariesPrinter.print(players.toPlayerSummaries(PlayerSummary(dealer, false)))
        finalScoresPrinter.print(ScoreSummary(scores))
    }

    private fun drawStartingCardsFromDeck(deck: CardDeck): List<Card> = listOf(deck.drawCard(), deck.drawCard())

    private fun Players.toPlayerSummaries(dealerSummary: PlayerSummary): List<PlayerSummary> =
        listOf(dealerSummary) + list.map { PlayerSummary(it) }
}
