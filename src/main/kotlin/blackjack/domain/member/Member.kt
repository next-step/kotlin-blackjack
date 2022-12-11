package blackjack.domain.member

import blackjack.domain.Card
import blackjack.domain.Cards
import blackjack.domain.Const
import kotlin.math.abs

sealed interface Member {
    val name: String
    val cards: Cards

    val cardElements: Set<Card>
        get() = cards.elements

    fun addCard(card: Card) {
        this.cards.add(card)
    }

    fun resultScore() = this.cards.score()
    fun isOverBlackjackNumber() = resultScore() > Const.BLACKJACK_NUMBER
    fun isEqualsBlackjackNumber() = resultScore() == Const.BLACKJACK_NUMBER

    fun isNearBlackJackThan(otherMember: Member) =
        abs(this.resultScore() - Const.BLACKJACK_NUMBER) < abs(otherMember.resultScore() - Const.BLACKJACK_NUMBER)

    fun isEqualNumberThan(otherMember: Member) = this.resultScore() == otherMember.resultScore()

    fun isWin(otherMember: Member): Boolean {
        if (isOverBlackjackNumber()) {
            return false
        }

        return conditionOfWin(otherMember)
    }

    fun isLose(otherMember: Member) = !isWin(otherMember)

    fun ableMoreDrawCard(): Boolean
    fun conditionOfWin(otherMember: Member): Boolean
}
