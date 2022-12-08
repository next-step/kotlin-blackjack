package blackjack.domain

import kotlin.math.abs

sealed interface Member {

    val cards: Cards

    val cardElements: Set<Card>
        get() = cards.elements

    fun addCard(card: Card) {
        this.cards.add(card)
    }

    fun resultScore() = this.cards.score()

    fun isNearBlackJackThan(otherMember: Member) =
        abs(this.resultScore() - Const.BLACKJACK_NUMBER) <= abs(otherMember.resultScore() - Const.BLACKJACK_NUMBER)

    fun ableMoreDrawCard(): Boolean
}
