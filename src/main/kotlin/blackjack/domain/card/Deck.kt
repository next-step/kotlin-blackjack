package blackjack.domain.card

import java.util.*

class Deck(cards: List<Card>) {

    private val _cards: LinkedList<Card> = LinkedList(cards)
    val cards: List<Card> = _cards

    fun drawCard(): Card {
        check(_cards.isNotEmpty()) { "더 이상 뽑을 카드가 없습니다." }

        return _cards.removeFirst()
    }

    companion object {
        fun create(): Deck {
            return Deck(CardFactory.cards.shuffled())
        }
    }
}
