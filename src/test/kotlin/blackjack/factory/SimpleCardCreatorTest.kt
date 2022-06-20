package blackjack.factory

import blackjack.card.Card
import blackjack.card.CardSymbol
import blackjack.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class SimpleCardCreatorTest {

    @Test
    fun createTest() {
        val cardArrays = Suit.values().flatMap { suit ->
            CardSymbol.values()
                .map { symbol -> Card(suit, symbol) }
        }.toTypedArray()
            .also { it.shuffle() }

        val distinctBySymbol = cardArrays.distinctBy { it.symbol }
        val distinctBySuit = cardArrays.distinctBy { it.suit }

        assertThat(distinctBySymbol.size).isEqualTo(13)
        assertThat(distinctBySuit.size).isEqualTo(4)

        val startCard = SimpleCardCreator.startCard()

        assertThat(startCard.size).isEqualTo(52)
    }
}
