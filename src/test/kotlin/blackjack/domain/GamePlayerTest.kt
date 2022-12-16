package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class GamePlayerTest {
    @Test
    fun `참가자는 카드를 받을 수 있다`() {
        val card = card("5", "CLOVER")
        val deck = Deck(listOf(card))
        val player = GamePlayer("pobi").hit(deck)
        player.cards.count() shouldBe 1
        player.cards.contains(card) shouldBe true
    }

    @CsvSource("9,LOSE", "J,DRAW", "A,WIN")
    @ParameterizedTest
    fun `딜러와 카드 비교로 승패를 반환한다`(cardNumber: String, result: String) {
        val dealer = Dealer(cards(card("A", "HEART"), card("9", "SPADE"))) // 20
        val gamePlayer = GamePlayer(Name("eden"), cards(card("K", "HEART"), card(cardNumber, "CLOVER")))
        gamePlayer.compareTo(dealer) shouldBe GameResult.valueOf(result)
    }
}
