package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.game.Credit
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

    @Test
    fun `딜러가 총합이 21을 넘으면 게이머가 배팅한 금액을 리턴한다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenStatus = PlayerStatus.BURST
        val givenName = Name("dealer")
        val card1 = Card(Suit.HEART, Denomination.QUEEN)
        val card2 = Card(Suit.CLUB, Denomination.KING)
        val card3 = Card(Suit.DIAMOND, Denomination.TWO)
        val givenCards = Cards.from(listOf(card1, card2, card3))
        val dealer = Dealer(Profile(givenName, givenStatus), givenCards)
        val givenGamer1 = Gamer(profile1, Cards.EMPTY, Credit.from(100))
        val givenGamer2 = Gamer(profile2, Cards.EMPTY, Credit.from(100))

        val actual = dealer.judge(listOf(givenGamer1, givenGamer2))

        assertThat(actual.players.getDealer()?.getPlayerCredit()).isEqualTo(Credit.from(-200))
        assertThat(actual.players.getPlayer(givenGamer1)?.getPlayerCredit()).isEqualTo(Credit.from(200))
        assertThat(actual.players.getPlayer(givenGamer2)?.getPlayerCredit()).isEqualTo(Credit.from(200))
    }

    @Test
    fun `게이머 카드 총합이 21을 넘으면 딜러가 게이머가 배팅한 금액을 리턴한다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenStatus = PlayerStatus.BURST
        val givenName = Name("dealer")
        val card1 = Card(Suit.HEART, Denomination.QUEEN)
        val card2 = Card(Suit.CLUB, Denomination.KING)
        val card3 = Card(Suit.DIAMOND, Denomination.TWO)
        val givenCards = Cards.from(listOf(card1, card2, card3))
        val dealer = Dealer(Profile(givenName, givenStatus))
        val givenGamer1 = Gamer(profile1, givenCards, Credit.from(100))
        val givenGamer2 = Gamer(profile2, givenCards, Credit.from(100))

        val actual = dealer.judge(listOf(givenGamer1, givenGamer2))

        assertThat(actual.players.getDealer()?.getPlayerCredit()).isEqualTo(Credit.from(200))
        assertThat(actual.players.getPlayer(givenGamer1)?.getPlayerCredit()).isEqualTo(Credit.from(0))
        assertThat(actual.players.getPlayer(givenGamer2)?.getPlayerCredit()).isEqualTo(Credit.from(0))
    }

    @Test
    fun `게이머 카드가 딜러를 이기면 베팅한 금액을 가져간다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenStatus = PlayerStatus.BURST
        val givenName = Name("dealer")
        val card1 = Card(Suit.HEART, Denomination.KING)
        val card2 = Card(Suit.CLUB, Denomination.KING)
        val givenCards = Cards.from(listOf(card1, card2))
        val dealer = Dealer(Profile(givenName, givenStatus))
        val givenGamer1 = Gamer(profile1, givenCards, Credit.from(100))
        val givenGamer2 = Gamer(profile2, givenCards, Credit.from(100))

        val actual = dealer.judge(listOf(givenGamer1, givenGamer2))

        assertThat(actual.players.getDealer()?.getPlayerCredit()).isEqualTo(Credit.from(-200))
        assertThat(actual.players.getPlayer(givenGamer1)?.getPlayerCredit()).isEqualTo(Credit.from(200))
        assertThat(actual.players.getPlayer(givenGamer2)?.getPlayerCredit()).isEqualTo(Credit.from(200))
    }

    @Test
    fun `딜러가 이기면 베팅한 금액을 가져간다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenStatus = PlayerStatus.BURST
        val givenName = Name("dealer")
        val card1 = Card(Suit.HEART, Denomination.NINE)
        val card2 = Card(Suit.CLUB, Denomination.NINE)
        val card3 = Card(Suit.DIAMOND, Denomination.TWO)
        val givenCards = Cards.from(listOf(card1, card2))
        val dealer = Dealer(Profile(givenName, givenStatus), Cards.from(listOf(card1, card2, card3)))
        val givenGamer1 = Gamer(profile1, givenCards, Credit.from(100))
        val givenGamer2 = Gamer(profile2, givenCards, Credit.from(100))

        val actual = dealer.judge(listOf(givenGamer1, givenGamer2))

        assertThat(actual.players.getDealer()?.getPlayerCredit()).isEqualTo(Credit.from(200))
        assertThat(actual.players.getPlayer(givenGamer1)?.getPlayerCredit()).isEqualTo(Credit.from(0))
        assertThat(actual.players.getPlayer(givenGamer2)?.getPlayerCredit()).isEqualTo(Credit.from(0))
    }

    @Test
    fun `비길경우 게이머는 베팅금액을 돌려받는다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenStatus = PlayerStatus.BURST
        val givenName = Name("dealer")
        val card1 = Card(Suit.HEART, Denomination.NINE)
        val card2 = Card(Suit.CLUB, Denomination.NINE)
        val givenCards = Cards.from(listOf(card1, card2))
        val dealer = Dealer(Profile(givenName, givenStatus), givenCards)
        val givenGamer1 = Gamer(profile1, givenCards, Credit.from(100))
        val givenGamer2 = Gamer(profile2, givenCards, Credit.from(100))

        val actual = dealer.judge(listOf(givenGamer1, givenGamer2))

        assertThat(actual.players.getDealer()?.getPlayerCredit()).isEqualTo(Credit.from(0))
        assertThat(actual.players.getPlayer(givenGamer1)?.getPlayerCredit()).isEqualTo(Credit.from(100))
        assertThat(actual.players.getPlayer(givenGamer2)?.getPlayerCredit()).isEqualTo(Credit.from(100))
    }

    @Test
    fun `게이머가 블랙잭이면 베팅금액의 1배 반을 돌려받는다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenStatus = PlayerStatus.BURST
        val givenName = Name("dealer")
        val card1 = Card(Suit.HEART, Denomination.KING)
        val card2 = Card(Suit.CLUB, Denomination.ACE)
        val card3 = Card(Suit.CLUB, Denomination.THREE)
        val givenCards = Cards.from(listOf(card1, card2))
        val dealer = Dealer(Profile(givenName, givenStatus), Cards.from(listOf(card1, card3)))
        val givenGamer1 = Gamer(profile1, givenCards, Credit.from(100))
        val givenGamer2 = Gamer(profile2, givenCards, Credit.from(100))

        val actual = dealer.judge(listOf(givenGamer1, givenGamer2))

        assertThat(actual.players.getDealer()?.getPlayerCredit()).isEqualTo(Credit.from(-300))
        assertThat(actual.players.getPlayer(givenGamer1)?.getPlayerCredit()).isEqualTo(Credit.from(150))
        assertThat(actual.players.getPlayer(givenGamer2)?.getPlayerCredit()).isEqualTo(Credit.from(150))
    }

    @Test
    fun `게이머 딜러 둘다 블랙잭이면 배팅금액을 돌려받는다`() {
        val profile1 = Profile.from(Name("player1"))
        val profile2 = Profile.from(Name("player2"))
        val givenStatus = PlayerStatus.BURST
        val givenName = Name("dealer")
        val card1 = Card(Suit.HEART, Denomination.KING)
        val card2 = Card(Suit.CLUB, Denomination.ACE)
        val givenCards = Cards.from(listOf(card1, card2))
        val dealer = Dealer(Profile(givenName, givenStatus), givenCards)
        val givenGamer1 = Gamer(profile1, givenCards, Credit.from(100))
        val givenGamer2 = Gamer(profile2, givenCards, Credit.from(100))

        val actual = dealer.judge(listOf(givenGamer1, givenGamer2))

        assertThat(actual.players.getDealer()?.getPlayerCredit()).isEqualTo(Credit.from(0))
        assertThat(actual.players.getPlayer(givenGamer1)?.getPlayerCredit()).isEqualTo(Credit.from(100))
        assertThat(actual.players.getPlayer(givenGamer2)?.getPlayerCredit()).isEqualTo(Credit.from(100))
    }
}
