package blackjack.domain.game

import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Denomination
import blackjack.domain.card.Suit
import blackjack.domain.player.Dealer
import blackjack.domain.player.Gamer
import blackjack.domain.player.Name
import blackjack.domain.player.Players
import blackjack.domain.player.Profile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.Test

internal class RuleTest {

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
        val players = Players(listOf(givenGamer1, givenGamer2, dealer))
        val rule = Rule()

        val actual = rule.judge(players)

        assertAll(
            { assertThat(actual[dealer]).contains(Score.WIN) },
            { assertThat(actual[givenGamer1]).contains(Score.LOSE) },
            { assertThat(actual[givenGamer2]).contains(Score.LOSE) }
        )
    }
}
