package blackjack.card

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class CardTest : AnnotationSpec() {

    @Test
    fun `CARD의 fullName이 반환된다`() {
        val sut = Card(CardNumber.TWO, CardShape.HEART)

        val result = sut.fullName()

        result shouldBe "2하트"
    }
}
