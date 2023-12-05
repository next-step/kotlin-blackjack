package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import fixtures.createBlackjackCards
import fixtures.createUnderBlackjackCards
import fixtures.createBustCards
import fixtures.createCard
import fixtures.createCards
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayersTest {
    private lateinit var runningPlayers: Players
    private lateinit var finishedPlayers: Players

    @BeforeEach
    fun setUp() {
        runningPlayers = Players(
            listOf(
                Player(name = "player1", state = Hit(createUnderBlackjackCards())),
                Player(name = "player2", state = Hit(createUnderBlackjackCards()))
            )
        )
        finishedPlayers = Players(
            listOf(
                Player(name = "player3", state = Bust(createBustCards())),
                Player(name = "player4", state = Blackjack(createBlackjackCards())),
            )
        )
    }

    @Test
    fun `filterReceivable 테스트`() {
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
    fun `getNames 테스트`() {
        // when
        val names = (runningPlayers + finishedPlayers).getNames()
        names shouldBe "player1, player2, player3, player4"
    }

    @Test
    fun `receiveInitialCards 테스트`() {
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
