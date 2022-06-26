package camp.nextstep.blackjack.player

import camp.nextstep.blackjack.card.Card
import camp.nextstep.blackjack.card.CardDeck
import camp.nextstep.blackjack.card.CardNumber
import camp.nextstep.blackjack.card.CardSuit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class DealerTest {

    @DisplayName("딜러는 카드덱에서 카드를 뽑아 플레이어에게 줄 수 있다.")
    @Test
    fun serveCardToPlayer() {
        val dealer = Dealer()
        val cardDeck = CardDeck.of(
            listOf(
                Card(CardSuit.SPADE, CardNumber.ACE),
                Card(CardSuit.SPADE, CardNumber.TWO),
            )
        )
        val gambler = Gambler("tim",)

        dealer.serve(cardDeck, gambler)
        dealer.serve(cardDeck, gambler)

        assertThat(gambler.hand.cards).containsExactly(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.SPADE, CardNumber.TWO))
    }

    @DisplayName("딜러의 처음 카드 패는 비어있다.")
    @Test
    fun newDealerHasAnyCards() {
        val dealer = Dealer()
        assertThat(dealer.hand.isEmpty()).isTrue
    }

    @DisplayName("딜러는 카드를 받아 패에 추가할 수 있다.")
    @Test
    fun receiveCard() {
        val dealer = Dealer()
        dealer.receive(Card(CardSuit.SPADE, CardNumber.ACE))
        assertThat(dealer.hand.cards).containsExactly(Card(CardSuit.SPADE, CardNumber.ACE))
    }

    @DisplayName("딜러의 첫번째 카드는 공개한다.")
    @Test
    fun faceUpFirstCard() {
        val dealer = Dealer()
        dealer.receive(Card(CardSuit.SPADE, CardNumber.ACE))
        assertThat(dealer.hand.faceUpCards).containsExactly(Card(CardSuit.SPADE, CardNumber.ACE))
        assertThat(dealer.hand.faceDownCardCount).isEqualTo(0)
    }

    @DisplayName("딜러의 첫번째 카드가 10 혹은 에이스가 아니면 두 번째 카드는 비공개한다.")
    @Test
    fun faceDownSecondCardIfFirstCardIsTenNorAce() {
        val dealer = Dealer()
        dealer.receive(Card(CardSuit.SPADE, CardNumber.TWO))
        dealer.receive(Card(CardSuit.SPADE, CardNumber.TEN))
        assertThat(dealer.hand.faceUpCards).containsExactly(Card(CardSuit.SPADE, CardNumber.TWO))
        assertThat(dealer.hand.faceDownCardCount).isEqualTo(1)
    }

    @DisplayName("딜러의 첫번째 카드 10이면 두번째 카드는 공개한다. ")
    @Test
    fun faceUpSecondCardIfFirstCardIsTen() {
        val dealer = Dealer()
        dealer.receive(Card(CardSuit.SPADE, CardNumber.TEN))
        dealer.receive(Card(CardSuit.SPADE, CardNumber.TWO))
        assertThat(dealer.hand.faceUpCards).containsExactly(Card(CardSuit.SPADE, CardNumber.TEN), Card(CardSuit.SPADE, CardNumber.TWO))
        assertThat(dealer.hand.faceDownCardCount).isEqualTo(0)
    }

    @DisplayName("딜러의 첫번째 카드 에이스면 두번째 카드는 공개한다. ")
    @Test
    fun faceUpSecondCardIfFirstCardIsAce() {
        val dealer = Dealer()
        dealer.receive(Card(CardSuit.SPADE, CardNumber.ACE))
        dealer.receive(Card(CardSuit.SPADE, CardNumber.TWO))
        assertThat(dealer.hand.faceUpCards).containsExactly(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.SPADE, CardNumber.TWO))
        assertThat(dealer.hand.faceDownCardCount).isEqualTo(0)
    }

    @DisplayName("딜러의 세번째 카드부터는 항상 공개한다.")
    @Test
    fun faceUpAfterThirdCard() {
        val dealer = Dealer()
        dealer.receive(Card(CardSuit.SPADE, CardNumber.ACE))
        dealer.receive(Card(CardSuit.SPADE, CardNumber.TWO))
        dealer.receive(Card(CardSuit.SPADE, CardNumber.THREE))
        assertThat(dealer.hand.faceUpCards).containsExactly(Card(CardSuit.SPADE, CardNumber.ACE), Card(CardSuit.SPADE, CardNumber.TWO), Card(CardSuit.SPADE, CardNumber.THREE))
        assertThat(dealer.hand.faceDownCardCount).isEqualTo(0)
    }
}
