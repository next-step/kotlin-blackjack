package blackjack.domain.game

import blackjack.domain.player.Player

data class Betting(val player: Player, val credit: Credit) {

    fun loseBetting(): Betting {
        return Betting(player, this.credit.subtract(credit))
    }

    fun lostDealerBetting(credit: Credit): Betting {
        return Betting(player, this.credit.subtractDealerCredit(credit))
    }

    fun winBetting(): Betting {
        return Betting(player, this.credit.receiveCredit())
    }

    fun winDealerBetting(credit: Credit): Betting {
        return Betting(player, this.credit.receiveCredit(credit))
    }

    fun winBlackJackBetting(): Betting {
        return Betting(player, this.credit.receiveBlackJackCredit())
    }

    fun loseBlackJackBetting(credit: Credit): Betting {
        return Betting(player, this.credit.subtractBlackJackDealer(credit))
    }
}
