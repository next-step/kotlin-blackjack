package blackjack.domain.card

import kotlin.random.Random

class CardDeck {
    var cards: Cards = Cards(
        CardShape.values()
            .flatMap { shape -> CardCharacter.values().map { cardCharacter -> Card(cardCharacter, shape) } }
            .toMutableList()
    )

    fun draw(): Card {
        require(cards.isNotEmpty()) { "카드덱이 비어 있습니다." }
        val card = cards.elementAt(Random.nextInt(cards.size))
        cards -= card
        return card
    }
}
