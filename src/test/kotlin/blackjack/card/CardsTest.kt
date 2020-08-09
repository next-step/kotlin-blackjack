package blackjack.card

import blackjack.model.card.Card
import blackjack.model.card.CardNumber
import blackjack.model.card.CardType
import blackjack.model.card.Cards
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class CardsTest {

    @DisplayName(value = "Happy Case")
    @Test
    fun checkBlackJackCase() {
        val cards = Cards()
        cards.addCard(Card.newInstance(CardType.CLUBS, CardNumber.ONE))
        cards.addCard(Card.newInstance(CardType.DIAMONDS, CardNumber.KING))

        Assertions.assertThat(cards.getScore())
            .isEqualTo(Cards.BLACKJACK_SCORE)
    }

    @DisplayName(value = "ACE 를 포함할 경우, 21이하에 가까운 숫자로 변환")
    @Test
    fun checkAceCaseCards1() {
        val cards = Cards()
        cards.addCard(Card.newInstance(CardType.CLUBS, CardNumber.ONE))
        cards.addCard(Card.newInstance(CardType.DIAMONDS, CardNumber.ONE))

        Assertions.assertThat(cards.getScore())
            .isEqualTo(CardNumber.ONE.score + CardNumber.ONE.score + Cards.ACE_GAP)
    }

    @DisplayName(value = "ACE 를 포함할 경우, 21이하에 가까운 숫자로 변환2")
    @Test
    fun checkAceCaseCards2() {
        val cards = Cards()
        cards.addCard(Card.newInstance(CardType.CLUBS, CardNumber.ONE))
        cards.addCard(Card.newInstance(CardType.DIAMONDS, CardNumber.FIVE))
        cards.addCard(Card.newInstance(CardType.DIAMONDS, CardNumber.SEVEN))

        Assertions.assertThat(cards.getScore())
            .isEqualTo(CardNumber.FIVE.score + CardNumber.SEVEN.score + CardNumber.ONE.score)
    }
}
