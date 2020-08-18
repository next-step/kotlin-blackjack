package blackjack.card

import blackjack.model.card.CardDeck
import blackjack.model.card.CardNumber
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardDeckTest {

    @DisplayName(value = "한 세트의 CardPool의 사이즈는 52장이다.")
    @Test
    fun checkCardPopTest() {
        val cardDeck = CardDeck()
        val resultDeck = (1..52).map {
            cardDeck.popCard()
        }
        Assertions.assertThat(resultDeck.distinct().size).isEqualTo(52)
    }

    @DisplayName(value = "pop된 카드의 스코어는 1~10 이여야한다.")
    @Test
    fun checkPopTest() {
        val cardDeck = CardDeck()
        val card = cardDeck.popCard()
        Assertions.assertThat(card.getScore()).isIn(CardNumber.values().map { it.score })
    }

    @DisplayName(value = "Player의 Pop하는 카드더미의 카드수는 2장이여야한다.")
    @Test
    fun checkPopPlayerTest() {
        val cardDeck = CardDeck()
        val cardDummy = cardDeck.popPlayerCardDummy()
        Assertions.assertThat(cardDummy.dummy.size)
            .isEqualTo(CardDeck.PLAYER_INIT_CARD_COUNT)
    }

    @DisplayName(value = "Dealer의 Pop하는 카드더미의 카드수는 1장이여야한다.")
    @Test
    fun checkPopDealerTest() {
        val cardDeck = CardDeck()
        val cardDummy = cardDeck.popDealerCardDummy()
        Assertions.assertThat(cardDummy.dummy.size)
            .isEqualTo(CardDeck.DEALER_INIT_CARD_COUNT)
    }

    @DisplayName(value = "한세트의 CarPool은 52번 이상의 popCard는 진행할 수 없다. ,Exception")
    @Test
    fun inputNegativeAndNonInteger() {
        val cardPool = CardDeck()
        Assertions.assertThatExceptionOfType(RuntimeException::class.java)
            .isThrownBy {
                (1..53).map {
                    cardPool.popCard()
                }
            }
    }
}
