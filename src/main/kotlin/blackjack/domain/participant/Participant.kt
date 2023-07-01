package blackjack.domain.participant

import blackjack.domain.card.Card
import blackjack.domain.card.Cards

abstract class Participant(val name: String, val cards: Cards = Cards()) {
    init {
        require(name.isNotBlank()) { NAME_EXCEPTION }
    }

    fun addCard(card: Card) {
        cards.addCard(card)
    }

    fun addCards(cards: List<Card>) {
        cards.map { addCard(it) }
    }

    fun score(): Int {
        return cards.score()
    }

    companion object {
        private const val NAME_EXCEPTION = "이름을 정확하게 입력해주십시오"
    }
}