package blackjack.domain.card

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

    @Test
    fun `카드를 문자열로 변환할 수 있다`() {
        val cardSpadeTwo = Card(Shape.SPADE, Denomination.TWO)
        val cardClubAce = Card(Shape.CLUB, Denomination.ACE)

        cardSpadeTwo.toString() shouldBe "2스페이드"
        cardClubAce.toString() shouldBe "A클로버"
    }
}
