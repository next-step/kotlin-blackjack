package blackjack.model

import blackjack.domain.Dealer
import blackjack.domain.Player

enum class GameResult {
    WIN,
    PUSH,
    LOSE,
    BLACKJACK,
    ;

    companion object {
        fun of(dealer: Dealer, player: Player): GameResult {
            val playerSum = player.play.score()
            val dealerSum = dealer.play.score()
            return when {
                dealer.play.blackjack && player.play.blackjack -> PUSH
                player.play.blackjack -> BLACKJACK
                (dealer.play.bust && !player.play.bust) ||
                    (dealer.play.stay && player.play.stay && playerSum > dealerSum) -> WIN

                dealer.play.stay && player.play.stay && playerSum == dealerSum -> PUSH
                else -> LOSE
            }
        }
    }
}

sealed class PlayerProfit {
    abstract val name: String
    abstract val profit: Double

    data class Dealer(
        override val name: String,
        override val profit: Double = 0.0,
    ) : PlayerProfit()

    data class Player(
        override val name: String,
        override val profit: Double = 0.0,
    ) : PlayerProfit()
}

data class PlayerProfits(val value: List<PlayerProfit>)
