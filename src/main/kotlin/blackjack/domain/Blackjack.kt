package blackjack.domain

import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.toPlayers

class Blackjack private constructor(
    val dealer: Dealer,
    val players: Players,
) {
    fun hitOrStay(player: Player, isHit: Boolean): Unit =
        when (isHit) {
            true -> giveCardTo(player)
            false -> acceptStayFrom(player)
        }

    fun getNotFinishedPlayers(): Players = players.getNotFinishedPlayers()

    fun complete() {
        dealer.takeFinalCards()

        players.forEach {
            dealer.takeResult(it)
        }
    }

    private fun giveCardTo(player: Player) {
        player.hit(dealer.drawCard())
    }

    private fun acceptStayFrom(player: Player) {
        player.stay()
    }

    companion object {
        const val BLACKJACK_BEST_SCORE = 21

        fun of(dealerName: String, playerNames: List<String>, playerBettingAmounts: List<Double>, cardVendor: CardVendor): Blackjack {
            require(playerNames.size == playerBettingAmounts.size) {
                "playerNames and playerBettings should have the same size"
            }

            val dealer = Dealer(
                name = dealerName,
                cards = cardVendor.drawCards(),
                cardVendor = cardVendor
            )

            val players: Players = playerNames.withIndex().map { (playerIndex, playerName) ->
                Player(
                    name = playerName,
                    cards = cardVendor.drawCards(),
                    betting = playerBettingAmounts[playerIndex]
                )
            }.toPlayers()

            return Blackjack(dealer, players)
        }
    }
}
