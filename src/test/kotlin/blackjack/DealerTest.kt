package blackjack

import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.Dealer
import blackjack.domain.state.Hit
import blackjack.domain.state.Stay
import blackjack.domain.strategy.draw.HitDrawStrategy
import blackjack.state.CARD_HEART_EIGHT
import blackjack.state.CARD_HEART_KING
import blackjack.state.CARD_HEART_TEN
import blackjack.state.CARD_HEART_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DealerTest {

    @Test
    fun `딜러는 16점을 초과하면 카드를 받을 수 없다`() {
        val overSixteenCards = Cards(listOf(CARD_HEART_KING, CARD_HEART_TEN))
        val hit = Hit(overSixteenCards)
        val dealer = Dealer(state = hit)
        assertThat(dealer.canHit()).isFalse
    }

    @Test
    fun `딜러는 16점 이하이면 카드를 받을 수 있다`() {
        val belowSixteenCards = Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO))
        val hit = Hit(belowSixteenCards)
        val dealer = Dealer(state = hit)
        assertThat(dealer.canHit()).isTrue
    }

    @Test
    fun `딜러가 처음에 뽑은 두장의 카드가 16이하이면 카드를 받을 수 있다`() {
        val cards = Cards(listOf(CARD_HEART_KING, CARD_HEART_TWO))
        val hit = Hit(cards)

        val dealer = Dealer(state = hit)
            .draw(CardDeck(), HitDrawStrategy)

        assertThat(dealer.cards.cards.size).isEqualTo(3)
    }

    @Test
    fun `딜러가 처음에 뽑은 두장의 카드가 16이상 21미만이면 Stay 상태이다`() {
        val cards = Cards(listOf(CARD_HEART_KING, CARD_HEART_EIGHT))
        val hit = Hit(cards)

        val dealer = Dealer(state = hit)
            .draw(CardDeck(), HitDrawStrategy)

        assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `딜러가 처음에 뽑은 두장의 카드가 16이상 21미만이면 카드를 받을 수 없다`() {
        val cards = Cards(listOf(CARD_HEART_KING, CARD_HEART_EIGHT))
        val hit = Hit(cards)

        val dealer = Dealer(state = hit)
            .draw(CardDeck(), HitDrawStrategy)

        assertThat(dealer.cards.cards.size).isEqualTo(2)
    }

    @Test
    fun `딜러는 stay()할 수 있다`() {
        val cards = Cards(listOf(CARD_HEART_KING, CARD_HEART_EIGHT))
        val hit = Hit(cards)

        val dealer = Dealer(state = hit)
            .draw(CardDeck(), HitDrawStrategy)
            .stay()

        assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }
}
