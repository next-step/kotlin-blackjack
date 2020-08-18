package blackjack

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardType
import blackjack.model.card.toCards
import blackjack.model.player.Dealer
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class DealerTest {

    @DisplayName(value = "Dealer가 21이상일 경우, 카드를 받을수 없다.")
    @Test
    fun checkDealerCanNotMoreHit() {
        val cards = listOf(
            Card(CardType.DIAMONDS, CardNumber.ONE),
            Card(CardType.HEARTS, CardNumber.KING)
        ).toCards()
        val dealer = Dealer(cards)

        Assertions.assertThat(dealer.canMoreCard()).isFalse()
    }

    @DisplayName(value = "Player가 16이하일 경우, 카드를 받을수 있다.")
    @Test
    fun checkDealerCanMoreHit() {
        val cards = listOf(
            Card(CardType.DIAMONDS, CardNumber.SIX),
            Card(CardType.HEARTS, CardNumber.KING)
        ).toCards()
        val dealer = Dealer(cards)

        Assertions.assertThat(dealer.canMoreCard()).isTrue()
    }

    @DisplayName(value = "Player가 16보다 클때, 카드를 받지 않는다.")
    @Test
    fun checkDealerCanMoreHitOver16() {
        val cards = listOf(
            Card(CardType.DIAMONDS, CardNumber.SEVEN),
            Card(CardType.HEARTS, CardNumber.KING)
        ).toCards()
        val dealer = Dealer(cards)

        Assertions.assertThat(dealer.canMoreCard()).isFalse()
    }
}
