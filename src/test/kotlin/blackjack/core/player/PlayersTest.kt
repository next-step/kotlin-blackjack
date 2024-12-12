package blackjack.core.player

import blackjack.core.amount.BettingAmount
import io.kotest.assertions.throwables.shouldThrow
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `Player와 배팅금액 리스트의 개수가 일치하지 않을 경우 발생하는 Exception을 테스트`() {
        shouldThrow<IllegalArgumentException> {
            Players(listOf(Name("1"), Name("2")), listOf(BettingAmount(100)))
        }
    }
}
