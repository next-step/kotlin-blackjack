package blackjack.helper

import blackjack.domain.Deck
import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.ShuffleMachine

object DeckHelper {

    fun createMockDeck(): Deck {
        return Deck(shuffleMachine = createShuffleMachine())
    }

    private fun createShuffleMachine(): ShuffleMachine {
        return object : ShuffleMachine {
            override fun shuffle(cards: List<Card>): List<Card> {
                return listOf(
                    Card(number = CardNumber.ACE, pattern = CardPattern.HEART),
                    Card(number = CardNumber.TWO, pattern = CardPattern.HEART),
                    Card(number = CardNumber.THREE, pattern = CardPattern.HEART),
                    Card(number = CardNumber.FOUR, pattern = CardPattern.HEART),
                    Card(number = CardNumber.FIVE, pattern = CardPattern.HEART),
                    Card(number = CardNumber.SIX, pattern = CardPattern.HEART),
                )
            }
        }
    }
}
