package blackjack.domain.card

import blackjack.domain.exception.AllCardExhaustException

class CardDeck {
    private val cards: MutableList<Card>

    init {
        cards = CardSuit.values()
            .map { makeCard(it) }
            .flatten()
            .toMutableList()
    }

    fun getCurrentCardSize(): Int {
        return cards.size
    }

    fun isContains(card: Card): Boolean {
        return cards.contains(card)
    }

    fun pickCard(): Card {
        check(getCurrentCardSize() > 0) { throw AllCardExhaustException("52장의 카드를 모두 소진해서 더이상 뽑을 수 없습니다.") }

        val randomIndex = (0 until cards.size).random()
        return cards.removeAt(randomIndex)
    }

    fun pickCards(size: Int): MutableList<Card> {
        val pickCards: MutableList<Card> = mutableListOf()

        repeat(size) {
            pickCards.add(pickCard())
        }

        return pickCards
    }

    fun pickCardByNumber(number: Int): Card {
        val pickCard = cards.first { it.number == number }
        cards.remove(pickCard)
        return pickCard
    }

    private fun makeCard(cardSuit: CardSuit): List<Card> {
        val cards: MutableList<Card> = mutableListOf()

        for (number in Card.BASIC_CARD_RANGE) {
            cards.add(Card.BasicCard(cardSuit = cardSuit, number = number))
        }

        cards.add(Card.AceCard(cardSuit = cardSuit))
        cards.add(Card.JackCard(cardSuit = cardSuit))
        cards.add(Card.QueenCard(cardSuit = cardSuit))
        cards.add(Card.KingCard(cardSuit = cardSuit))

        return cards
    }
}
