package camp.nextstep.blackjack.ui

import camp.nextstep.blackjack.card.Card

class FaceUpCard(card: Card) {

    val number = card.number.value
    val suit = card.suit.name

    override fun toString(): String {
        return "{$number:$suit}"
    }

    companion object {
        fun from(cards: List<Card>): List<FaceUpCard> {
            return cards.map { FaceUpCard(it) }
        }
    }
}
