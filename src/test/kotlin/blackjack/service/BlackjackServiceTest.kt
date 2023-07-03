package blackjack.service

import blackjack.dto.PlayerInfo
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackjackServiceTest {

    @Test
    fun `블랙잭 게임 초기 시작 시 플레이어 당 2장 씩 딜러도 2장 카드를 가진다`() {
        val players = listOf(PlayerInfo("test1", 10000), PlayerInfo("test2", 10000))
        val blackjackGame = BlackjackService().initBlackjackGame(players)

        val player1CardCount = blackjackGame.players[0].cards.cards.size
        val player2CardCount = blackjackGame.players[1].cards.cards.size
        val dealerCardCount = blackjackGame.dealer.cards.cards.size
        player1CardCount shouldBe BASIC_CARD_COUNT
        player2CardCount shouldBe BASIC_CARD_COUNT
        dealerCardCount shouldBe BASIC_CARD_COUNT

        val expected = TOTAL_CARD_COUNT - (player1CardCount + player2CardCount + dealerCardCount)
        blackjackGame.dealer.deck.cardCount shouldBe expected
    }

    companion object {
        const val TOTAL_CARD_COUNT = 52
        const val BASIC_CARD_COUNT = 2
    }
}
