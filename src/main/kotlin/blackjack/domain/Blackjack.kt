package blackjack.domain

import blackjack.Fetcher
import blackjack.Printer
import blackjack.common.NonEmptyList
import blackjack.common.PlayerDecision
import blackjack.common.PlayerSummary
import blackjack.domain.card.CardDeck
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerState

object Blackjack {
    const val numberOfStartingCards = 2

    fun play(
        playerNameFetcher: Fetcher<Unit, NonEmptyList<String>>,
        playerDecisionFetcher: Fetcher<String, PlayerDecision>,
        startingPlayerSummaryPrinter: Printer<List<PlayerSummary>>,
        playerSummaryPrinter: Printer<PlayerSummary>,
        finalPlayerSummaryPrinter: Printer<List<PlayerSummary>>
    ) {
        val players: List<Player> = playerNameFetcher.fetch(Unit).list.toPlayers()
        val deck = CardDeck()

        giveOutStartingCardsToPlayers(players, deck)

        startingPlayerSummaryPrinter.print(players.toPlayerSummaries())

        players.forEach { player ->
            var playerState = PlayerState.of(player)

            while (playerState is PlayerState.Playing) {
                playerState = when (playerDecisionFetcher.fetch(player.name)) {
                    PlayerDecision.HIT -> playerState.hit(deck.drawCard())
                    PlayerDecision.STAND -> playerState.stand()
                }

                playerSummaryPrinter.print(PlayerSummary(player))
            }
        }

        finalPlayerSummaryPrinter.print(players.toPlayerSummaries())
    }

    private fun giveOutStartingCardsToPlayers(players: List<Player>, deck: CardDeck) {
        players.forEach {
            it.drawCardFromDeck(deck, numberOfStartingCards)
        }
    }

    private fun List<String>.toPlayers(): List<Player> = map { Player(it) }

    private fun List<Player>.toPlayerSummaries(): List<PlayerSummary> = map { PlayerSummary(it) }

    private fun Player.drawCardFromDeck(deck: CardDeck, numberOfCards: Int = 1) {
        repeat(numberOfCards) {
            addCardToHand(deck.drawCard())
        }
    }
}
