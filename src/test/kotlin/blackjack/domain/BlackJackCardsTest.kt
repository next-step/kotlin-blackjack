package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlackJackCardsTest {
    @Test
    fun addcardTest() {
        val blackJackCards =
            BlackJackCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                ),
            )
        blackJackCards.addCard(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE))
        assertThat(blackJackCards.cards.size).isEqualTo(3)
        assertThat(blackJackCards.cards[2]).isEqualTo(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE))
    }

    @Test
    fun `카드 추가 불가 케이스 테스트`() {
        val blackJackCards =
            BlackJackCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.KING),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.TWO),
                ),
            )
        assertThatThrownBy { blackJackCards.addCard(BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE)) }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("21을 넘으면 카드를 못뽑아요")
    }

    @Test
    fun bestSumTest() {
        val blackJackCards1 =
            BlackJackCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.ACE),
                    BlackJackCard(BlackJackCardShape.HEART, BlackJackCardNumber.JACK),
                ),
            )

        val blackJackCards2 =
            BlackJackCards(
                mutableListOf(
                    BlackJackCard(BlackJackCardShape.DIAMOND, BlackJackCardNumber.ACE),
                    BlackJackCard(BlackJackCardShape.DIAMOND, BlackJackCardNumber.JACK),
                    BlackJackCard(BlackJackCardShape.DIAMOND, BlackJackCardNumber.TWO),
                ),
            )

        assertThat(blackJackCards1.getCardsBestSum()).isEqualTo(21)
        assertThat(blackJackCards2.getCardsBestSum()).isEqualTo(13)
    }
}
