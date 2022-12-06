package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class CardTest {
    @Test
    fun `카드는 모양과 숫자를 가진다`() {
        val cardSpadeTwo = Card(Shape.SPADE, Denomination.TWO)

        cardSpadeTwo.shape shouldBe Shape.SPADE
        cardSpadeTwo.denomination shouldBe Denomination.TWO
        cardSpadeTwo.denomination.score shouldBe 2
    }
}
