package blackjack.domain.card

import blackjack.exception.CardExhaustException

class CardsDeck {

    private val cards = CardDenomination.values().flatMap { cardDenomination ->
        CardPattern.values().map { cardPattern ->
            Card(
                pattern = cardPattern.name,
                denomination = cardDenomination
            )
        }
    }.shuffled()

    private var dividedCount = 0

    fun divide(): Card {
        if (dividedCount >= cards.size) {
            throw CardExhaustException("카드가 모두 소진되었습니다.")
        }

        return cards[dividedCount++]
    }
}
