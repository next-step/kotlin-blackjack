package blackjack.domain.participant

import blackjack.test.TestObjectGenerator
import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({
    "플레이어의 이름이 중복되면 예외를 던진다." {
        shouldThrowAny {
            Players(
                listOf(
                    TestObjectGenerator.player(name = "p1", betAmount = BetAmount(1000)),
                    TestObjectGenerator.player(name = "p1", betAmount = BetAmount(1000))
                )
            )
        }
    }
})
