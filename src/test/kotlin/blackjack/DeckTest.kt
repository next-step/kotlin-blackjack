package blackjack

import blackjack.model.Deck
import blackjack.model.Rank
import blackjack.model.Suit
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DeckTest {

    private lateinit var deck: Deck

    @BeforeEach
    fun setUp() {
        deck = Deck()
    }

    @DisplayName("Deck은 전체 카드를 생성해야 한다.")
    @Test
    fun `Deck은 전체 카드를 생성해야 한다`() {
        deck.cardsRemaining() shouldBe (Suit.list.size * Rank.list.size)
    }

    @DisplayName("Deck에서 카드를 뽑으면 남은 카드 수가 줄어들어야 한다.")
    @Test
    fun `Deck에서 카드를 뽑으면 남은 카드 수가 줄어들어야 한다`() {
        val initialCount = deck.cardsRemaining()
        val card = deck.takeRandomCard()
        deck.cardsRemaining() shouldBe (initialCount - 1)
    }

    @DisplayName("모든 카드를 소진한 후 카드 뽑기 시 예외가 발생해야 한다.")
    @Test
    fun `모든 카드를 소진한 후 카드 뽑기 시 예외가 발생해야 한다`() {
        repeat(Suit.list.size * Rank.list.size) {
            deck.takeRandomCard()
        }

        shouldThrow<IllegalArgumentException> {
            deck.takeRandomCard()
        }.message shouldBe "남는 카드가 없습니다."
    }

    @DisplayName("Deck을 리셋하면 모든 카드가 다시 생성되어야 한다.")
    @Test
    fun `Deck을 리셋하면 모든 카드가 다시 생성되어야 한다`() {
        deck.takeRandomCard()
        deck.takeRandomCard()
        deck.cardsRemaining() shouldBe ((Suit.list.size * Rank.list.size) - 2)

        deck.resetAllCards()
        deck.cardsRemaining() shouldBe (Suit.list.size * Rank.list.size)
    }
}