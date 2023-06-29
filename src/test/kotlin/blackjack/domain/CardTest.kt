package blackjack.domain

import blackjack.domain.Card.Companion.newCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CardTest {

    @Test
    fun `서로 같은 카드를 구분할 수 있다`() {
        // given
        val heartTwo1 = newCard {
            CardImage.HEART with CardLevel.TWO
        }
        val heartTwo2 = newCard {
            CardImage.HEART with CardLevel.TWO
        }

        // when
        // then
        assertThat(heartTwo1 == heartTwo2).isEqualTo(true)
    }

    @Test
    fun `서로 다른 카드를 구분할 수 있다`() {
        // given
        val heartOne = newCard {
            CardImage.HEART with CardLevel.ACE
        }
        val heartTwo = newCard {
            CardImage.HEART with CardLevel.TWO
        }

        // when
        // then
        assertThat(heartOne == heartTwo).isEqualTo(false)
    }
}
