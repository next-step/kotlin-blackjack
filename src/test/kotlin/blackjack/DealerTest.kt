package blackjack

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {
    @Test
    fun `딜러가 중복없는 카드를 내놓는지 테스트`() {
        val allCards = mutableSetOf<Card>()

        CardNumber.values().forEach { number ->
            CardShape.values().forEach { shape ->
                allCards.add(Card(number, shape))
            }
        }

        val allCardSize = allCards.size
        repeat(allCardSize) {
            val served = Dealer.popOneCard()

            allCards.remove(served)
        }

        assertThat(allCards).hasSize(0)
    }
}