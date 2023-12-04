package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `딜러가 21을 초과하면 남아있는 플레이어는 다 이긴다`() {
        val dealer = Dealer()
        val player1 = Player("")
        dealer.getFirstDealCards(
            listOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.JACK, Suit.CLUBS),
                Card(Denomination.TWO, Suit.CLUBS)
            )
        )

        val gameResult = GameResult(dealer, listOf(player1))
        val resultMap = gameResult.getResultMap()

        resultMap.first().get(player1) shouldBe MatchResult.WIN
    }

    @Test
    fun `딜러와 player의 점수를 각자 비교해서 21에 더 가까운 사람이 승, 나머지 사람은 패이다`() {
        val dealer = Dealer()
        val player1 = Player("player1")
        dealer.getFirstDealCards(
            mutableListOf(
                Card(Denomination.SEVEN, Suit.CLUBS),
                Card(Denomination.JACK, Suit.CLUBS),
            )
        )
        player1.getFirstDealCards(
            mutableListOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.KING, Suit.CLUBS),
            )
        )

        val gameResult = GameResult(dealer, listOf(player1))
        val resultMap = gameResult.getResultMap()

        resultMap.first()[player1] shouldBe MatchResult.WIN
    }

    @Test
    fun `플레이어가 버스트 상태가 되면 가진 돈을 전부 잃는다`() {
        val dealer = Dealer()
        val player1 = Player("player1", 10000)
        dealer.getFirstDealCards(
            mutableListOf(
                Card(Denomination.SEVEN, Suit.CLUBS),
                Card(Denomination.JACK, Suit.CLUBS),
            )
        )
        player1.getFirstDealCards(
            mutableListOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.KING, Suit.CLUBS)
            )
        )

        val gameResult = GameResult(dealer, listOf(player1))
        gameResult.setProfit()

        player1.profit shouldBe -10000
    }

    @Test
    fun `딜러가 버스트 상태가 되면 플레이어의 점수와 상관없이 베팅 금액을 돌려받는다`() {
        val dealer = Dealer()
        val player1 = Player("player1", 10000)
        dealer.getFirstDealCards(
            mutableListOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.KING, Suit.CLUBS)
            )
        )
        player1.getFirstDealCards(
            mutableListOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.KING, Suit.CLUBS)
            )
        )

        val gameResult = GameResult(dealer, listOf(player1))
        gameResult.setProfit()

        player1.profit shouldBe 10000
    }

    @Test
    fun `처음받은 카드 2장의 합이 21이면 베팅 금액의 1,5배를 돌려받는다`() {
        val dealer = Dealer()
        val player1 = Player("player1", 10000)
        dealer.getFirstDealCards(
            mutableListOf(
                Card(Denomination.TWO, Suit.CLUBS),
                Card(Denomination.THREE, Suit.CLUBS)
            )
        )
        player1.getFirstDealCards(
            mutableListOf(
                Card(Denomination.KING, Suit.CLUBS),
                Card(Denomination.ACE, Suit.CLUBS)
            )
        )

        val gameResult = GameResult(dealer, listOf(player1))
        val players = gameResult.setProfit()

        players.get(0).profit.toInt() shouldBe 15000
    }
}
