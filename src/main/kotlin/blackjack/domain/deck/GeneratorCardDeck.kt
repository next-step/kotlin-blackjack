package blackjack.domain.deck

import blackjack.domain.entity.Card
import blackjack.domain.entity.enums.Denomination
import blackjack.domain.entity.enums.Suit

object GeneratorCardDeck {

    fun generatorCardDeck(): MutableList<Card> = Suit.values()
        .flatMap {
            Denomination
                .values()
                .map { denomination -> Card(it, denomination = denomination) }
        }
        .shuffled()
        .toMutableList()
}
