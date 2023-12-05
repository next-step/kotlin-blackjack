package blackjack.domain

import blackjack.domain.state.Blackjack
import blackjack.domain.state.Bust
import blackjack.domain.state.Hit
import fixtures.createBlackjackCards
import fixtures.createBustCards
import fixtures.createUnderBlackjackCards
import fixtures.createCard
import fixtures.createCards
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    fun `딜러가 Blackjack인 경우 versus 결과 테스트`() {
        // given
        val dealer = Dealer(state = Blackjack(createBlackjackCards()))
        val player1 = Player(name = "player1", state = Bust(createBustCards()))
        val player2 = Player(name = "player2", state = Blackjack(createBlackjackCards()))
        val player3 = Player(name = "player3", state = Hit(createUnderBlackjackCards()))
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
        val dealer = Dealer(state = Bust(createBustCards()))
        val player1 = Player(name = "player1", state = Bust(createBustCards()))
        val player2 = Player(name = "player2", state = Blackjack(createBlackjackCards()))
        val player3 = Player(name = "player2", state = Hit(createUnderBlackjackCards()))
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
        val dealer = Dealer(state = Hit(dealerCards))
        val player = Player(name = "player", state = Hit(playerCards))
        // when
        val playerResult = player versus dealer
        // then
        playerResult shouldBe GameResult.WIN
    }
}
