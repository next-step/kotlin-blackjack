package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GameResultTest {

    @CsvSource("8,WIN,LOSE,-1000", "9,DRAW,DRAW,0", "K,LOSE,WIN,1000", "A,LOSE,WIN,1500")
    @ParameterizedTest(name = "딜러={1}, 참가자={2}, 참가자수익={3}")
    fun `딜러와 참가자로 게임 결과를 반환한다`(playerCard: String, dealerResult: String, playerResult: String, profit: Int) {
        val dealer = Dealer(cards(card("10", "CLOVER"), card("9", "CLOVER"))) // 19
        val playerCards = cards(card("10", "HEART"), card(playerCard, "HEART"))
        val player = GamePlayer(PlayerInfo(Name("이든"), BettingAmount(1000)), playerCards)
        GameResult.getGameResultOfDealer(dealer, player) shouldBe GameResult.valueOf(dealerResult)
        val gameResult = GameResult.getGamePlayerResult(dealer, player)
        gameResult shouldBe GameResult.valueOf(playerResult)
        gameResult.profit(player).getAmount() shouldBe profit
    }

    @Test
    fun `딜러와 참가자 둘다 블랙잭이면 수익은 0이다`() {
        val dealer = Dealer(cards(card("10", "CLOVER"), card("A", "CLOVER")))
        val playerCards = cards(card("10", "HEART"), card("A", "HEART"))
        val player = GamePlayer(PlayerInfo(Name("이든"), BettingAmount(1000)), playerCards)
        GameResult.getGameResultOfDealer(dealer, player) shouldBe GameResult.DRAW
        val gameResult = GameResult.getGamePlayerResult(dealer, player)
        gameResult shouldBe GameResult.DRAW
        gameResult.profit(player).getAmount() shouldBe 0
    }
}
