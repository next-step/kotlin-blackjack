package blackjack.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class ScoreTest {
    @BeforeEach
    fun setUp(){
        Score.dealerScore.put("win",0)
        Score.dealerScore.put("lose", 0)
        Score.playerScore.clear()
    }

    @Test
    fun `19는 21과 2만큼 차이난다`() {
        // given
        val expectedDistance = 2

        // when
        val result = Score.getDistance(19)

        // then
        Assertions.assertThat(result).isEqualTo(expectedDistance)
    }

    @Test
    fun `플레이어가 이기면 playerScore에 player이름, "승"이라는 pair가 추가된다`() {
        // given
        val dealerCards = listOf(Card(Shape.DIAMOND, CardNumber.TWO), Card(Shape.CLOVER, CardNumber.SIX))
        val dealerWallet = Wallet(dealerCards)
        val dealer = Dealer(dealerWallet)

        val playerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX))
        val playerWallet = Wallet(playerCards)
        val player = Player("제이", playerWallet)

        val expected = Pair("제이", "승")

        // when
        Score.compare(player, dealer)

        // then
        Assertions.assertThat(Score.playerScore[0]).isEqualTo(expected)
    }

    @Test
    fun `플레이어가 지면 playerScore에 player이름, "패"라는 pair가 추가된다`() {
        // given
        val dealerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX))
        val dealerWallet = Wallet(dealerCards)
        val dealer = Dealer(dealerWallet)

        val playerCards = listOf(Card(Shape.DIAMOND, CardNumber.TWO), Card(Shape.CLOVER, CardNumber.SIX))
        val playerWallet = Wallet(playerCards)
        val player = Player("제이", playerWallet)

        val expected = Pair("제이", "패")

        // when
        Score.compare(player, dealer)

        // then
        Assertions.assertThat(Score.playerScore[0]).isEqualTo(expected)
    }

    @Test
    fun `딜러가 이기면 dealerScore의 win 값이 1 증가한다`() {
        // given
        val dealerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX))
        val dealerWallet = Wallet(dealerCards)
        val dealer = Dealer(dealerWallet)

        val playerCards = listOf(Card(Shape.DIAMOND, CardNumber.TWO), Card(Shape.CLOVER, CardNumber.SIX))
        val playerWallet = Wallet(playerCards)
        val player = Player("제이", playerWallet)

        val expected = Score.dealerScore["win"]?.plus(1)

        // when
        Score.compare(player, dealer)

        // then
        Assertions.assertThat(Score.dealerScore["win"]).isEqualTo(expected)
    }

    @Test
    fun `딜러가 지면 dealerScore의 lose 값이 1 증가한다`() {
        // given
        val dealerCards = listOf(Card(Shape.DIAMOND, CardNumber.TWO), Card(Shape.CLOVER, CardNumber.SIX))
        val dealerWallet = Wallet(dealerCards)
        val dealer = Dealer(dealerWallet)

        val playerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX))
        val playerWallet = Wallet(playerCards)
        val player = Player("제이", playerWallet)

        val expected = Score.dealerScore["lose"]?.plus(1)

        // when
        Score.compare(player, dealer)

        // then
        Assertions.assertThat(Score.dealerScore["lose"]).isEqualTo(expected)
    }

    @Test
    fun `딜러가 21을 넘어가면 플레이어가 21을 넘어가도 플레이어가 이긴다`() {
        // given
        val dealerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX), Card(Shape.CLOVER, CardNumber.KING))
        val dealerWallet = Wallet(dealerCards)
        val dealer = Dealer(dealerWallet)

        val playerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX), Card(Shape.CLOVER, CardNumber.KING))
        val playerWallet = Wallet(playerCards)
        val player = Player("제이", playerWallet)

        val expected = Pair("제이", "승")

        // when
        Score.compare(player, dealer)

        // then
        Assertions.assertThat(Score.playerScore[0]).isEqualTo(expected)
    }

    @Test
    fun `딜러가 21을 넘지 않고 플레이어가 21을 넘기면 딜러가 이긴다`() {
        // given
        val dealerCards = listOf(Card(Shape.DIAMOND, CardNumber.TWO), Card(Shape.CLOVER, CardNumber.SIX))
        val dealerWallet = Wallet(dealerCards)
        val dealer = Dealer(dealerWallet)

        val playerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX), Card(Shape.CLOVER, CardNumber.KING))
        val playerWallet = Wallet(playerCards)
        val player = Player("제이", playerWallet)

        val expected = Pair("제이", "패")

        // when
        Score.compare(player, dealer)

        // then
        Assertions.assertThat(Score.playerScore[0]).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 숫자가 딜러의 숫자보다 21에 가까우면 플레이어가 이긴다`() {
        // given
        val dealerCards = listOf(Card(Shape.DIAMOND, CardNumber.TWO), Card(Shape.CLOVER, CardNumber.SIX))
        val dealerWallet = Wallet(dealerCards)
        val dealer = Dealer(dealerWallet)

        val playerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX))
        val playerWallet = Wallet(playerCards)
        val player = Player("제이", playerWallet)

        val expected = Pair("제이", "승")

        // when
        Score.compare(player, dealer)

        // then
        Assertions.assertThat(Score.playerScore[0]).isEqualTo(expected)
    }

    @Test
    fun `플레이어의 숫자가 딜러의 숫자보다 21에 멀면 딜러가 이긴다`() {
        // given
        val dealerCards = listOf(Card(Shape.DIAMOND, CardNumber.JACK), Card(Shape.CLOVER, CardNumber.SIX))
        val dealerWallet = Wallet(dealerCards)
        val dealer = Dealer(dealerWallet)

        val playerCards = listOf(Card(Shape.DIAMOND, CardNumber.TWO), Card(Shape.CLOVER, CardNumber.SIX))
        val playerWallet = Wallet(playerCards)
        val player = Player("제이", playerWallet)

        val expected = Pair("제이", "패")

        // when
        Score.compare(player, dealer)

        // then
        Assertions.assertThat(Score.playerScore[0]).isEqualTo(expected)
    }
}