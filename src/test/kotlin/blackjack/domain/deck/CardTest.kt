package blackjack.domain.deck

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

internal class CardTest {

    @DisplayName("끗수가 알파펫인 카드의 점수는 10이다")
    @ParameterizedTest
    @EnumSource(value = Pip::class, names = ["KING"])
    fun scoreOfKing(pip: Pip) {
        assertThat(Card(pip, Suit.SPADE).getScore()).isEqualTo(10)
    }

    @DisplayName("끗수가 숫자인 카드의 점수는 숫자를 그대로 사용한다")
    @ParameterizedTest
    @EnumSource(value = Pip::class, names = ["TWO"])
    fun scoreOfNumber(pip: Pip) {
        assertThat(Card(pip, Suit.HEART).getScore()).isEqualTo(2)
    }

    @DisplayName("Ace는 11을 기본값으로 사용한다")
    @ParameterizedTest
    @EnumSource(value = Pip::class, names = ["ACE"])
    fun scoreOfAce(pip: Pip) {
        assertThat(Card(pip, Suit.CLUB).getScore()).isEqualTo(11)
    }
}
