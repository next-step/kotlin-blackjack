package blackjack.domain

import java.lang.IllegalStateException

class Deck {
    private val cards = ArrayDeque<Card>()
    val size: Int
        get() = cards.size

    init {
        cards.addAll(Card.ALL_CARDS)
        cards.shuffle()
    }

    fun draw(): Card = cards.removeFirstOrNull() ?: throw IllegalStateException("덱에 카드가 없습니다")
}
