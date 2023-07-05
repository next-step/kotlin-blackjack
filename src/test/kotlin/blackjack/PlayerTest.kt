package blackjack

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.CardSuit
import blackjack.domain.Player
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class PlayerTest {

    @DisplayName("플레이어는 여러 장의 카드를 가지고 있다.")
    @Test
    fun playerHasCards() {
        val cards = listOf(
            Card.of(CardSuit.DIAMOND, CardRank.ACE),
            Card.of(CardSuit.HEART, CardRank.ACE)
        )
        val actual = Player()
        actual.addCards(cards)

        actual.cards shouldBe cards
    }

    @DisplayName("플레이어는 한장의 카드를 추가할 수 있다.")
    @Test
    fun addCard() {
        val card = Card.of(CardSuit.DIAMOND, CardRank.ACE)
        val actual = Player()
        actual.addCard(card)

        actual.cards.size shouldBe 1
        actual.cards.shouldContain(card)
    }
}
