package blackjack.application

import io.kotest.matchers.throwable.shouldHaveMessage
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class BlackJackServiceTest {

    @ParameterizedTest
    @MethodSource
    fun `플레이어 수가 1 미만 26 초과인 경우 예외`(names: List<String>) {
        assertThrows<IllegalArgumentException> { BlackJackService.createPlayers(names) }
            .shouldHaveMessage("플레이어 수는 1 이상 26 이하여야 합니다.")
    }

    companion object {

        @JvmStatic
        fun `플레이어 수가 1 미만 26 초과인 경우 예외`(): List<Arguments> {
            return listOf(
                Arguments.arguments(listOf<String>()),
                Arguments.arguments(listOf("1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1", "1")),
            )
        }
    }
}
