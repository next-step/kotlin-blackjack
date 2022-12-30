package blackjack.domain.participant.state.role

import blackjack.domain.participant.state.Name

abstract class DealerRole : Role() {
    override val name: Name
        get() = Name(DEALER_NAME)

    override fun isDealer(): Boolean {
        return true
    }

    companion object {
        const val DEALER_NAME = "딜러"
    }
}
