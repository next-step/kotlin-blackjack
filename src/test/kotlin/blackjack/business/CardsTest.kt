package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드팩")
class CardsTest {

    @Test
    fun `52장의 다른 카드를 가지고 있다`() {
        // given,when
        val cards = Cards()

        // then
        cards.cards.size shouldBe 52
        cards.cards.toSet().size shouldBe 52
    }

    @Test
    fun `52장의 카드에서 랜덤으로 카드를 뽑는다`() {
        // given
        val cards = Cards()

        // when
        val card = cards.draw()

        // then
        cards.cards.size shouldBe 51
        cards.cards.contains(card) shouldBe false
    }
}
