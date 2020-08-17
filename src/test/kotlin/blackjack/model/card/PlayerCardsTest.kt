package blackjack.model.card

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerCardsTest {
    private lateinit var cards: PlayerCards
    private val cardA = Card(Card.Suit.CLUBS, Card.Denomination.ACE)
    private val card10 = Card(Card.Suit.CLUBS, Card.Denomination.TEN)
    private val card5 = Card(Card.Suit.CLUBS, Card.Denomination.FIVE)

    @BeforeEach
    fun beforeTest() {
        cards = PlayerCards()
    }

    @DisplayName(value = "카드 점수 확인 - 블랙잭 with ACE1")
    @Test
    fun `playerCard_BlackJack_Point1`() {
        cards.plus(cardA)
        cards.plus(card10)

        Assertions.assertThat(cards.getPoint()).isEqualTo(21)
    }

    @DisplayName(value = "카드 점수 확인 - 블랙잭 with ACE2")
    @Test
    fun `playerCard_BlackJack_Point2`() {
        cards.plus(cardA)
        cards.plus(card5)
        cards.plus(card5)

        Assertions.assertThat(cards.getPoint()).isEqualTo(21)
    }

    @DisplayName(value = "카드 점수 확인 - 블랙잭 without ACE")
    @Test
    fun playerCardPoint() {
        cards.plus(card10)
        cards.plus(card5)

        Assertions.assertThat(cards.getPoint()).isEqualTo(15)
    }
}
