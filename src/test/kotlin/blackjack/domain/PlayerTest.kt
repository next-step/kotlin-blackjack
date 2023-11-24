package blackjack.domain

import blackjack.domain.BlackjackUtil.INITIAL_CARD_NUM
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class PlayerTest {
    @ParameterizedTest
    @ValueSource(strings = ["", "  "])
    fun `플레이어 이름으로 빈 값 전달시 예외 발생`(name: String) {
        assertThatThrownBy { Player(name) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("플레이어 이름은 빈 값일 수 없습니다.")
    }

    @Test
    fun `손패 중 초기 카드만 반환`() {
        val player = Player("test")
        val allCards = listOf(
            Card(CardSuitInfo.SPADE, CardNumberInfo.ACE),
            Card(CardSuitInfo.SPADE, CardNumberInfo.TWO),
            Card(CardSuitInfo.SPADE, CardNumberInfo.THREE),
        )

        allCards.forEach { player.hand.add(it) }

        assertThat(player.hand.size()).isEqualTo(allCards.size)
        assertThat(player.initialCards()).isEqualTo(allCards.take(INITIAL_CARD_NUM))
    }
}
