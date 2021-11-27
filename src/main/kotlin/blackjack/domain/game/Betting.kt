package blackjack.domain.game

import blackjack.domain.player.Player

data class Betting(val player: Player, val credit: Credit) {

    fun loseBetting(): Betting {
        return Betting(player, this.credit.subtract(credit))
    }
}
