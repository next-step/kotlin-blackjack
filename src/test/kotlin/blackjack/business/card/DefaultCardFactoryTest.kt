package blackjack.business.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class DefaultCardFactoryTest {

    @Test
    fun `52장의 다른 카드를 돌려준다`() {
        // given
        val defaultCardFactory = DefaultCardFactory()

        // when
        val cards = defaultCardFactory.getCards()

        // then
        cards.toSet().size shouldBe 52
    }
}
