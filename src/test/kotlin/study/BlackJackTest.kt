package study

import blackjack.domain.card.Denomination
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackTest {

    @Test
    fun `enum 클래스 이름 테스트`() {
        assertThat(Denomination.ACE.isAce).isTrue()
    }
}