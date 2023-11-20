package blackjack.model

import blackjack.dto.GameResult
import blackjack.dto.Money
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class PlayerResultStatusTest {

    @ParameterizedTest
    @MethodSource("getPriceTestInput")
    fun `결과에 따른 상금이 다르다`(
        resultStatus: PlayerResultStatus,
        expected: Double
    ) {
        val money = Money(1000)
        val player = Player("test", money).apply {
            setGameResult(GameResult(0, resultStatus))
        }

        assertThat(PlayerResultStatus.getPrice(player)).isEqualTo(money * expected)
    }

    companion object {
        @JvmStatic
        fun getPriceTestInput(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    PlayerResultStatus.BLACKJACK,
                    1.5
                ),
                Arguments.of(
                    PlayerResultStatus.WIN,
                    1.0
                ),
                Arguments.of(
                    PlayerResultStatus.TIE,
                    0.0
                ),
                Arguments.of(
                    PlayerResultStatus.LOSE,
                    -1.0
                ),
            )
        }
    }
}
