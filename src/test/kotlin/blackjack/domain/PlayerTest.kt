package blackjack.domain

import blackjack.domain.Card.Companion.newCard
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class PlayerTest {

    private val card: Card = newCard {
        CardImage.HEART with CardLevel.JACK
    }

    @Test
    fun `플레이어는 이름을 갖는다`() {
        // given
        val sut = Player("jun")

        // when
        // then
        assertThat(sut.name).isEqualTo("jun")
    }

    @Test
    fun `초기상태 플레이어는 아무런 카드가 없다`() {
        // given
        val sut = Player("jun")

        // when
        val cards = sut.cards

        // then
        assertThat(cards.all().size).isEqualTo(0)
    }

    @Test
    fun `플레이어는 카드를 가져갈 수 있다`() {
        // given
        val sut = Player("jun")

        // when
        val cards = sut.receiveCard(card)

        // then
        assertThat(cards.all().size).isEqualTo(1)
        assertThat(cards.all().first()).isEqualTo(card)
        assertThat(sut.cards.all().size).isEqualTo(1)
        assertThat(sut.cards.all().first()).isEqualTo(card)
    }

    @Test
    fun `플레이어는 카드를 여러장 가져갈 수 있다`() {
        // given
        val sut = Player("jun")

        // when
        sut.receiveCard(card)
        sut.receiveCard(card)
        val cards = sut.receiveCard(card)

        // then
        assertThat(cards.all().size).isEqualTo(3)
        assertThat(sut.cards.all().size).isEqualTo(3)
        assertThat(sut.cards.all()[0]).isEqualTo(card)
        assertThat(sut.cards.all()[1]).isEqualTo(card)
        assertThat(sut.cards.all()[2]).isEqualTo(card)
    }
}
