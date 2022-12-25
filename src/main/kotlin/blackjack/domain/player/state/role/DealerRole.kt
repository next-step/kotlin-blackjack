package blackjack.domain.player.state.role

import blackjack.domain.player.state.Name

abstract class DealerRole : Role() {
    override val name: Name
        get() = Name("딜러")

    override fun isDealer(): Boolean {
        return true
    }
}
