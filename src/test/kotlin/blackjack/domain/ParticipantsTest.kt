package blackjack.domain

import blackjack.domain.state.Hit
import fixtures.createUnderBlackjackCards
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class ParticipantsTest {
    lateinit var participants: Participants

    @BeforeEach
    fun setUp() {
        val players = Players(
            listOf(
                Player(name = "player1", state = Hit(createUnderBlackjackCards())),
                Player(name = "player2", state = Hit(createUnderBlackjackCards()))
            )
        )
        participants = Participants(
            dealer = Dealer(),
            players = players
        )
    }

    @Test
    fun receiveInitialCards() {
        // when
        participants.receiveInitialCards {
            Deck().draw(2)
        }
        // then
        val dealer = participants.dealer
        val players = participants.players
        dealer.state.cards.size shouldBe 2
        players.forEach { it.state.cards.size shouldBe 2 }
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
    fun `getGameResult 테스트`() {
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
