package blackjack.domain

import blackjack.domain.card.CardVendor
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class Blackjack(
    val players: Players,
    private val cardVendor: CardVendor
) {

    fun giveCardTo(player: Player) {
        player.hit(cardVendor.drawCard())
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
