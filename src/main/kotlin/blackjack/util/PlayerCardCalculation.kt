package blackjack.util

import blackjack.entity.Player
import blackjack.entity.enums.Denomination

object PlayerCardCalculation {

    private const val BLACK_JACK = 21

    fun calculation(player: Player): Int {

        var score = player
            .cards
            .sumOf { it.denomination.cardNumber }

        if (score > BLACK_JACK) {
            val aceCount = player
                .cards
                .count { it.denomination == Denomination.ACE }

            score = score - (Denomination.ACE.cardNumber * aceCount) + (1 * aceCount)
        }

        return score
    }
}
