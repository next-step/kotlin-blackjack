package blackJack.domain

import blackJack.domain.Rank.ACE
import blackJack.domain.Rank.KING
import blackJack.domain.Rank.QUEEN
import blackJack.domain.Rank.THREE
import blackJack.domain.Rank.TWO
import blackJack.domain.Suit.CLUB
import blackJack.domain.Suit.DIAMOND
import blackJack.domain.Suit.HEART
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
    fun `3카드와 2카드를 합하면 5 가 출력되어야 한다`() {
        val cards = Cards(mutableListOf(Card(SPADE, THREE), Card(SPADE, TWO)))
        assertEquals(5, cards.calculateTotalScore())
    }

    @Test
    fun `카드 스코어의 합을 출력할 때 ACE 가 1의 값을 가져야 하는 경우`() {
        val aceOneCards = Cards(mutableListOf(Card(SPADE, ACE), Card(SPADE, QUEEN), Card(SPADE, KING)))
        assertEquals(21, aceOneCards.calculateTotalScore())
    }

    @Test
    fun `카드 스코어의 합을 출력할 때 ACE 가 11의 값을 가져야 하는 경우`() {
        val aceElevenCards = Cards(mutableListOf(Card(SPADE, ACE), Card(SPADE, TWO)))
        assertEquals(13, aceElevenCards.calculateTotalScore())
    }

    @Test
    fun `카드 스코어의 합을 출력할 때 여러 개의 ACE 가 있는 경우 하나의 ACE 만 11의 값을 가져야 한다`() {
        val multipleAcesCards = Cards(mutableListOf(Card(SPADE, ACE), Card(HEART, ACE), Card(DIAMOND, ACE), Card(CLUB, ACE)))
        assertEquals(14, multipleAcesCards.calculateTotalScore())
    }

    companion object {
        private const val TOTAL_CARDS_COUNT = 52
        private const val OVERFLOW_CARDS_COUNT = 53
    }
}
