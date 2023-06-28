package blackjack.player

import domain.player.BetAmount
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

class BetAmountTest {

    @ParameterizedTest
    @ValueSource(ints = [0, -1])
    fun `베팅 금액이 0원 이하일 경우 예외 발생`(betAmount: Int) {
        shouldThrow<IllegalArgumentException> {
            BetAmount(betAmount)
        }
    }
}
