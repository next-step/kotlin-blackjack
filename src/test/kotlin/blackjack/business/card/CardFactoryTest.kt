package blackjack.business.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardFactoryTest {

    @Test
    fun `52장의 다른 카드를 돌려준다`() {
        // given,when
        val cards = CardFactory.getCards()

        // then
        cards.toSet().size shouldBe 52
    }
}
