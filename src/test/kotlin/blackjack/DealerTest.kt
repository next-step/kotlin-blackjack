package blackjack

import blackjack.domain.Dealer
import blackjack.domain.GamePlayer
import blackjack.domain.Money
import blackjack.domain.Name
import blackjack.domain.card.CardDeck
import blackjack.domain.card.Cards
import blackjack.domain.state.Hit
import blackjack.domain.state.Initial
import blackjack.domain.state.Stay
import blackjack.domain.strategy.draw.HitDrawStrategy
import blackjack.state.CARD_HEART_EIGHT
import blackjack.state.CARD_HEART_FOUR
import blackjack.state.CARD_HEART_KING
import blackjack.state.CARD_HEART_TEN
import blackjack.state.CARD_HEART_TWO
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.LinkedList

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
    fun `딜러가 처음에 뽑은 두장의 카드가 16이하이면 Hit 상태이다`() {
        val cardDeck = CardDeck(LinkedList(listOf(CARD_HEART_TWO, CARD_HEART_FOUR)))
        val initial = Initial()

        val dealer = Dealer(state = initial)
            .draw(cardDeck, HitDrawStrategy)
            .draw(cardDeck, HitDrawStrategy)

        assertThat(dealer.state).isInstanceOf(Hit::class.java)
    }

    @Test
    fun `딜러가 처음에 뽑은 두장의 카드가 16이상 21이하면 Stay상태이다`() {
        val cardDeck = CardDeck(LinkedList(listOf(CARD_HEART_EIGHT, CARD_HEART_KING)))
        val initial = Initial()

        val dealer = Dealer(state = initial)
            .draw(cardDeck, HitDrawStrategy)
            .draw(cardDeck, HitDrawStrategy)

        assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `딜러는 stay()할 수 있다`() {
        val cardDeck = CardDeck(LinkedList(listOf(CARD_HEART_TWO, CARD_HEART_FOUR)))
        val initial = Initial()

        val dealer = Dealer(state = initial)
            .draw(cardDeck, HitDrawStrategy)
            .draw(cardDeck, HitDrawStrategy)
            .stay()

        assertThat(dealer.state).isInstanceOf(Stay::class.java)
    }

    @Test
    fun `딜러의 profit()은 GamePlayer profit()의 음수값이다`() {
        val dealerCards = Cards(listOf(CARD_HEART_KING, CARD_HEART_EIGHT))
        val dealerState = Stay(dealerCards)
        val dealer = Dealer(state = dealerState)

        val gamePlayerCards = Cards(listOf(CARD_HEART_TWO, CARD_HEART_FOUR))
        val gamePlayerState = Stay(gamePlayerCards)
        val gamePlayer = GamePlayer(name = Name.from("플레이어"), gamePlayerState)

        val gamePlayerProfit = gamePlayer.profit(dealer, Money.from("3000"))
        val dealerProfit = dealer.profit(gamePlayer, Money.from("3000"))

        assertThat(dealerProfit).isEqualTo(-gamePlayerProfit)
    }
}
