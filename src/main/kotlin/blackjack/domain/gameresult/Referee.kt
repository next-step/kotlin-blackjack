package blackjack.domain.gameresult

import blackjack.domain.Dealer
import blackjack.domain.player.Player

class Referee(val dealer: Dealer) {
    fun referee(player: Player): Result = when {
        player.blackjack() -> blackjack()
        player.bust() -> Result.LOSE
        dealer.bust() -> Result.WIN
        player.total() > dealer.total() -> Result.WIN
        player.total() == dealer.total() -> Result.DRAW
        player.total() < dealer.total() -> Result.LOSE
        else -> throw RuntimeException()
    }
    fun blackjack(): Result = if (dealer.blackjack()) Result.BLACKJACK_DRAW else Result.BLACKJACK
}
