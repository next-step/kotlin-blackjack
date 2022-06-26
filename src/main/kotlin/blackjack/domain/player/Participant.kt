package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Hand

private val NAME_LENGTH_RANGE = 2..5

class Participant(_name: String) {
    val name = _name.trim()
    val hand = Hand()

    init {
        require(name.length in NAME_LENGTH_RANGE) { "이름의 길이는 ${NAME_LENGTH_RANGE}자 사이여야 합니다." }
    }

    infix fun receive(card: Card) {
        hand.add(card)
    }

    fun isBust(): Boolean {
        return hand.isBust()
    }
}
