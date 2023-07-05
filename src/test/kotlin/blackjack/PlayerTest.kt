package blackjack

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.CardSuit
import blackjack.domain.Player
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
        actual.addCard(cards)

        actual.cards shouldBe cards
    }
}
