package blackjack.service

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackServiceTest {

    @Test
    fun `구분한 참가자명으로 플레이어를 생성하며 한명의 플레이어 당 두장의 카드를 지급 받아 생성한다 (이때 카드는 딜러에게 전달 받는다)`() {
        val players = listOf("test1", "test2")
        val blackjackGame = BlackjackService().initBlackjackGame(players)
        blackjackGame.players.size shouldBe 2
        blackjackGame.players[0].name shouldBe "test1"
        blackjackGame.players[1].name shouldBe "test2"
        blackjackGame.dealer.deck.currentCardCount() shouldBe 52 - 4
    }
}
