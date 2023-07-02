package blackjack.domain

import blackjack.enums.Rank
import blackjack.enums.Symbol
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CardsTest {

    private lateinit var cards: Cards
    private lateinit var aceCardValueElevenCards: Cards
    private lateinit var aceCardValueOneCards: Cards

    @BeforeEach
    fun setup() {
        cards = Cards(
            listOf(
                Card(rank = Rank.JACK, symbol = Symbol.SPADES),
                Card(rank = Rank.FIVE, symbol = Symbol.HEARTS)
            )
        )

        aceCardValueElevenCards = Cards(
            listOf(
                Card(rank = Rank.ACE, symbol = Symbol.SPADES),
                Card(rank = Rank.FIVE, symbol = Symbol.HEARTS),
                Card(rank = Rank.FIVE, symbol = Symbol.CLUBS),
            )
        )

        aceCardValueOneCards = Cards(
            listOf(
                Card(rank = Rank.ACE, symbol = Symbol.SPADES),
                Card(rank = Rank.FIVE, symbol = Symbol.HEARTS),
                Card(rank = Rank.KING, symbol = Symbol.HEARTS)
            )
        )
    }

    @Test
    fun `카드 뭉치에 대한 숫자 합계를 구한다`() {
        val actual = cards.calculateScore().value
        actual shouldBe 15
    }

    @Test
    fun `에이스를 제외한 모든 숫자의 합이 10 미만 일 때 에이스는 11로 계산된다`() {
        val actual = aceCardValueElevenCards.calculateScore().value
        actual shouldBe 21
    }

    @Test
    fun `에이스를 제외한 모든 숫자의 합이 10 이상 일 때 에이스는 1로 계산된다`() {
        val actual = aceCardValueOneCards.calculateScore().value
        actual shouldBe 16
    }
}
