package blackjack.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

class BlackjackGameTest : FunSpec({

    context("firstDraw") {
        test("현재 턴이 firstDraw 턴이 아닌데 요청한 경우 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = 1, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.firstDraw() }
            exception.message shouldBe "first draw 턴이 아닙니다."
        }

        test("모든 유저에게 2장의 카드를 첫 드로우한다.") {
            val blackjackGame = BlackjackGame(players = PLAYERS)
            val actual = blackjackGame.firstDraw()

            blackjackGame.cardDeck.size() shouldBe 48
            blackjackGame.turn shouldBe 0
            actual shouldHaveSize 2
        }
    }

    context("isEndGame") {
        forAll(
            row(-1, false),
            row(0, false),
            row(2, true)
        ) { input, expected ->
            test("현재 턴 ${input}이 종료되었음은 ${expected}이다.") {
                val blackjackGame = BlackjackGame(turn = input, players = PLAYERS)
                val actual = blackjackGame.isEndGame()
                actual shouldBe expected
            }
        }
    }
}) {
    companion object {
        private val PLAYERS = Players(listOf(Player("a"), Player("b")))
    }
}
