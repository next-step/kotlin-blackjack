package blackjack.domain

import blackjack.domain.card.CardVendor
import blackjack.domain.player.Player
import blackjack.domain.player.Players

class Blackjack(
    val players: Players,
    val cardVendor: CardVendor
) {

    companion object {
        fun of(playerNames: List<String>): Blackjack {
            val cardVendor = CardVendor()
            val players = Players(
                playerNames.map { Player(it, cardVendor.drawPlayerFirstTwoCards()) }
            )

            return Blackjack(players, cardVendor)
        }
    }
}
