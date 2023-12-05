package blackjack.domain

import fixtures.createBlackjackDealer
import fixtures.createBlackjackPlayer
import fixtures.createHitPlayer
import fixtures.createBustPlayer
import fixtures.createBustDealer
import fixtures.createCards
import fixtures.createCard
import fixtures.createHitDealer
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `딜러가 Blackjack인 경우 versus 결과 테스트`() {
        // given
        val dealer = createBlackjackDealer()
        val player1 = createBustPlayer("player1")
        val player2 = createBlackjackPlayer("player2")
        val player3 = createHitPlayer("player3")
        // when
        val playerResult1 = player1 versus dealer
        val playerResult2 = player2 versus dealer
        val playerResult3 = player3 versus dealer
        // then
        assertSoftly {
            playerResult1 shouldBe GameResult.LOSE
            playerResult2 shouldBe GameResult.DRAW
            playerResult3 shouldBe GameResult.LOSE
        }
    }

    @Test
    fun `딜러가 Bust인 경우 versus 결과 테스트`() {
        // given
        val dealer = createBustDealer()
        val player1 = createBustPlayer("player1")
        val player2 = createBlackjackPlayer("player2")
        val player3 = createHitPlayer("player3")
        // when
        val playerResult1 = player1 versus dealer
        val playerResult2 = player2 versus dealer
        val playerResult3 = player3 versus dealer
        // then
        assertSoftly {
            playerResult1 shouldBe GameResult.LOSE
            playerResult2 shouldBe GameResult.WIN
            playerResult3 shouldBe GameResult.WIN
        }
    }

    @Test
    fun `딜러와 플레이어 모두 Bust나 Blackjack이 아닌 경우 Blackjack과 최대한 가까운 사람이 승리한다`() {
        // given
        val dealerCards = createCards(
            createCard(Suit.CLUBS, Denomination.ACE),
            createCard(Suit.HEARTS, Denomination.TWO)
        )
        val playerCards = createCards(
            createCard(Suit.DIAMONDS, Denomination.ACE),
            createCard(Suit.HEARTS, Denomination.QUEEN)
        )
        val dealer = createHitDealer(dealerCards)
        val player = createHitPlayer("player", playerCards)
        // when
        val playerResult = player versus dealer
        // then
        playerResult shouldBe GameResult.WIN
    }
}
