package blackJack.domain

import blackJack.domain.Rank.ACE
import blackJack.domain.Rank.TWO
import blackJack.domain.Suit.SPADE
import blackJack.error.ErrorMessage
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CardsTest {

    private lateinit var cardDeck: Cards
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        cardDeck = CardDeck.createShuffledDeck()
        dealer = Dealer(cardDeck)
    }

    @Test
    fun `cards 는 비어 있을 수 없다`() {
        val exception = assertThrows<IllegalArgumentException> {
            Cards(mutableListOf())
        }
        assertEquals(ErrorMessage.EMPTY_CARDS.message, exception.message)
    }

    @Test
    fun `52장의 카드가 모두 다른 카드가 뽑힌다`() {
        val drawnCards = mutableSetOf<Card>()

        repeat(TOTAL_CARDS_COUNT) {
            val card = cardDeck.drawCard()
            drawnCards.add(card)
        }
        assertEquals(52, drawnCards.size)
    }

    @Test
    fun `모든 카드를 뽑으면 IllegalArgumentException 이 발생 한다`() {
        val exception = assertThrows<IllegalArgumentException> {
            repeat(OVERFLOW_CARDS_COUNT) {
                cardDeck.drawCard()
            }
        }
        assertEquals(ErrorMessage.EMPTY_CARDS.message, exception.message)
    }

    @Test
    fun `카드 스코어의 합을 출력한다`() {
        val cards = Cards(mutableListOf(Card(SPADE, ACE), Card(SPADE, TWO)))
        assertEquals(3, cards.calculateTotalScore())
    }

    companion object {
        private const val TOTAL_CARDS_COUNT = 52
        private const val OVERFLOW_CARDS_COUNT = 53
    }
}
