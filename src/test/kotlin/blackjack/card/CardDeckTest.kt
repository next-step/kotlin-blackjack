package blackjack.card

import blackjack.model.card.CardDeck
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardDeckTest {

    @DisplayName(value = "한 세트의 CardPool의 사이즈는 52장이다.")
    @Test
    fun checkCardPoolSize() {
        val cards = CardDeck().cards
        Assertions.assertThat(cards.size).isEqualTo(52)
    }

    @DisplayName(value = "한 세트의 CardPool의 pop이후 사이즈는 헤당 pop 만큼 줄어야한다.")
    @Test
    fun checkCardPoolSizeAfterPop() {
        val cardPool = CardDeck()
        val popCount = (1..52).random()
        (1..popCount).map {
            cardPool.popCard()
        }
        Assertions.assertThat(cardPool.cards.size).isEqualTo(52 - popCount)
    }

    @DisplayName(value = "한세트의 CarPool은 중복 제거를 해도 같아야 한다.")
    @Test
    fun checkDuplicationCardPool() {
        val cards = CardDeck().cards
        val cards2 = cards.distinct()
        Assertions.assertThat(cards2).containsAll(cards)
    }

    @DisplayName(value = "한세트의 CarPool은 pop된 카드는 포함하고 있으면 안된다. ")
    @Test
    fun checkPopInCardPool() {
        val cardPool = CardDeck()
        val card = cardPool.popCard()
        Assertions.assertThat(cardPool.cards).doesNotContain(card)
        Assertions.assertThat(cardPool.cards.size).isEqualTo(51)
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
