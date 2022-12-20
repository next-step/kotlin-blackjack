package blackjack.domain

import io.kotest.matchers.collections.shouldContain
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class PlayerResultTest {

    @CsvSource("9,WIN", "J,DRAW", "A,LOSE")
    @ParameterizedTest
    fun `딜러와 참가자의 카드 비교로 승패를 반환한다`(cardNumber: String, result: String) {
        val dealer = Dealer(cards(card("A", "HEART"), card("9", "SPADE"))) // 20
        val gamePlayer = GamePlayer(PlayerInfo(Name("eden")), cards(card("K", "HEART"), card(cardNumber, "CLOVER")))
        PlayerResult.ofDealer(dealer, Players(listOf(gamePlayer))).gameResult shouldContain GameResult.valueOf(result)
    }
}
