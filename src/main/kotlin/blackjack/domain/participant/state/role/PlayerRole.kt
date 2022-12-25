package blackjack.domain.participant.state.role

abstract class PlayerRole : Role() {
    override fun isDealer(): Boolean {
        return false
    }
}
