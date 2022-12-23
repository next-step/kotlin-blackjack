package blackjack.domain.player.state.role

abstract class DealerRole : Role {
    override fun isDealer(): Boolean {
        return true
    }
}
