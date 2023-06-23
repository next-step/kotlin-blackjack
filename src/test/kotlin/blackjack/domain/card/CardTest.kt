package blackjack.domain.card

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class CardTest {
    @Test
    fun `카드 한 장에는 숫자와 기호가 있다`() {
        val rank = CardRank.EIGHT
        val suit = CardShape.HEART
        val card = Card.createCard(rank, suit)

        card.rank shouldBe CardRank.EIGHT
        card.shape shouldBe CardShape.HEART
    }
}
