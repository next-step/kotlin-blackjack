package blackjack.domain.card

import blackjack.domain.createCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

internal class CardDeckTest {
    private lateinit var cards: CardDeck

    @BeforeEach
    fun setUp() {
        cards = CardDeck(object : ShuffleStrategy {
            override fun shuffle(cards: List<Card>): List<Card> {
                return cards.sortedWith(compareBy({ it.symbol }, { it.suit }))
            }
        })
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
