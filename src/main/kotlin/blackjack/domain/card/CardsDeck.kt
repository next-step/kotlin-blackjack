package blackjack.domain.card

import blackjack.exception.CardExhaustException

class CardsDeck {

    private val cards = CardDenomination.values().flatMap { cardDenomination ->
        CardPattern.values().map { cardPattern ->
            Card(
                pattern = cardPattern,
                denomination = cardDenomination
            )
        }
    }
        .shuffled()
        .toMutableList()

    fun divide(): Card {
        if (cards.size == ZERO) {
            throw CardExhaustException("카드가 모두 소진되었습니다.")
        }

        return cards.removeFirst()
    }

    companion object {
        private const val ZERO = 0
    }
}
