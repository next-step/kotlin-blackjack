package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.game.Score
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
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

        assertThat(actual.openCards()).contains(givenCard)
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

    @Test
    fun `플레이어를 입력받아 게임 결과를 리턴한다`() {
        val card1 = Card(Suit.HEART, Denomination.SIX)
        val card2 = Card(Suit.CLUB, Denomination.NINE)
        val card3 = Card(Suit.DIAMOND, Denomination.JACK)
        val card4 = Card(Suit.DIAMOND, Denomination.FIVE)
        val card5 = Card(Suit.DIAMOND, Denomination.QUEEN)
        val card6 = Card(Suit.HEART, Denomination.QUEEN)
        val card7 = Card(Suit.HEART, Denomination.EIGHT)

        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val profile3 = Profile.from(Name("dealer"))
        val givenGamer1 = Gamer(profile1, Cards(listOf(card1, card2, card3)))
        val givenGamer2 = Gamer(profile2, Cards(listOf(card4, card5)))
        val dealer = Dealer(profile3, Cards(listOf(card6, card7)))
        val gamers = Players(listOf(givenGamer1, givenGamer2))

        val actual = dealer.judge(gamers)

        Assertions.assertAll(
            { assertThat(actual[dealer]).contains(Score.WIN) },
            { assertThat(actual[givenGamer1]).contains(Score.LOSE) },
            { assertThat(actual[givenGamer2]).contains(Score.LOSE) }
        )
    }
}
