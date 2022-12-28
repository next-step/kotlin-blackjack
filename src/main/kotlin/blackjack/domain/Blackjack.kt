package blackjack.domain

import blackjack.domain.card.vendor.CardVendor
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class Blackjack(
    val dealer: Dealer,
    val players: Players,
) {
    fun hitOrStay(player: Player, isHit: Boolean): Unit =
        when (isHit) {
            true -> giveCardTo(player)
            false -> acceptStayFrom(player)
        }

    fun notFinishedPlayers(): List<Player> = players.notFinishedPlayers()

    fun complete() {
        dealer.takeFinalCards()

        players.map {
            dealer.result(it)
        }
    }

    private fun giveCardTo(player: Player) {
        check(player.isNotFinished()) {
            "Blackjack should not give card to this player which is finished. [$player]"
        }

        player.hit(dealer.drawCard())
    }

    private fun acceptStayFrom(player: Player) {
        check(player.isNotFinished()) {
            "Player should be able to stay. [$player]"
        }

        player.stay()
    }

    companion object {
        const val BLACKJACK_BEST_SCORE = 21

        fun of(dealerName: String, playerNames: List<String>, cardVendor: CardVendor): Blackjack {
            val dealer = Dealer(
                name = dealerName,
                cards = cardVendor.drawPlayerFirstTwoCards(),
                cardVendor = cardVendor
            )

            val players = Players(
                playerNames.map { playerName ->
                    Player(
                        name = playerName,
                        cards = cardVendor.drawPlayerFirstTwoCards()
                    )
                }
            )

            return Blackjack(dealer, players)
        }
    }
}
