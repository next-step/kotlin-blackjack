package blackjack.domain.card

import blackjack.domain.card.type.Ace
import blackjack.domain.card.type.Eight
import blackjack.domain.card.type.Five
import blackjack.domain.card.type.Four
import blackjack.domain.card.type.Jack
import blackjack.domain.card.type.King
import blackjack.domain.card.type.Nine
import blackjack.domain.card.type.Queen
import blackjack.domain.card.type.Seven
import blackjack.domain.card.type.Six
import blackjack.domain.card.type.Suit
import blackjack.domain.card.type.Ten
import blackjack.domain.card.type.Three
import blackjack.domain.card.type.Two

private const val CARD_SIZE = 52
private const val SUIT_SIZE = 4

class CardDeck(
    cards: List<Card>
) {
    private val _cards: MutableList<Card>
    val cards: List<Card>
        get() = _cards.toList()

    init {
        require(cards.size == CARD_SIZE) { "블랙젝을 위한 카드는 52장이 필요합니다." }
        require(cards.groupBy(Card::suit).keys.size == SUIT_SIZE) { "게임을 진행하기 위해선 4가지 문양이 모두 필요합니다." }

        _cards = cards.toMutableList()
    }

    fun draw(): Card = _cards.removeFirstOrNull() ?: throw IllegalArgumentException("더이상 남은 카드가 없어 게임을 진행할수 없습니다.")
}

fun setupCardDeck(block: CardDeckBuilder.() -> Unit): CardDeck {
    return CardDeckBuilder().apply(block).build()
}

class CardDeckBuilder {
    private val cards: MutableList<Card> = mutableListOf()

    fun spade() {
        cards.addAll(denominations.map { Card(it, Suit.SPADE) })
    }

    fun diamond() {
        cards.addAll(denominations.map { Card(it, Suit.DIAMOND) })
    }

    fun heart() {
        cards.addAll(denominations.map { Card(it, Suit.HEART) })
    }

    fun club() {
        cards.addAll(denominations.map { Card(it, Suit.CLUB) })
    }

    fun build(): CardDeck {
        return CardDeck(cards.shuffled())
    }

    companion object {
        private val denominations = listOf(
            Ace(),
            Two(),
            Three(),
            Four(),
            Five(),
            Six(),
            Seven(),
            Eight(),
            Nine(),
            Ten(),
            Jack(),
            Queen(),
            King()
        )
    }
}
