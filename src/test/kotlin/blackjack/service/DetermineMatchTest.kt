package blackjack.service

import blackjack.domain.card.Card
import blackjack.domain.card.CardDenomination
import blackjack.domain.card.CardPattern
import blackjack.domain.player.Dealer
import blackjack.domain.player.Participant
import blackjack.domain.player.Player
import blackjack.domain.player.ResultStatus
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class DetermineMatchTest {

    private val determineMatch = DetermineMatch()

    @Test
    fun `현재 가지고 있는 카드가 21을 초과할때 모두 패배 한다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.TEN))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
            addCard(Card(CardPattern.HEART, CardDenomination.KING))
        }

        val players = listOf(
            Player(Participant("one"), 0),
            Player(Participant("two"), 0)
        )

        determineMatch.match(dealer, players)

        val actual = dealer.getMatchResult()

        assertEquals(2, actual[ResultStatus.LOSE])
    }

    @Test
    fun `현재 가지고 있는 카드가 플레이어의 카드보다 클경우 승리한다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.ACE))
            addCard(Card(CardPattern.DIAMOND, CardDenomination.KING))
            addCard(Card(CardPattern.HEART, CardDenomination.KING))
        }

        val players = listOf(Player(Participant("one"), 0))

        determineMatch.match(dealer, players)

        val actual = dealer.getMatchResult()

        assertEquals(1, actual[ResultStatus.WIN])
    }

    @Test
    fun `현재 가지고 있는 카드가 플레이어의 카드보다 작을경우 패배한다`() {
        val dealer = Dealer(Participant("딜러")).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.TWO))
        }

        val player = Player(Participant("one"), 0).apply {
            addCard(Card(CardPattern.DIAMOND, CardDenomination.THREE))
        }

        val players = listOf(player)

        determineMatch.match(dealer, players)

        val actual = dealer.getMatchResult()

        assertEquals(1, actual[ResultStatus.LOSE])
    }

    @Test
    fun `딜러의 카드합이 18, 플레이어1의 카드합이 19, 플레이어2의 카드합이 17, 플레이어3의 카드합이 18일때 1승 1패 1무`() {
        val dealer = buildDealer()
        val players = buildPlayers()

        determineMatch.match(dealer, players)

        val matchResult = dealer.getMatchResult()

        assertEquals(1, matchResult[ResultStatus.WIN])
        assertEquals(1, matchResult[ResultStatus.LOSE])
        assertEquals(1, matchResult[ResultStatus.TIE])

        assertEquals(ResultStatus.WIN, players[0].resultStatus)
        assertEquals(ResultStatus.LOSE, players[1].resultStatus)
        assertEquals(ResultStatus.TIE, players[2].resultStatus)
    }

    private fun buildDealer(): Dealer {
        return Dealer(Participant("딜러")).apply {
            addCard(Card(pattern = CardPattern.HEART, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.HEART, denomination = CardDenomination.EIGHT))
        }
    }

    private fun buildPlayers(): List<Player> {
        val one = Player(Participant("one"), 0).apply {
            addCard(Card(pattern = CardPattern.DIAMOND, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.DIAMOND, denomination = CardDenomination.NINE))
        }

        val two = Player(Participant("one"), 0).apply {
            addCard(Card(pattern = CardPattern.SPADE, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.SPADE, denomination = CardDenomination.SEVEN))
        }

        val three = Player(Participant("one"), 0).apply {
            addCard(Card(pattern = CardPattern.CLOVER, denomination = CardDenomination.TEN))
            addCard(Card(pattern = CardPattern.CLOVER, denomination = CardDenomination.EIGHT))
        }
        return listOf(one, two, three)
    }
}
