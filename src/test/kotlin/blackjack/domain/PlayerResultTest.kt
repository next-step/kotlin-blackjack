package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.math.BigDecimal

@Suppress("NonAsciiCharacters")
class PlayerResultTest {
    @ParameterizedTest
    @MethodSource
    fun `수익을 계산한다`(
        result: PlayerResult,
        expected: BigDecimal,
    ) {
        result.profit() shouldBe expected
    }

    companion object {
        @JvmStatic
        fun `수익을 계산한다`() =
            listOf(
                Arguments.of(
                    PlayerResult("black", Bet(10_000L), PlayerOutcome.WIN),
                    BigDecimal(10_000L),
                ),
                Arguments.of(
                    PlayerResult("black", Bet(10_000L), PlayerOutcome.LOSE),
                    BigDecimal(-10_000L),
                ),
                Arguments.of(
                    PlayerResult("black", Bet(10_000L), PlayerOutcome.DRAW),
                    BigDecimal.ZERO,
                ),
            )
    }
}
