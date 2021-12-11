package blackjack.deck

import blackjack.entity.Card
import blackjack.entity.enums.Denomination
import blackjack.entity.enums.Suit

object CardDeck {

    private val _cards = mutableListOf<Card>()
    val cards get() = _cards

    init {
        Suit
            .values()
            .forEach { suit ->
                Denomination
                    .values()
                    .forEach { denomination ->
                        cards.add(Card(suit, denomination))
                    }
            }

        _cards.shuffle()
    }
}
