package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({

    "플레이어가 1명 이하면 예외가 발생한다." {
        // given
        val players = listOf(
            Player(
                name = PlayerName("플레이어1")
            )
        )

        // expected
        shouldThrowWithMessage<IllegalArgumentException>("플레이어는 최소 2명 이상이어야 합니다.") {
            Players(players)
        }
    }
})
