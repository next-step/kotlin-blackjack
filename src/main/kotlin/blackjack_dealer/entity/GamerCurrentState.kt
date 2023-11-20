package blackjack_dealer.entity

sealed class GamerCurrentState {
    object INITIAL : GamerCurrentState()
    object HIT : GamerCurrentState()
    object STAND : GamerCurrentState()
    object BUST : GamerCurrentState()
    object BLACKJACK : GamerCurrentState()
}
