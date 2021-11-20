package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class DealerTest {
    @Test
    fun `딜러를 생성할 수 있다`() {
        val actual = Dealer(Profile.createDealer())

        assertThat(actual).isNotNull
    }

    @Test
    fun `카드를 받으면 딜러가 가진 카드 묶음에 추가된다`() {
        val givenDealer = Dealer.of()
        val givenCard = Card(Suit.CLUB, Denomination.TWO)

        val actual = givenDealer.receiveCard(givenCard)

        assertThat(actual.openCards()).contains(givenCard)
    }

    @Test
    fun `턴 종료시 게이머의 상태는 중지가 된다`() {
        val givenDealer = Dealer.of()

        givenDealer.turnOff()

        assertThat(givenDealer.isBurst()).isFalse
    }

    @Test
    fun `턴이면 딜러의 상태는 차례가 된다`() {
        val givenDealer = Dealer.of()

        val actual = givenDealer.turnOn()

        assertThat(actual.isBurst()).isTrue
    }

    @Test
    fun `딜러 차례가 맞으면 true를 리턴한다`() {
        val givenName = Name("dealer")
        val givenStatus = PlayerStatus.BURST
        val givenCards = Cards.EMPTY
        val givenDealer = Dealer(Profile(givenName, givenStatus), givenCards)

        assertThat(givenDealer.isBurst()).isTrue
    }

    @Test
    fun `딜러의 카드 총합이 17이 넘으면 더 이상 카드를 받을 수 없다`() {
        val givenName = Name("dealer")
        val givenStatus = PlayerStatus.BURST
        val card1 = Card(Suit.HEART, Denomination.QUEEN)
        val card2 = Card(Suit.CLUB, Denomination.SEVEN)
        val card3 = Card(Suit.DIAMOND, Denomination.TWO)
        val givenCards = Cards.from(listOf(card1, card2))
        val givenDealer = Dealer(Profile(givenName, givenStatus), givenCards)

        assertThat(givenDealer.getHighestPoint()).isEqualTo(17)

        givenDealer.receiveCard(card3)

        assertThat(givenDealer.getHighestPoint()).isEqualTo(17)
    }
}
