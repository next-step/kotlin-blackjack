package blackjack

import blackjack.Number.ACE
import blackjack.Number.TWO
import blackjack.Sharp.CLOVER
import blackjack.Sharp.HEART
import org.assertj.core.api.Assertions.assertThatIllegalArgumentException
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class PlayerTest {

    @ParameterizedTest
    @ValueSource(strings = ["", "\t", "\n"])
    internal fun `플레이어 이름이 공백문자면 예외가 발생한다`(input: String) {
        // given

        // when, then
        assertThatIllegalArgumentException().isThrownBy {
            Player(input, mockCards())
        }
    }

    fun mockCards(): Cards = Cards(mutableSetOf(Card(ACE, HEART), Card(TWO, CLOVER)))
}
