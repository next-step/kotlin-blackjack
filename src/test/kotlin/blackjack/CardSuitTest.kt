package blackjack

import blackjack.domain.card.suit.Clover
import blackjack.domain.card.suit.Diamond
import blackjack.domain.card.suit.Heart
import blackjack.domain.card.suit.Spade
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardSuitTest {
    @Test
    fun `카드 Suit가 동일 Suit인지 확인 할 수 있다`() {
        assertThat(Spade()).isEqualTo(Spade())

        assertThat(Diamond()).isEqualTo(Diamond())

        assertThat(Clover()).isEqualTo(Clover())

        assertThat(Heart()).isEqualTo(Heart())
    }
    @Test
    fun `카드 Suit가 다은 Suit인지 확인 할 수 있다`() {
        assertThat(Spade()).isNotEqualTo(Diamond())

        assertThat(Spade()).isNotEqualTo(Clover())

        assertThat(Spade()).isNotEqualTo(Heart())
    }
}
