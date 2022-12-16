package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

fun cards(vararg values: Card): Cards = Cards(values.toList())
class CardsTest {
    @Test
    fun `카드의 점수를 카운팅한다`() {
        val cards = cards(card("K", "HEART"), card("4", "DIAMOND"), card("7", "SPADE"))
        cards.countingCard() shouldBe 21
    }

    @ParameterizedTest
    @CsvSource("8,DIAMOND,21", "9,HEART,12", "Q,SPADE,13")
    fun `Ace 카드는 카드의 점수가 21이 넘으면 1로 21이 넘지 않으면 11로 계산된다`(cardNumber: String, cardShape: String, score: Int) {
        val cards = cards(card("A", "CLOVER"), card("2", "SPADE"), card(cardNumber, cardShape))
        cards.countingCard() shouldBe score
    }
}
