package blackjack.dealer

import io.kotest.core.spec.style.AnnotationSpec
import io.kotest.matchers.shouldBe

class DealerDeckTest : AnnotationSpec() {

    @Test
    fun `총 48개의 카드를 가진다`() {
        val sut = DealerDeck()

        sut.size() shouldBe 48
    }

    @Test
    fun `카드 1장을 뽑으면 남은 카드는 47장이다`() {
        val sut = DealerDeck()

        sut.draw()

        sut.size() shouldBe 47
    }
}
