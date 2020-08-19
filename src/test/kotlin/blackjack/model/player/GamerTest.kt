package blackjack.model.player

import blackjack.model.card.Card
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class GamerTest {
    private lateinit var dealer: Dealer
    private lateinit var gamer: Gamer

    private val cardA = Card(Card.Suit.CLUBS, Card.Denomination.ACE)
    private val card10 = Card(Card.Suit.CLUBS, Card.Denomination.TEN)
    private val card9 = Card(Card.Suit.CLUBS, Card.Denomination.NINE)
    private val card8 = Card(Card.Suit.CLUBS, Card.Denomination.EIGHT)

    @BeforeEach
    fun beforeTest() {
        dealer = Dealer()
        gamer = Gamer("jejun")
    }

    @DisplayName(value = "21 이하, 딜러 승리")
    @Test
    fun dealerWin() {
        dealer.cards.add(card10)
        gamer.cards.add(card9)

        Assertions.assertThat(gamer.getPrizeRate(dealer)).isEqualTo(Gamer.ZERO)
    }

    @DisplayName(value = "21 이하, 게이머 승리")
    @Test
    fun gamerWin() {
        dealer.cards.add(card9)
        gamer.cards.add(cardA)

        Assertions.assertThat(gamer.getPrizeRate(dealer)).isEqualTo(Gamer.DOUBLE)
    }

    @DisplayName(value = "21 초과, 게이머 승리")
    @Test
    fun gamerWinOverBlackJackNumber() {

        dealer.cards.add(card8)
        dealer.cards.add(card8)
        dealer.cards.add(card8)

        gamer.cards.add(card8)
        gamer.cards.add(card8)
        gamer.cards.add(card9)

        Assertions.assertThat(gamer.getPrizeRate(dealer)).isEqualTo(Gamer.DOUBLE)
    }

    @DisplayName(value = "게이머 블랙잭")
    @Test
    fun gamerBlackJack() {

        dealer.cards.add(cardA)
        dealer.cards.add(card9)
        dealer.cards.getCardStatus()

        gamer.cards.add(cardA)
        gamer.cards.add(card10)
        gamer.cards.getCardStatus()

        Assertions.assertThat(gamer.getPrizeRate(dealer)).isEqualTo(Gamer.DOUBLE_HALF)
    }

    @DisplayName(value = "게이머 블랙잭 && 딜러 블랙")
    @Test
    fun gamerBlackJackAndDealerBlackJack() {

        dealer.cards.add(cardA)
        dealer.cards.add(card10)
        dealer.cards.getCardStatus()

        gamer.cards.add(cardA)
        gamer.cards.add(card10)
        gamer.cards.getCardStatus()

        Assertions.assertThat(gamer.getPrizeRate(dealer)).isEqualTo(Gamer.RESTORE)
    }
}
