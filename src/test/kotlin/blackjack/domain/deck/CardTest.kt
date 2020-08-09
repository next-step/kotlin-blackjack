package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardTest {

    @DisplayName("끗수가 알파펫인 카드의 점수는 10이다")
    @Test
    fun scoreOfKing() {
        assertThat(Card("K", Suit.SPADE).score).isEqualTo(10)
    }

    @DisplayName("끗수가 숫자인 카드의 점수는 숫자를 그대로 사용한다")
    @Test
    fun scoreOfNumber() {
        assertThat(Card("2", Suit.HEART).score).isEqualTo(2)
    }

    @DisplayName("Ace는 11을 기본값으로 사용한다")
    @Test
    fun scoreOfAce() {
        assertThat(Card("A", Suit.CLUB).score).isEqualTo(11)
    }
}
