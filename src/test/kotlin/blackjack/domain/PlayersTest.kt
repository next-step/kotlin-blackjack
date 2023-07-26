package blackjack.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class PlayersTest : StringSpec({
    "players 생성 성공" {
        val players = Players(listOf("edge", "test"))
        players.getPlayer("edge").name shouldBe "edge"
    }

    "player 이름이 중복되면 에러" {
        shouldThrow<IllegalArgumentException> {
            Players(listOf("edge", "edge"))
        }
    }
})
