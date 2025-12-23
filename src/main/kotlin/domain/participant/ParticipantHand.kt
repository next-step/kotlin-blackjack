package domain.participant

import domain.card.Card
import domain.card.CardValue

class ParticipantHand() {
    private val _ownCards: MutableList<Card> = mutableListOf()

    val ownCards: List<Card>
        get() = _ownCards.toList()

    companion object {
        const val BLACKJACK_MAX_SCORE = 21
    }

    fun receiveCard(card: Card) {
        _ownCards.add(card)
    }

    fun handSize() = _ownCards.size

    fun calculateScore(scope: Int = _ownCards.size): Int {
        val scopedCard = _ownCards.take(scope)
        var total = scopedCard.sumOf { it.value.basicScore }
        var aceCount = scopedCard.count { it.value == CardValue.ACE }

        while (total > BLACKJACK_MAX_SCORE && aceCount > 0) {
            total -= 10 // ACE 11 → 1 변경
            aceCount--
        }

        return total
    }

    override fun toString(): String {
        return _ownCards.joinToString()
    }
}
