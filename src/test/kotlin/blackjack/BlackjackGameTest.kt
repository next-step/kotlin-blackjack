package blackjack

import blackjack.domain.Player
import blackjack.domain.Players
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class BlackjackGameTest : FunSpec({

    context("firstDraw") {
        test("현재 턴이 firstDraw 턴이 아닌데 요청한 경우 예외가 발생한다.") {
            val blackjackGame = BlackjackGame(turn = 1, players = PLAYERS)
            val exception = shouldThrowExactly<IllegalStateException> { blackjackGame.firstDraw() }
            exception.message shouldBe "first draw 턴이 아닙니다."
        }
    }
}) {
    companion object {
        private val PLAYERS = Players(listOf(Player("최진영")))
    }
}
