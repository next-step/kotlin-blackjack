package blackjack.domain

import fixtures.createBlackjackPlayer
import fixtures.createHitPlayer
import fixtures.createBustPlayer
import fixtures.createCards
import fixtures.createCard
import fixtures.createPlayers
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayersTest {
    private lateinit var runningPlayers: Players
    private lateinit var finishedPlayers: Players

    @BeforeEach
    fun setUp() {
        runningPlayers = createPlayers(
            createHitPlayer("player1"),
            createHitPlayer("player2")
        )
        finishedPlayers = createPlayers(
            createBustPlayer("player3"),
            createBlackjackPlayer("player4")
        )
    }

    @Test
    fun `running 상태의 player들은 filterReceivable 메서드 호출 후 반환된다`() {
        // when
        val receivablePlayer = runningPlayers.filterReceivable()
        val notReceivablePlayer = finishedPlayers.filterReceivable()
        // then
        assertSoftly {
            receivablePlayer.size shouldBe 2
            notReceivablePlayer.size shouldBe 0
        }
    }

    @Test
    fun `플레이어들의 이름을 한번에 가져올 수 있다`() {
        // when
        val names = (runningPlayers + finishedPlayers).getNames()
        names shouldBe "player1, player2, player3, player4"
    }

    @Test
    fun `플레이어들은 게임이 시작하면 카드를 일정 개수만큼 수령한다`() {
        // when
        val testPlayers = Players(listOf(Player(name = "player1"), Player(name = "player2")))
        testPlayers.receiveInitialCards {
            createCards(
                createCard(suit = Suit.CLUBS, denomination = Denomination.TWO),
                createCard(suit = Suit.DIAMONDS, denomination = Denomination.TWO),
            )
        }
        // then
        testPlayers.forEach { it.state.cards.size shouldBe 2 }
    }
}
