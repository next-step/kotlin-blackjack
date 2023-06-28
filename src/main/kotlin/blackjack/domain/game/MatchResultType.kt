package blackjack.domain.game

enum class MatchResultType {
    WIN,
    TIE,
    LOSE,
    ;

    fun isWin() = this == WIN

    fun isTie() = this == TIE

    fun isLose() = this == LOSE
}
