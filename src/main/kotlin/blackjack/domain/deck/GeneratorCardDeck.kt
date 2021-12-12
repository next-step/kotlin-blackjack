package blackjack.domain.deck

import blackjack.domain.entity.Card
import blackjack.domain.entity.enums.Denomination
import blackjack.domain.entity.enums.Suit

object GeneratorCardDeck {

    fun generatorCardDeck(): MutableList<Card> {

        val cards = mutableListOf<Card>()

        Suit
            .values()
            .forEach { suit ->
                Denomination
                    .values()
                    .forEach { denomination ->
                        cards.add(Card(suit, denomination))
                    }
            }

        cards.shuffle()
        return cards
    }
}
