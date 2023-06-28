package blackjack.domain.game

import blackjack.domain.gamer.Dealer
import blackjack.domain.gamer.Player

enum class MatchResultType {
    WIN,
    TIE,
    LOSE,
    ;

    fun isWin() = this == WIN

    fun isTie() = this == TIE

    fun isLose() = this == LOSE

    companion object {

        fun calculatePlayerMatchResult(
            dealer: Dealer,
            player: Player
        ): MatchResultType {
            return when {
                player.state.isBust() -> LOSE
                dealer.state.isBust() -> WIN
                dealer.state.cards.score < player.state.cards.score -> WIN
                dealer.state.cards.score == player.state.cards.score -> TIE
                else -> LOSE
            }
        }
    }
}
