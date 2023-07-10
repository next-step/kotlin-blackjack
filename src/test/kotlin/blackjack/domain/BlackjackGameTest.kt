package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BlackjackGameTest {
    @Test
    internal fun `게임이 시작되면 플레이어는 두장의 카드를 받는다`() {
        val challengerA = Challenger("a")
        val challengerB = Challenger("b")
        val sut = BlackjackGame(Challengers(listOf(challengerA, challengerB)))

        sut.dealInitialHand()

        sut.challengers.forEach { challenger ->
            challenger.deck.size shouldBe 2
        }
        sut.dealer.deck.size shouldBe 2
    }

    @Test
    internal fun `플레이어에게 추가로 카드를 전달한다`() {
        val challenger = Challenger("a")
        val sut = BlackjackGame(Challengers(listOf(challenger)))

        sut.dealCardTo(challenger)

        challenger.deck.size shouldBe 1
    }
}
