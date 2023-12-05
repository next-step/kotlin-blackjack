package blackjack.domain

import fixtures.createCard
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `card 문자열 변환 테스트`() {
        // given
        val card = createCard()

        // when
        val cardString = card.toString()

        // then
        cardString shouldBe "10스페이드"
    }
}
