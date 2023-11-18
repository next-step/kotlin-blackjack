package blackjack.domain.player

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayersTest : DescribeSpec({
    describe("플레이어들 생성") {
        context("플레이어 이름이 주어지면") {
            val name1 = PlayerName("홍길동")
            val name2 = PlayerName("베트맨")
            val names = PlayerNames(listOf(name1, name2))

            val result = Players.from(names)
            it("주어진 이름 순서대로 플레이어들 생성") {
                result.allPlayers[0] shouldBe Player(name1)
                result.allPlayers[1] shouldBe Player(name2)
            }
            it("첫 이름의 플레이어가 첫 순번") {
                result.playerInTurn shouldBe Player(name1)
            }
        }
    }
})
