package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerResultTest {

    @CsvSource("8,패배,-1000", "9,무승부,0", "Q,승리,1000", "A,승리(블랙잭),1500")
    @ParameterizedTest(name = "게임 결과, {1}로 수익금은 {2}이다")
    fun `참가자의 승패로 배팅 금액만큼 수익을 반환한다`(cardNumber: String, result: String, profit: Int) {
        val dealer = Dealer(cards(card("A", "HEART"), card("8", "SPADE"))) // 19
        val gamePlayer = GamePlayer(
            PlayerInfo(Name("eden"), BettingAmount(1000)),
            cards(card("K", "HEART"), card(cardNumber, "CLOVER"))
        )
        PlayerResult.ofGamePlayer(dealer, (gamePlayer)).getProfit() shouldBe profit
    }
}
