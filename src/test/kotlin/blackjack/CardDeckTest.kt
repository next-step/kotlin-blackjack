package blackjack

import blackjack.domain.Card
import blackjack.domain.CardDeck
import blackjack.domain.CardType
import blackjack.domain.CardValue
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardDeckTest {
    private val cardDeck = CardDeck()

    @Test
    fun `카드 생성 테스트`() {
        var count = 0
        CardType.values().forEach { cardType ->
            CardValue.values().forEach {
                assertThat(cardDeck.cards[count++] == Card(cardType, it))
            }
        }
    }

    @Test
    fun `카드 뽑고 덱에서 없애기 테스트`() {
        val firstDeckSize = cardDeck.cards.size
        val pickCard = cardDeck.pickCard()
        assertThat(cardDeck.cards.contains(pickCard)).isEqualTo(false)
        assertThat(firstDeckSize - 1).isEqualTo(cardDeck.cards.size)
    }
}
