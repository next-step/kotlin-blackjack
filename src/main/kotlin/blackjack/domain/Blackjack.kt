package blackjack.domain

import blackjack.Fetcher
import blackjack.Printer
import blackjack.common.NonEmptyList
import blackjack.common.PlayerDecision
import blackjack.common.PlayerSummary
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

object Blackjack {
    const val numberOfStartingCards = 2

    fun play(
        playerNameFetcher: Fetcher<Unit, NonEmptyList<String>>,
        playerDecisionFetcher: Fetcher<String, PlayerDecision>,
        startingSummariesPrinter: Printer<List<PlayerSummary>>,
        playerSummaryPrinter: Printer<PlayerSummary>,
        dealerSummaryPrinter: Printer<PlayerSummary>,
        finalSummariesPrinter: Printer<List<PlayerSummary>>
    ) {
        val allPlayers = Players(
            Dealer(),
            playerNameFetcher.fetch(Unit).list.toPlayers()
        )

        val deck = CardDeck()

        allPlayers.drawStartingCards(deck)

        startingSummariesPrinter.print(allPlayers.toPlayerSummaries(true))

        allPlayers.run {
            players.map { player ->
                PlayerTurn(player).play(
                    deck,
                    { playerDecisionFetcher.fetch(player.name) }
                ) { playerSummaryPrinter.print(PlayerSummary(player)) }
            }

            DealerTurn(dealer).play(deck) { dealerSummaryPrinter.print(PlayerSummary(dealer)) }
        }

        finalSummariesPrinter.print(allPlayers.toPlayerSummaries(false))
    }

    private fun Players.drawStartingCards(deck: CardDeck) {
        dealer.drawCardFromDeck(deck, numberOfStartingCards)

        players.forEach {
            it.drawCardFromDeck(deck, numberOfStartingCards)
        }
    }

    private fun Players.toPlayerSummaries(excludeHiddenCard: Boolean): List<PlayerSummary> =
        listOf(PlayerSummary(dealer, excludeHiddenCard)) + players.map { PlayerSummary(it) }

    private fun List<String>.toPlayers(): List<Player> = map { Player(it) }

    private fun Player.drawCardFromDeck(deck: CardDeck, numberOfCards: Int = 1) {
        repeat(numberOfCards) {
            addCardToHand(deck.drawCard())
        }
    }
}
