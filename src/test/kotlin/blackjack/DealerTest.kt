package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.LinkedList

class DealerTest {
    private val dealer = Dealer()

    @Test
    fun `Dealer의 이름은 딜러로 고정된다`() {
        assertThat(dealer.name).isEqualTo("딜러")
    }

    @Test
    fun `딜러는 점수가 16점 이하면 반드시 추가로 카드를 뽑는다`() {
        dealer.hit(LowCardExtractor())
        assertThat(dealer.cardDeck.cards.size).isEqualTo(3)
    }

    class LowCardExtractor : CardExtractor(LinkedList(allCards.shuffled())) {

        override fun addAll() {
            cards.addAll(allCards.shuffled())
        }

        companion object {
            private val allCards: List<Card> = CardType.values().flatMap { cardType ->
                CardNumber.values().filter { it.score <= 8 }.map { cardNumber ->
                    Card(cardType, cardNumber)
                }
            }
        }
    }
}
