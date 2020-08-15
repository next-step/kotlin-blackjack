package blackjack.domain

import blackjack.domain.Game.Companion.DEFAULT_CARD_AMOUNT
import blackjack.domain.Game.Companion.MAXIMUM_GAME_SCORE

interface Participant {

    fun setUpWithCards(dealer: Dealer) =
        (1..DEFAULT_CARD_AMOUNT).map { this.draw(dealer.pickCard()) }

    fun drawIf(dealer: Dealer, fitsCondition: () -> Boolean): Participant? {
        if (fitsCondition()) {
            draw(dealer.pickCard()) ?: return null
        }
        return this
    }

    fun draw(newCard: Card?): Cards?

    fun hasMoreScoreThanMax(score: Int) = score >= MAXIMUM_GAME_SCORE

    fun amountOfCards(): Int

    fun totalScore(): Int

    fun stateOfCards(): String
}
