package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var deck: Deck
    private lateinit var card: Card
    private lateinit var cards: Cards
    private lateinit var dealer: Dealer

    @BeforeEach
    fun `set up`() {
        deck = Deck(setOf(Card(Pair(CardScore.SEVEN, Suit.SPADE))))
        card = Card(Pair(CardScore.ACE, Suit.HEART))
        cards = Cards(setOf(card))
        dealer = Dealer(deck, cards)
    }

    @Test
    fun `카드 한 장을 반환하되, 덱이 비어있으면 null을 반환한다`() {
        // when
        val card = dealer.pickCard()
        val nullCard = dealer.pickCard()

        // then
        assertTrue(card is Card)
        assertTrue(nullCard == null)
    }

    @Test
    fun `카드 점수 합계가 17 미만인지 확인하는 기능이 있다`() {
        // when
        val hasLessThan17 = dealer.hasLessScoreThan17(dealer.totalScore())

        // then
        assertTrue(hasLessThan17)
    }

    @Test
    fun `카드 한장을 공개하는 기능이 있다`() {
        // when
        val firstCard = dealer.faceUpCard()

        // then
        assertThat(firstCard).isEqualTo(card)
    }

    @Test
    fun `승리와 패배를 기록하는 기능이 있다 (3명의 참가자 중 1명만 승리)`() {
        // when
        val matchResult = dealer.checkWin(3, 1)
        val winCount = matchResult[0]
        val loseCount = matchResult[1]

        // then
        assertThat(winCount).isEqualTo(2)
        assertThat(loseCount).isEqualTo(1)
    }
}
