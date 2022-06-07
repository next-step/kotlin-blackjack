package blackjack.controller

import blackjack.dummy.BiasedCardDistributor
import blackjack.dummy.BiasedHitMakerDecisionMaker
import blackjack.dummy.TestPlayerProvider
import blackjack.model.player.Player
import blackjack.model.player.Players.Companion.toPlayers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @Test
    fun `BlackJack 게임 로직 통합 테스트`() {

        // given
        val playerNames = listOf("AA", "BB", "CC")
        val expectedWinnerName = playerNames[1] // 테스트용 승리자 이름

        // (1) 편파적인 게임 환경 구성
        val cardDistributor = BiasedCardDistributor(expectedWinnerName)
        val hitDecisionMaker = BiasedHitMakerDecisionMaker(expectedWinnerName)

        // (2) 플레이어 구성
        val players = playerNames.map { Player(it, hitDecisionMaker) }.toPlayers()
        val expectedWinner = players.find { it.name == expectedWinnerName }
        val playerProvider = TestPlayerProvider(players)

        val game = BlackJackGame(
            playerProvider = playerProvider,
            cardDistributor = cardDistributor
        )

        // when (Play Game)
        game.run()

        // then
        assertThat(players.blackJackPlayer).isEqualTo(expectedWinner)
    }
}
