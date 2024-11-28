package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({
    "블랙잭 플레이어는 1~6명 이어야 합니다." {
        shouldThrow<IllegalArgumentException> {
            Players(
                listOf(
                    Player("1"),
                    Player("2"),
                    Player("3"),
                    Player("4"),
                    Player("5"),
                    Player("6"),
                    Player("7")
                )
            )
        }

        shouldThrow<IllegalArgumentException> { Players(emptyList()) }
    }
})
