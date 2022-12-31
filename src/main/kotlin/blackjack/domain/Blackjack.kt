package blackjack.domain

import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players
import blackjack.domain.player.toPlayers

class Blackjack(
    val dealer: Dealer,
    val players: Players,
) {
    fun hitOrStay(player: Player, isHit: Boolean): Unit =
        when (isHit) {
            true -> giveCardTo(player)
            false -> acceptStayFrom(player)
        }

    fun notFinishedPlayers(): Players = players.notFinishedPlayers()

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

        fun of(dealerName: String, playerNames: List<String>, cardVendor: CardVendor): Blackjack {
            val dealer = Dealer(
                name = dealerName,
                cards = cardVendor.drawCards(),
                cardVendor = cardVendor
            )

            val players: Players = playerNames.map { playerName ->
                Player(
                    name = playerName,
                    cards = cardVendor.drawCards()
                )
            }.toPlayers()

            return Blackjack(dealer, players)
        }
    }
}
