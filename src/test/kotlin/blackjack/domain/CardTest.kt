package blackjack.domain

import blackjack.card.AceCard
import blackjack.card.CardPattern
import blackjack.card.CardPicture
import blackjack.card.NormalCard
import blackjack.card.PictureCard
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {

    @Test
    fun `카드는 숫자와 무늬를 가진다`() {
        val cardNumber = 1
        val cardPattern = CardPattern.DIAMOND
        val card = NormalCard(cardNumber, cardPattern)

        card.number shouldBe cardNumber
        card.pattern shouldBe cardPattern
    }

    @Test
    fun `King, Queen, Jack이라는 숫자를 가지지 않는 카드가 있다`() {
        val picture = CardPicture.KING
        val card = PictureCard(picture, CardPattern.DIAMOND)

        card.picture shouldBe picture
    }

    @Test
    fun `무늬를 가지는 Ace 카드가 있다`() {
        val cardPattern = CardPattern.CLOVER
        val card = AceCard(cardPattern)

        card.pattern shouldBe cardPattern
    }
}
