package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class BlackjackGameTest {
    @Test
    internal fun `게임이 시작되면 플레이어는 두장의 카드를 받는다`() {
        val playerA = Player("a")
        val playerB = Player("b")
        val sut = BlackjackGame(Players(listOf(playerA, playerB)))

        sut.dealInitialHand()

        playerA.deck.size shouldBe 2
        playerB.deck.size shouldBe 2
    }

    @Test
    internal fun `플레이어에게 추가로 카드를 전달한다`() {
        val playerA = Player("a")
        val sut = BlackjackGame(Players(listOf(playerA)))

        sut.dealCardTo(playerA)

        playerA.deck.size shouldBe 1
    }
}
