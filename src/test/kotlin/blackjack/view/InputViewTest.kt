package blackjack.view

import blackjack.view.input.StubInput
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class InputViewTest : DescribeSpec({

    describe("askPlayers") {
        context("players 입력일 경우") {
            it(", 만큼 이름이 분리된다") {
                val sut = InputView(StubInput.ofPlayers("pobi,jason"))

                sut.players.size shouldBe 2
                sut.players[0] shouldBe "pobi"
                sut.players[1] shouldBe "jason"
            }

            it("띄어쓰기는 무시하고 이름이 분리된다") {
                val sut = InputView(StubInput.ofPlayers(" pobi, jason "))

                sut.players[0] shouldBe "pobi"
                sut.players[1] shouldBe "jason"
            }

            it(", 가 없는 입력은 1명으로 반환된다") {
                val sut = InputView(StubInput.ofPlayers("pobi"))

                sut.players.size shouldBe 1
                sut.players[0] shouldBe "pobi"
            }
        }

        context("hasNextCard 입력일 경우") {
            it("y를 입력하면 true가 반환된다") {
                val sut = InputView(StubInput.ofNextCard("y"))

                val result = sut.hasNextCard("pobi")

                result shouldBe true
            }

            it("n를 입력하면 false가 반환된다") {
                val sut = InputView(StubInput.ofNextCard("n"))

                val result = sut.hasNextCard("pobi")

                result shouldBe false
            }

            it("y 혹은 n이 아니면 IllegalArgumentException 가 발생한다") {
                val sut = InputView(StubInput.ofNextCard("a"))

                val exception = shouldThrow<IllegalArgumentException> {
                    sut.hasNextCard("")
                }

                exception.message shouldBe "y 혹은 n으로 입력해주세요. 현재입력값=a"
            }
        }
    }
})
