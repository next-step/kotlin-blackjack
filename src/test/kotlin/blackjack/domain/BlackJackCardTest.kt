package blackjack.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BlackJackCardTest {
//    @Test
//    fun `카드를 생성한다`() {
//        val card = BlackJackCard(5, BlackJackCard.CardType.HEART, BlackJackCard.SpacialCard.JACK)
//        println(card)
//    }

    @Test
    fun `카드를 숫자(인덱스)로 생성한다`() {
        val card = BlackJackCard(5)
        println(card)
    }

    @ParameterizedTest
    @CsvSource(value = ["1:1", "2:2", "52:10", "44:5"], delimiter = ':')
    fun `카드의 점수를 확인한다`(
        number: Int,
        points: Int,
    ) {
        val card = BlackJackCard(number)
        println(card)
        card.getPoint() shouldBe points
    }
}
