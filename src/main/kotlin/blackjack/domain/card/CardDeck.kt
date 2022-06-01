package blackjack.domain.card

import blackjack.domain.card.type.Denomination
import blackjack.domain.card.type.Suit

class CardDeck (
    private val cards: Set<Card>
) {
    init {
        require(cards.size == 52) { "블랙젝을 위한 카드는 52장이 필요합니다." }
        require(cards.groupBy(Card::suit).keys.size == 4) { "게임을 진행하기 위해선 4가지 문양이 모두 필요합니다." }
    }
}

fun setupCardDeck(block: CardDeckBuilder.() -> Unit): CardDeck {
    return CardDeckBuilder().apply(block).build()
}

class CardDeckBuilder {
    private val cards: MutableList<Card> = mutableListOf()

    fun spade() {
        cards.addAll(Denomination.values().map { Card(it, Suit.SPADE) })
    }

    fun diamond() {
        cards.addAll(Denomination.values().map { Card(it, Suit.DIAMOND) })
    }

    fun heart() {
        cards.addAll(Denomination.values().map { Card(it, Suit.HEART) })
    }

    fun club() {
        cards.addAll(Denomination.values().map { Card(it, Suit.CLUB) })
    }

    fun build(): CardDeck {
        return CardDeck(cards.toSet())
    }
}
