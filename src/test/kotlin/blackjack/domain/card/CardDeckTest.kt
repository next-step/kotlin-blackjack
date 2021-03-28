package blackjack.domain.card

import blackjack.domain.SORTED_SHUFFLE
import blackjack.domain.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertDoesNotThrow

internal class CardDeckTest {
    private lateinit var cards: CardDeck

    @BeforeEach
    fun setUp() {
        cards = CardDeck(SORTED_SHUFFLE)
    }

    @DisplayName("카드덱 생성")
    @Test
    fun initCard() {
        assertDoesNotThrow { cards }.also { assertThat(cards.cards.size).isEqualTo(52) }
    }

    @DisplayName("가장 앞에 있는 카드를 반환")
    @Test
    fun pop() {
        val card = cards.pop()

        assertAll(
            { assertThat(card).isEqualTo(createCard(CardSymbol.KING.name, CardSuit.SPADE.name)) },
            { assertThat(cards.cards.size).isEqualTo(51) },
        )
    }

    @DisplayName("카드를 모두 소진한 경우 카드를 재 할당 후 가장 앞에 있는 카드를 반환")
    @Test
    fun pop2() {
        repeat(52) { cards.pop() }
        val card = cards.pop()

        assertAll(
            { assertThat(card).isEqualTo(createCard(CardSymbol.KING.name, CardSuit.SPADE.name)) },
            { assertThat(cards.cards.size).isEqualTo(51) },
        )
    }
}
