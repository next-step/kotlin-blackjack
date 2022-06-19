package blackjack.domain

sealed interface MatchStatus {
    data class Dealer(val win: Int, val lose: Int, val push: Int) : MatchStatus
    object Win : MatchStatus
    object Lose : MatchStatus
    object Push : MatchStatus
}
