package blackjack.domain

import blackjack.util.createPlayers
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayersTest {
    @Test
    fun `플레이어가 2명이 아니면 에러가 발생한다`() {
        shouldThrowWithMessage<IllegalArgumentException>(message = "블랙잭 게임에서 플레이어는 2명이어야 합니다") {
            createPlayers("pablo")
        }
        shouldThrowWithMessage<IllegalArgumentException>(message = "블랙잭 게임에서 플레이어는 2명이어야 합니다") {
            createPlayers("pablo", "musk", "vivi")
        }
    }

    @Test
    fun `플레이어 이름을 나열 할 수 있다`() {
        val players = createPlayers("pablo", "musk")
        players.toPlayerNamesString() shouldBe "pablo, musk"
    }
}
