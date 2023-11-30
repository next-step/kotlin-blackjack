package blackjack.domain

import blackjack.domain.CardScoreCalculator.isCloseToBlackjack
import blackjack.domain.CardScoreCalculator.isOverScore

enum class GameResult(
    val message: String
) {
    WIN("승"),
    LOSE("패"),
    DRAW("무승부");

    fun opposite(): GameResult {
        return when (this) {
            WIN -> LOSE
            LOSE -> WIN
            DRAW -> DRAW
        }
    }

    companion object {
        fun resultOfPlayer(player: Player, dealer: Dealer): GameResult {
            val isPlayerCloseToBlackjack = isCloseToBlackjack(player.cards, dealer.cards)
            return when {
                isOverScore(dealer.cards, CardScoreCalculator.BLACKJACK) || isPlayerCloseToBlackjack -> WIN
                !isPlayerCloseToBlackjack -> LOSE
                else -> DRAW
            }
        }
    }
}
