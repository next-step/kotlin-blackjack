package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardsTest {
    @Test
    fun `카드들을 가지고 추가할 수 있다`() {
        val cards = Cards()
        cards.count shouldBe 0
        cards.add(Card(CardNumber.of("K"), CardShape.HEART))
        cards.add(Card(CardNumber.of("4"), CardShape.DIAMOND))
        cards.list
    }
}
