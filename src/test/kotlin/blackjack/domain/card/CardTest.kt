package blackjack.domain.card

import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class CardTest {

    @DisplayName("카드는 숫자와 문양을 가진다.")
    @Test
    fun suitAndRank() {
        val rank = CardRank.ACE
        val suit = CardSuit.DIAMOND
        val actual = Card.of(suit, rank)

        actual.rank shouldBe rank
        actual.suit shouldBe suit
    }

    @DisplayName("King, Queen, Jack은 각 10으로 계산된다.")
    @Test
    fun jqkValueEqual10() {
        val actual = listOf(CardRank.JACK, CardRank.QUEEN, CardRank.KING)

        actual.forAll { cardRank -> cardRank.value shouldBe 10 }
    }

    @DisplayName("suit, rank가 같으면 동일한 Card 인스턴스가 반환된다.")
    @Test
    fun cardInstance() {
        val suit = CardSuit.values().random()
        val rank = CardRank.values().random()

        val card1 = Card.of(suit, rank)
        val card2 = Card.of(suit, rank)

        val actual = card1 === card2
        actual shouldBe true
    }

    @DisplayName("print메서드는 CardRank.forOutput + CardSuit.forOutput을 반환한다.")
    @Test
    fun print() {
        val suit = CardSuit.values().random()
        val rank = CardRank.values().random()
        val card = Card.of(suit, rank)

        val actual = card.print()
        actual shouldBe "${rank.forOutput}${suit.forOutput}"
    }
}
