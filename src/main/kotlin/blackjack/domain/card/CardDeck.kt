package blackjack.domain.card

import java.util.LinkedList

object CardDeck {

    private val CARDS: LinkedList<Card> = LinkedList<Card>()

    fun drawCard(): Card {
        return CARDS.pop()
    }

    fun of(suit: Suit, denomination: Denomination): Card {
        return CARDS.find { it.suit == suit && it.denomination == denomination }
            ?: throw IllegalArgumentException("해당 카드를 찾을 수 없습니다.")
    }

    init {
        for (suit in Suit.values()) {
            for (denomination in Denomination.values()) {
                CARDS.push(Card(suit, denomination))
            }
        }
        CARDS.shuffle()
    }
}
