package blackjack.domain

import blackjack.domain.Game.Companion.MAXIMUM_GAME_SCORE

interface Participant {

    fun drawCardIf(newCard: Card?, fitsCondition: () -> Boolean): Participant? {
        if (fitsCondition()) {
            draw(newCard) ?: return null
        }
        return this
    }

    fun draw(newCard: Card?): Cards?

    fun hasScoreMoreThanMax(score: Int) = score >= MAXIMUM_GAME_SCORE

    fun amountOfCards(): Int

    fun totalScore(): Int

    fun stateOfCards(): String
}
