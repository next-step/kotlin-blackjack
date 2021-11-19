package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GamerTest {

    @Test
    fun `이름을 입력하여 게이머를 생성할 수 있다`() {
        val givenName = Name("player1")
        val actual = Gamer(Profile.from(givenName))

        assertThat(actual).isNotNull
    }

    @Test
    fun `카드를 받으면 게이머가 가진 카드 묶음에 추가된다`() {
        val givenName = Name("player1")
        val givenGamer = Gamer(Profile.from(givenName))
        val givenCard = Card(Suit.CLUB, Denomination.TWO)

        val actual = givenGamer.receiveCard(givenCard)

        assertThat(actual.cards).contains(givenCard)
    }

    @Test
    fun `턴 종료시 게이머의 상태는 중지가 된다`() {
        val givenName = Name("player1")
        val givenGamer = Gamer(Profile.from(givenName))

        givenGamer.turnOff()

        assertThat(givenGamer.isBurst()).isFalse
    }

    @Test
    fun `턴이면 게이머의 상태는 차례가 된다`() {
        val givenName = Name("player1")
        val givenGamer = Gamer(Profile.from(givenName))

        val actual = givenGamer.turnOn()

        assertThat(actual.isBurst()).isTrue
    }

    @Test
    fun `게이머 차례가 맞으면 true를 리턴한다`() {
        val givenName = Name("player1")
        val givenStatus = PlayerStatus.BURST
        val givenCards = Cards.EMPTY
        val givenGamer = Gamer(Profile(givenName, givenStatus), givenCards)

        assertThat(givenGamer.isBurst()).isTrue
    }
}
