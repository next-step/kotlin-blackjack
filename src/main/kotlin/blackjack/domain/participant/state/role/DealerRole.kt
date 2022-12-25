package blackjack.domain.participant.state.role

import blackjack.domain.participant.state.Name

abstract class DealerRole : Role() {
    override val name: Name
        get() = Name("딜러")

    override fun isDealer(): Boolean {
        return true
    }
}
