package blackjack.domain.game

import blackjack.domain.Score

/**
 * ### 게임의 승패 결과를 정의한 enum 입니다.
 */
enum class GameResultType {
    CHALLENGER_WIN,
    DEALER_WIN,
    DRAW,
    ;

    companion object {
        fun of(challengerScore: Score, dealerScore: Score): GameResultType {
            return when {
                challengerScore.isBurst -> DEALER_WIN
                dealerScore.isBurst -> CHALLENGER_WIN
                challengerScore > dealerScore -> CHALLENGER_WIN
                challengerScore < dealerScore -> DEALER_WIN
                else -> DRAW
            }
        }
    }
}
