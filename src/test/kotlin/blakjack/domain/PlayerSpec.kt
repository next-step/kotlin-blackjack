package blakjack.domain

import blakjack.domain.Player.Result.LOSE
import blakjack.domain.Player.Result.WIN
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PlayerSpec : DescribeSpec({
    describe("플레이어 생성 검증") {
        context("이름이 '홍길동'인 플레이어를 생성하면") {
            val player = Player("홍길동")

            it("이름은 '홍길동'이다.") {
                player.name shouldBe "홍길동"
            }
            it("카드 목록은 비어있다.") {
                player.cards shouldBe Cards.empty()
            }
        }
    }

    describe("결과(win, lose) 검증") {
        val playerA = Player("A")
        val playerB = Player("B")

        context("플레이어 A가 플레이어 B를 이기면") {
            playerA.win(playerB)

            it("플레이어 A의 결과는 WIN 이다.") {
                playerA.result shouldBe WIN
            }
            it("플레이어 B의 결과는 LOSE 이다.") {
                playerB.result shouldBe LOSE
            }
        }
    }
})
