package blackjack.domain

import blackjack.domain.Card
import blackjack.domain.CardRank
import blackjack.domain.CardSuit
import blackjack.domain.Deck
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class DeckTest {
    @Test
    internal fun `덱에 카드를 추가할 수 있다`() {
        val sut = Deck()
        sut.size shouldBe 0

        sut.add(Card.of(CardRank.NINE, CardSuit.HEART))
        sut.size shouldBe 1
    }

    @Test
    internal fun `덱에 있는 카드의 점수 합계를 알 수 있다`() {
        val sut = Deck()
        sut.add(Card.of(CardRank.JACK, CardSuit.HEART))
        sut.add(Card.of(CardRank.ACE, CardSuit.SPADE))
        sut.point shouldBe 21
    }
}
