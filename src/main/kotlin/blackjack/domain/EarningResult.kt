package blackjack.domain

import blackjack.domain.player.Customer

internal data class EarningResult(val customerEarnings: Map<Customer, Int>) {

    val dealerEarning: Int
        get() {
            return this.customerEarnings.values.sum().times(-1)
        }
}
