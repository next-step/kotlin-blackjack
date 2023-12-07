package blackjack.participant

import blackjack.ScoreCalculator
import blackjack.card.BlackJackCard
import blackjack.participant.status.Blackjack
import blackjack.participant.status.Bust

class Dealer(
    scoreCalculator: ScoreCalculator
) {
    private val blackjackStrategy: BlackjackStrategy = BlackjackStrategy(scoreCalculator)
    var bettingAmount: BettingAmount = BettingAmount(0)

    val cards get() = blackjackStrategy.cards

    val status get() = blackjackStrategy.status

    fun drawCard(cards: List<BlackJackCard>) {
        blackjackStrategy.drawCard(cards)
    }

    fun resultScore(): Int {
        return blackjackStrategy.resultScore()
    }

    fun shouldDraw(): Boolean {
        return blackjackStrategy.resultScore() < MAX_DRAW_SCORE
    }

    fun matchingScore(player: Player): Result {
        if (player.status is Blackjack) {
            return when (status) {
                is Blackjack -> Result.Lose
                else -> {
                    bettingAmount = bettingAmount.minusAmount(
                        player.status.calculateBettingAmount(
                            Result.Win,
                            player.bettingAmount
                        )
                    )
                    return Result.Win
                }
            }
        }

        if (player.status is Bust) {
            bettingAmount = bettingAmount.plusAmount(player.bettingAmount)
            return Result.Lose
        }

        if (status is Bust) {
            bettingAmount = bettingAmount.minusAmount(player.bettingAmount)
            return Result.Win
        }

        return when (resultScore() > player.resultScore()) {
            true -> {
                bettingAmount = bettingAmount.plusAmount(player.bettingAmount)
                Result.Lose
            }

            else -> Result.Win
        }
    }

    companion object {
        private const val MAX_DRAW_SCORE: Int = 17
    }
}
