package blackjack.domain.player

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerTest : DescribeSpec({
    describe("플레이어 생성") {
        context("플레이어 이름이 주어지면") {
            val name = PlayerName("홍길동")
            it("플레이어 생성") {
                val result = Player(name)

                result.name shouldBe name
            }
        }
    }
})
