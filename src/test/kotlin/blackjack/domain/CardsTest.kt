package blackjack.domain

import io.kotest.matchers.collections.shouldNotContain
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardsTest {

    @DisplayName("카드 목록에서 뽑은 두 장의 카드는 서로 다르다")
    @Test
    fun card() {
        val cards = Cards()
        val card = cards.getCard()
        val card2 = cards.getCard()

        card shouldNotBe card2
    }

    @DisplayName("카드를 뽑고 난 후 남은 카드 목록에는 해당 카드가 없다")
    @Test
    fun cardList() {
        val cards = Cards()
        val card = cards.getCard()
        val cardList = cards.getCardList()

        cardList shouldNotContain card
    }
}
