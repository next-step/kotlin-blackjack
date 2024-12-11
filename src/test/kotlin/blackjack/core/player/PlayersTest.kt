package blackjack.core.player

import blackjack.core.amount.BettingAmount
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PlayersTest {

    @Test
    fun `생성자를 테스트`() {
        shouldThrow<IllegalArgumentException> {
            Players(listOf(Name("1"), Name("2")), listOf(BettingAmount(100)))
        }
    }

}