package blackjack.domain.card

import kotlin.random.Random

class CardSet {
    private var cards: MutableSet<Card> = CardNumber.values().flatMap { number ->
        CardSymbol.values().map { symbol ->
            Card(number, symbol)
        }
    }.toMutableSet()

    fun peekCards(n: Int): List<Card> {
        val random = Random.Default
        val peekedCards = mutableListOf<Card>()
        val cardList = cards.toMutableList()
        repeat(n) {
            val randomIndex = random.nextInt(cardList.size)
            val peekedCard = cardList.removeAt(randomIndex)
            peekedCards += peekedCard
        }
        cards.removeAll(peekedCards.toSet())
        return peekedCards
    }
}
