package blackjack.domain

import fixtures.createHitPlayer
import fixtures.createPlayers
import fixtures.createStartedDealer
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantsTest {
    private lateinit var participants: Participants

    @BeforeEach
    fun setUp() {
        val players = createPlayers(
            createHitPlayer("player1"),
            createHitPlayer("player2")
        )
        participants = Participants(
            dealer = createStartedDealer(),
            players = players
        )
    }

    @Test
    fun `게임 중 플레이어는 게임을 계속한다고 선언하면 한 장의 카드를 더 받게 된다`() {
        // given
        val initialCardSize = participants.players[0].state.cards.size
        // when
        val playerWithHit = participants.playGameByPlayer(
            willHit = { true },
            card = { Card(Suit.CLUBS, Denomination.TWO) }
        )
        // then
        playerWithHit!!.state.cards.size shouldBe initialCardSize + 1
    }

    @Test
    fun `게임 중 플레이어가 게임을 중단한다고 선언하면 Stand로 State가 바뀐다`() {
        // given
        // when
        val playerWithNoHit = participants.playGameByPlayer(
            willHit = { false },
            card = { Card(Suit.CLUBS, Denomination.TWO) }
        )
        // then
        playerWithNoHit!!.isStand() shouldBe true
    }

    @Test
    fun `게임 참여자들로부터 gameResult를 얻을 수 있다`() {
        // given
        // when
        val gameResults = participants.getGameResult()
        // then
        gameResults.playerGameResults shouldBe listOf(
            PlayerGameResult("player1", GameResult.WIN),
            PlayerGameResult("player2", GameResult.WIN)
        )
        gameResults.dealerGameResult shouldBe mapOf(GameResult.LOSE to 2)
    }
}
