package blackjack.domain

import blackjack.domain.Card.Companion.newCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CardsTest {
    private val cloverAce = newCard {
        CardImage.CLOVER with CardLevel.ACE
    }
    private val cloverTwo = newCard {
        CardImage.CLOVER with CardLevel.TWO
    }

    @Test
    fun `여러장의 카드를 포함할 수 있다`() {
        // given
        val cardList = listOf(cloverAce, cloverTwo)
        val sut = Cards(cardList)

        // when
        // then
        assertThat(sut.all().size).isEqualTo(2)
        assertThat(sut.all()[0]).isEqualTo(cloverAce)
        assertThat(sut.all()[1]).isEqualTo(cloverTwo)
    }

    @Test
    fun `기존 카드들에 새로운 카드를 추가할 수 있다`() {
        // given
        val cardList = listOf(cloverAce, cloverTwo)
        val newCard = newCard { CardImage.CLOVER with CardLevel.THREE }
        val sut = Cards(cardList)

        // when
        val newCards = sut.addCard(newCard)

        // then
        assertThat(newCards.all().size).isEqualTo(3)
        assertThat(newCards.all()[0]).isEqualTo(cloverAce)
        assertThat(newCards.all()[1]).isEqualTo(cloverTwo)
        assertThat(newCards.all()[2]).isEqualTo(newCard)
    }
}
