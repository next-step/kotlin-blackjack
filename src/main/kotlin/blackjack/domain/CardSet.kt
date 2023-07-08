package blackjack.domain

import java.util.LinkedList

/**
 * ### 카드 세트를 표현하는 클래스입니다 기본적으로 52장의 중복없는 카드 모음을 가집니다
 *
 * 카드 세트에서 카드를 한장씩 뽑을 수 있습니다
 */
class CardSet(
    cards: Set<Card> = Card.CARD_SET
) {
    private val _cards: LinkedList<Card> = LinkedList<Card>()
        .apply {
            addAll(cards)
            shuffle()
        }

    val size: Int
        get() = _cards.size

    fun pop(): Card {
        return _cards.removeFirstOrNull() ?: throw IllegalStateException("There are no more cards to draw from the CardSet")
    }
}
