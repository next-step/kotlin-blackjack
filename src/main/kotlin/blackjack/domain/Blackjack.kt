package blackjack.domain

import blackjack.domain.card.CardVendor
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class Blackjack(
    val players: Players,
    private val cardVendor: CardVendor
) {

    fun hitOrStay(player: Player, isHit: Boolean): Unit =
        when (isHit) {
            true -> giveCardTo(player)
            false -> acceptStayFrom(player)
        }

    fun notFinishedPlayers(): List<Player> = players.notFinishedPlayers()

    private fun giveCardTo(player: Player) {
        check(player.isNotFinished()) {
            "Blackjack should not give card to this player which is finished. [$player]"
        }

        player.hit(cardVendor.drawCard())
    }

    private fun acceptStayFrom(player: Player) {
        check(player.isNotFinished()) {
            "Player should be able to stay. [$player]"
        }

        player.stay()
    }

    companion object {
        const val BLACKJACK_BEST_SCORE = 21

        fun of(playerNames: List<String>): Blackjack {
            val cardVendor = CardVendor()
            val players = Players(
                playerNames.map { Player(it, cardVendor.drawPlayerFirstTwoCards()) }
            )

            return Blackjack(players, cardVendor)
        }
    }
}
