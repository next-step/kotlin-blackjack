package blackjack.domain.player.state.role

abstract class PlayerRole : Role {
    override fun isDealer(): Boolean {
        return false
    }
}
