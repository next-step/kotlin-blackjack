package blackjack

import blackjack.Number.ACE
import blackjack.Number.THREE
import blackjack.Number.TWO
import blackjack.Sharp.CLOVER
import blackjack.Sharp.DIAMOND
import blackjack.Sharp.HEART
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = ["", "\t", "\n"])
    internal fun `플레이어 이름이 공백문자면 예외가 발생한다`(input: String) {
        // given

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Player(input, Cards(Card(ACE, HEART), Card(TWO, CLOVER)))
        }
    }

    @Test
    internal fun `플레이어의 카드가 더해진다`() {
        // given
        val player = Player("user", Cards(Card(ACE, HEART), Card(TWO, CLOVER)))

        // when
        player.addCard(Card(THREE, DIAMOND))

        // then
        assertAll(
            { assertThat(player.cards.size).isEqualTo(3) },
            {
                assertThat(player.cards.items).containsExactly(
                    Card(ACE, HEART),
                    Card(TWO, CLOVER),
                    Card(THREE, DIAMOND)
                )
            }
        )
    }

    @Test
    internal fun `보유 카드 점수 합을 반환한다`() {
        // given
        val player = Player("user", Cards(Card(ACE, HEART), Card(TWO, CLOVER)))
        // when
        val resultScore = player.resultScore()
        // then
        assertThat(resultScore).isEqualTo(13)
    }
}
