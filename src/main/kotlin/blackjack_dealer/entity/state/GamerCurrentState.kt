package blackjack_dealer.entity.state

sealed class GamerCurrentState {
    object INITIAL : GamerCurrentState()
    object HIT : GamerCurrentState()
    object STAND : GamerCurrentState()
    object BUST : GamerCurrentState()
    object BLACKJACK : GamerCurrentState()
    // 마땅한 이름이 생각이 안나네요;;;
    object INITIAL_BLACKJACK : GamerCurrentState()
}
