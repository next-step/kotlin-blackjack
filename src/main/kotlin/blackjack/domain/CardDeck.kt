package blackjack.domain

import kotlin.random.Random


class CardDeck {
    val cards: Cards = Cards(
        CardShape.values()
            .flatMap { shape -> CardCharacter.values().map { cardCharacter -> Card(cardCharacter, shape) } }
            .toMutableSet()
    )

    fun draw(): Card {
        require(cards.isNotEmpty()) { "카드덱이 비어 있습니다." }
        val card = cards.elementAt(Random.nextInt(cards.size))
        cards.remove(card)
        return card
    }
}
