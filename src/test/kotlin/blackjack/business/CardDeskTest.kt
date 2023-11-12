package blackjack.business

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("카드팩")
class CardDeskTest {

    @Test
    fun `52장의 다른 카드를 가지고 있다`() {
        // given,when
        val cardDesk = CardDesk()

        // then
        cardDesk.cards.size shouldBe 52
        cardDesk.cards.toSet().size shouldBe 52
    }

    @Test
    fun `52장의 카드에서 랜덤으로 카드를 뽑는다`() {
        // given
        val cardDesk = CardDesk()

        // when
        val card = cardDesk.draw()

        // then
        cardDesk.cards.size shouldBe 51
        cardDesk.cards.contains(card) shouldBe false
    }
}
