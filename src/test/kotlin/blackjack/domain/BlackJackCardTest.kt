package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BlackJackCardTest {
    @Test
    fun `카드 생성 테스트`() {
        val blackJackCard = BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)
        assertThat(blackJackCard.shape).isEqualTo(BlackJackCardShape.HEART)
        assertThat(blackJackCard.number).isEqualTo(BlackJackCardNumber.ACE)
        assertThat(blackJackCard.number.values).containsExactly(1, 11)
    }
}
