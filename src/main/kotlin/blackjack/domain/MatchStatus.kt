package blackjack.domain

sealed interface MatchStatus {
    data class Dealer(val win: Int, val lose: Int) : MatchStatus
    object Win : MatchStatus
    object Lose : MatchStatus
}
