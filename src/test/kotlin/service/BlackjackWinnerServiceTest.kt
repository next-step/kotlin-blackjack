package service

import domain.bet.BetMoney
import domain.card.Card
import domain.card.CardValue
import domain.card.Suit
import domain.participant.Dealer
import domain.participant.Player
import domain.participant.WinType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlackjackWinnerServiceTest {
    private val blackjackWinnerService = BlackjackWinnerService()

    @Test
    @DisplayName("딜러보다 높은 점수를 가진 플레이어가 승리한다.")
    fun determineWinnerTest() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEART, CardValue.TEN))
        dealer.receiveCard(Card(Suit.HEART, CardValue.THREE))

        // when
        val player = Player("A", BetMoney(10000))
        player.receiveCard(Card(Suit.SPADE, CardValue.NINE))
        player.receiveCard(Card(Suit.DIAMOND, CardValue.NINE))

        val player2 = Player("B", BetMoney(10000))
        player2.receiveCard(Card(Suit.HEART, CardValue.TWO))
        player2.receiveCard(Card(Suit.CLUB, CardValue.TWO))

        val player3 = Player("C", BetMoney(10000))
        player3.receiveCard(Card(Suit.CLUB, CardValue.TEN))
        player3.receiveCard(Card(Suit.CLUB, CardValue.THREE))

        val players = listOf(player, player2, player3)

        // when
        val winner = blackjackWinnerService.determineWinner(players, dealer)

        // then
        assertThat(winner[player]).isEqualTo(WinType.WIN)
        assertThat(winner[player2]).isEqualTo(WinType.LOSE)
        assertThat(winner[player3]).isEqualTo(WinType.DRAW)
    }

    @Test
    @DisplayName("플레이어가 21 초과 시 플레이어가 진다.")
    fun determineWinnerTest2() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEART, CardValue.TWO))
        dealer.receiveCard(Card(Suit.HEART, CardValue.TEN))

        // when
        val player = Player("A", BetMoney(10000))
        player.receiveCard(Card(Suit.SPADE, CardValue.NINE))
        player.receiveCard(Card(Suit.DIAMOND, CardValue.NINE))
        player.receiveCard(Card(Suit.CLUB, CardValue.NINE))

        val players = listOf(player)

        // when
        val winner = blackjackWinnerService.determineWinner(players, dealer)

        // then
        assertThat(winner[player]).isEqualTo(WinType.LOSE)
    }
}
