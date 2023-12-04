package camp.nextstep.edu.step.step2.domain.card

import camp.nextstep.edu.step.step2.domain.card.type.CardNumbers
import camp.nextstep.edu.step.step2.domain.card.type.CardType

sealed interface CardDeck {
    fun draw(): Card

    class DrawCard : CardDeck {
        private var card = deck()

        override fun draw(): Card {
            if (!card.hasNext()) {
                card = deck()
            }
            return card.next()
        }

        private fun deck(): Iterator<Card> {
            return (0 until 4)
                .flatMap { cards() }
                .shuffled()
                .iterator()
        }

        private fun cards(): List<Card> {
            return CardType.values().flatMap { shape ->
                CardNumbers.values().map { number ->
                    Card(number, shape)
                }
            }
        }
    }

}
