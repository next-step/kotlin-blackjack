package blackjack.card

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardType
import blackjack.model.card.Cards
import blackjack.model.status.Score
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardsTest {

    @DisplayName(value = "Happy Case")
    @Test
    fun checkBlackJackCase() {
        val cards = Cards().apply {
            addCard(Card(CardType.CLUBS, CardNumber.ONE))
            addCard(Card(CardType.DIAMONDS, CardNumber.KING))
        }

        Assertions.assertThat(cards.getScore())
            .isEqualTo(Score(Score.BLACKJACK))
    }

    @DisplayName(value = "ACE 를 포함할 경우, 21이하에 가까운 숫자로 변환")
    @Test
    fun checkAceCaseCards1() {
        val cards = Cards().apply {
            addCard(Card(CardType.CLUBS, CardNumber.ONE))
            addCard(Card(CardType.DIAMONDS, CardNumber.ONE))
        }
        Assertions.assertThat(cards.getScore())
            .isEqualTo(Score(CardNumber.ONE.score + CardNumber.ONE.score + Cards.ACE_GAP))
    }

    @DisplayName(value = "ACE 를 포함할 경우, 21이하에 가까운 숫자로 변환2")
    @Test
    fun checkAceCaseCards2() {
        val cards = Cards().apply {
            addCard(Card(CardType.CLUBS, CardNumber.ONE))
            addCard(Card(CardType.DIAMONDS, CardNumber.FIVE))
            addCard(Card(CardType.DIAMONDS, CardNumber.SEVEN))
        }
        Assertions.assertThat(cards.getScore())
            .isEqualTo(Score(CardNumber.FIVE.score + CardNumber.SEVEN.score + CardNumber.ONE.score))
    }

    @DisplayName(value = "Ace를 포함한 경우, 1로 계산되어, 21를 넘지 않으면 카드를 더 받을수 있다.")
    @Test
    fun checkCanMoreCardContainAce() {
        val cards = Cards().apply {
            addCard(Card(CardType.CLUBS, CardNumber.ONE))
            addCard(Card(CardType.DIAMONDS, CardNumber.KING))
            addCard(Card(CardType.DIAMONDS, CardNumber.KING))
        }
        Assertions.assertThat(cards.isBurst()).isFalse()
    }

    @DisplayName(value = "21를 넘은 경우, 카드를 받을 수 없다..")
    @Test
    fun checkCanMoreCardOverCase() {
        val cards = Cards().apply {
            addCard(Card(CardType.CLUBS, CardNumber.KING))
            addCard(Card(CardType.DIAMONDS, CardNumber.KING))
            addCard(Card(CardType.DIAMONDS, CardNumber.KING))
        }
        Assertions.assertThat(cards.isBurst()).isTrue()
    }
}
