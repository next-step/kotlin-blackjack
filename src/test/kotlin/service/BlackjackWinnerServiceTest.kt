package service

import domain.Card
import domain.CardValue
import domain.Dealer
import domain.Player
import domain.Suit
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class BlackjackWinnerServiceTest {
    private val blackjackWinnerService = BlackjackWinnerService()

    @Test
    @DisplayName("딜러보다 높은 점수를 가진 플레이어가 승리한다.")
    fun winnerTest() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEART, CardValue.TEN))
        dealer.receiveCard(Card(Suit.HEART, CardValue.THREE))

        // when
        val player = Player("A")
        player.receiveCard(Card(Suit.SPADE, CardValue.NINE))
        player.receiveCard(Card(Suit.DIAMOND, CardValue.NINE))

        val player2 = Player("B")
        player2.receiveCard(Card(Suit.HEART, CardValue.TWO))
        player2.receiveCard(Card(Suit.CLUB, CardValue.TWO))

        val players = setOf(player, player2)

        // when
        val winner = blackjackWinnerService.winner(players, dealer)

        // then
        assertThat(winner[player]).isTrue()
        assertThat(winner[player2]).isFalse()
    }

    @Test
    @DisplayName("플레이어가 21 초과 시 플레이어가 진다.")
    fun winnerTest2() {
        // given
        val dealer = Dealer()
        dealer.receiveCard(Card(Suit.HEART, CardValue.TWO))
        dealer.receiveCard(Card(Suit.HEART, CardValue.TEN))

        // when
        val player = Player("A")
        player.receiveCard(Card(Suit.SPADE, CardValue.NINE))
        player.receiveCard(Card(Suit.DIAMOND, CardValue.NINE))
        player.receiveCard(Card(Suit.CLUB, CardValue.NINE))

        val players = setOf(player)

        // when
        val winner = blackjackWinnerService.winner(players, dealer)

        // then
        assertThat(winner[player]).isFalse()
    }
}
