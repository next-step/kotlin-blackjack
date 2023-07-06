package blackjack.domain

import blackjack.domain.player.Dealer
import blackjack.domain.player.Player

sealed class Decision {
    abstract fun process(dealer: Dealer, player: Player)
}

object HitDecision: Decision() {
    override fun process(dealer: Dealer, player: Player) = dealer.dealing(player)
}

object StayDecision: Decision() {
    override fun process(dealer: Dealer, player: Player) = player.stay()
}
