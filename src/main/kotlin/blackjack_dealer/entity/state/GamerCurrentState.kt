package blackjack_dealer.entity.state

sealed class GamerCurrentState {
    object INITIAL : GamerCurrentState()
    object HIT : GamerCurrentState()
    object STAND : GamerCurrentState()
    object BUST : GamerCurrentState()
    object BLACKJACK : GamerCurrentState()
}
