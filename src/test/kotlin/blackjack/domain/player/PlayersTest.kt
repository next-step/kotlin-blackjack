package blackjack.domain.player

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerNames
import blackjack.domain.player.Players
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class PlayersTest : DescribeSpec({
    describe("플레이어들 생성") {
        context("플레이어 이름이 주어지면") {
            val name1 = PlayerName("홍길동")
            val name2 = PlayerName("베트맨")
            val names = PlayerNames(listOf(name1, name2))
            it("주어진 이름 순서대로 플레이어들 생성") {
                val result = Players.from(names)

                result.value[0] shouldBe Player(name1)
                result.value[1] shouldBe Player(name2)
            }
        }

        context("이름이 2개가 아닐 경우") {
            forAll(
                row(PlayerNames(listOf(PlayerName("One")))),
                row(PlayerNames(listOf(PlayerName("One"), PlayerName("Two"), PlayerName("Three")))),
            ) { names ->
                it("플레이어 생성 실패") {
                    shouldThrowExactly<IllegalArgumentException> {
                        Players.from(names)
                    }
                }
            }
        }
    }
})
