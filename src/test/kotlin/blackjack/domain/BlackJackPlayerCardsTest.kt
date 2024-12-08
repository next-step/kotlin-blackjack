package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlackJackPlayerCardsTest {
    @Test
    fun addcardTest() {
        val blackJackPlayerCards =
            BlackJackPlayerCards(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                ),
            )
        blackJackPlayerCards.addCard(BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE))
        assertThat(blackJackPlayerCards.cards.size).isEqualTo(3)
        assertThat(blackJackPlayerCards.cards[2]).isEqualTo(BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE))
    }

    @Test
    fun `카드 추가 불가 케이스 테스트`() {
        val blackJackPlayerCards =
            BlackJackPlayerCards(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.TWO),
                ),
            )
        assertThatThrownBy { blackJackPlayerCards.addCard(BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("21을 넘으면 카드를 못뽑아요")
    }

    @Test
    fun bestSumTest() {
        val blackJackPlayerCards1 =
            BlackJackPlayerCards(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard.get(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                ),
            )

        val blackJackPlayerCards2 =
            BlackJackPlayerCards(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.ACE),
                    BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.TWO),
                ),
            )

        assertThat(blackJackPlayerCards1.getCardsBestSum()).isEqualTo(21)
        assertThat(blackJackPlayerCards2.getCardsBestSum()).isEqualTo(13)
    }

    @Test
    fun byDeckTest() {
        val blackJackDeck =
            BlackJackDeck(
                mutableListOf(
                    BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.ACE),
                    BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.JACK),
                    BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.TWO),
                ),
            )
        assertThat(BlackJackPlayerCards.byDeck(blackJackDeck).cards).containsExactly(
            BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.TWO),
            BlackJackCard.get(BlackJackCardShape.DIAMOND, BlackJackCardNumber.JACK),
        )
    }
}
