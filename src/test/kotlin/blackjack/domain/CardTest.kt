package blackjack.domain

import blackjack.NormalCard
import blackjack.PictureCard
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드는 숫자와 무늬를 가진다`() {
        val cardNumber = 1
        val cardPattern = "Diamond"
        val card = NormalCard(cardNumber, cardPattern)

        card.number shouldBe cardNumber
        card.pattern shouldBe cardPattern
    }

    @Test
    fun `King, Queen, Jack이라는 숫자를 가지지 않는 카드가 있다`() {
        val cardPattern = "King"
        val card = PictureCard(cardPattern)

        card.pattern shouldBe cardPattern
    }
}
