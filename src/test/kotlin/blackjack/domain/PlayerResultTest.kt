package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerResultTest {

    @CsvSource("9,LOSE", "J,DRAW", "A,WIN")
    @ParameterizedTest
    fun `딜러와 참가자의 카드 비교로 수익을 반환한다`(cardNumber: String, result: String) {
        val dealer = Dealer(cards(card("A", "HEART"), card("9", "SPADE"))) // 20
        val gamePlayer = GamePlayer(PlayerInfo(Name("eden")), cards(card("K", "HEART"), card(cardNumber, "CLOVER")))
        PlayerResult.ofGamePlayer(dealer, (gamePlayer)).gameResult shouldBe GameResult.valueOf(result)
    }
}
