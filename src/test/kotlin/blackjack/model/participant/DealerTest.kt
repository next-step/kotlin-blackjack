package blackjack.model.participant

import blackjack.model.card.Card
import blackjack.model.card.CardScore
import blackjack.model.card.Cards
import blackjack.model.card.Suit
import blackjack.model.state.running.Hit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DealerTest {
    private lateinit var TWO_CLUB: Card
    private lateinit var SIX_CLUB: Card
    private lateinit var TEN_CULB: Card
    private lateinit var dealer: Dealer

    @BeforeEach
    fun setUp() {
        TWO_CLUB = Card(CardScore.TWO, Suit.HEART)
        SIX_CLUB = Card(CardScore.SIX, Suit.CLUB)
        TEN_CULB = Card(CardScore.TEN, Suit.CLUB)

        dealer = Dealer()
    }

    @Test
    fun `기본 카드 2장 뽑기`() {
        // when
        val actual = dealer.takeDefaultCards { Cards(TWO_CLUB, SIX_CLUB) }

        // then
        assertThat(actual.hasTwoCards()).isTrue()
        assertThat(actual.state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `카드 한 장 뽑기`() {
        // when
        val actual = dealer.draw(TWO_CLUB)

        // then
        assertThat(actual.state.cards).isEqualTo(Cards(TWO_CLUB))
    }

    @Test
    fun `딜러 조건 확인 (점수가 17미만일 때 카드를 더 뽑을 수 있다)`() {
        // given
        val dealerWithScore16 = dealer.draw(SIX_CLUB).draw(TEN_CULB)
        val dealerWithScore18 = dealerWithScore16.draw(TWO_CLUB)

        // when, then
        assertThat(dealerWithScore16.canDraw()).isTrue()
        assertThat(dealerWithScore18.canDraw()).isFalse()
    }
}
