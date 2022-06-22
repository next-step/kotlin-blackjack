package blackjack.domain.player

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec

class PlayersTest : StringSpec({
    "플레이어의 이름이 중복되면 예외를 발생한다." {
        // given
        val players = listOf(
            Player("김경록"),
            Player("김경록"),
        )

        // when // then
        shouldThrowExactly<IllegalArgumentException> { Players(players) }
    }
})
