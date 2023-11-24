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

    @Test
    fun `손패 점수 최소값을 토대로 카드를 더 뽑을 수 있는지 반환`() {
        val player = Player("test")

        // bust 아닐시 (10점)
        player.hand.add(Card(CardSuitInfo.SPADE, CardNumberInfo.TEN))
        assertThat(player.canDrawMore()).isTrue()

        // blackjack 가능 (11점 / 21점)
        player.hand.add(Card(CardSuitInfo.SPADE, CardNumberInfo.ACE))
        assertThat(player.canDrawMore()).isTrue()

        // bust 가능 (13점 / 23점)
        player.hand.add(Card(CardSuitInfo.SPADE, CardNumberInfo.TWO))
        assertThat(player.canDrawMore()).isTrue()

        // 항상 bust (23점 / 33점)
        player.hand.add(Card(CardSuitInfo.SPADE, CardNumberInfo.KING))
        assertThat(player.canDrawMore()).isFalse()
    }
}
