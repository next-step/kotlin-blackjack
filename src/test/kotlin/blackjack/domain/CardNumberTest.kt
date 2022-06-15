package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardNumberTest {
    @Test
    fun `CardNumber는 ACE부터 KING까지 13가지 종류로 이루어진다`() {
        assertThat(CardNumber.values()).hasSize(13)
    }
}
