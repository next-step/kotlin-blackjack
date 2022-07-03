package blackjack.domain.user

import blackjack.domain.Card
import blackjack.domain.Score
import blackjack.domain.Stat
import blackjack.domain.Stat.DRAW
import blackjack.domain.Stat.LOSE
import blackjack.domain.Stat.WIN

class Dealer : User() {

    override val name = NAME

    override fun drawable(): Boolean {
        return Score(cards).value < STOP_POINT
    }

    override fun addCard(card: Card) {
        check(drawable()) { "$name can not draw more than [$STOP_POINT] point" }
        cards.add(card)
    }

    fun versus(player: Player): Stat {
        val score = getScore()
        val playerScore = player.getScore()
        return when {
            playerScore.isBust() -> WIN
            score.isBust() -> LOSE
            score.value > playerScore.value -> WIN
            score.value == playerScore.value -> DRAW
            else -> LOSE
        }
    }

    fun getFirstCard(): Card {
        return cards.first()
    }

    companion object {
        private const val NAME = "딜러"
        const val STOP_POINT = 17
    }
}
