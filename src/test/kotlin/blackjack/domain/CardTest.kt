package blackjack.domain

import fixtures.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `card 문자열 변환 테스트`() {
        // given
        val card = createCard()

        // when
        val cardString = card.toString()

        // then
        assertThat(cardString).isEqualTo("10스페이드")
    }
}
