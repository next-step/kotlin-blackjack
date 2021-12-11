package blackjack.deck

import blackjack.entity.Card
import blackjack.entity.enums.Denomination
import blackjack.entity.enums.Suit

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
