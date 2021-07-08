package model

import CardFixture
import game.BlackJackWinner
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class PlayerTest {
    @Test
    @DisplayName("플레이어는 카드 목록 가질 수 있다")
    fun `hasACards`() {
        val name = PlayerName("hello")
        val player = Player(name)
        val card = Card(Suit.CLUBS, Denomination.ACE)
        player.receive(card)
        assertThat(player.cards).hasSize(1)
    }

    @Test
    @DisplayName("플레이어는 이름을 가질 수 있다")
    fun `hasAName`() {
        val name = PlayerName("hello")
        val player = Player(name)
        assertThat(player.name).isEqualTo(name)
    }

    @Test
    @DisplayName("가지고 있는 카드의 스코어를 합산 된다")
    fun `scoreOfCard`() {
        val name = PlayerName("hello")
        var player = Player(name)
        val cards = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN))
        CardFixture.receiveCard(player, cards)
        assertThat(player.score()).isEqualTo(21)
    }

    @Test
    @DisplayName("가지고 있는 카드에 ACE 가 있으면 11점이 된다")
    fun `scoreOfCardAce`() {
        val name = PlayerName("hello")
        val player = Player(name)
        player.receive(Card(Suit.CLUBS, Denomination.ACE))
        assertThat(player.score()).isGreaterThanOrEqualTo(11)
    }

    @Test
    @DisplayName("player 가 21점 일 때 WIN 이다")
    fun `score21Winner`() {
        val player = Player("hello")
        val playerLoser = DealerPlayer(PlayerName("hello2"))
        val cards = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN))
        val cardLoser = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.EIGHT))
        CardFixture.receiveCard(player, cards)
        CardFixture.receiveCard(playerLoser, cardLoser)
        assertThat(player.compareResult(playerLoser)).isEqualTo(BlackJackWinner.WIN)
    }

    @Test
    @DisplayName("player 가 같은 점수 일 때 draw 이다")
    fun `scoreDraw`() {
        val player = Player("hello")
        val dealerPlayerDraw = DealerPlayer(PlayerName("hello2"))
        val cards = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN))
        val cardLoser = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN))
        CardFixture.receiveCard(player, cards)
        CardFixture.receiveCard(dealerPlayerDraw, cardLoser)
        assertThat(player.compareResult(dealerPlayerDraw)).isEqualTo(BlackJackWinner.DRAW)
    }

    @Test
    @DisplayName("player 가 낮은 점수 일 때 lose 이다")
    fun `scoreLoser`() {
        val dealerPlayer = DealerPlayer(PlayerName("hello"))
        val playerLoser = Player("hello2")
        val cards = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.SIX))
        val cardLoser = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.QUEEN))
        CardFixture.receiveCard(dealerPlayer, cards)
        CardFixture.receiveCard(playerLoser, cardLoser)
        assertThat(dealerPlayer.compareResult(playerLoser)).isEqualTo(BlackJackWinner.LOSE)
    }

    @Test
    @DisplayName("dealer 가 21점 이상이면 player 는 win 이다")
    fun `dealerScoreOver21`() {
        val player = Player("hello")
        val dealer = DealerPlayer()
        val cards = listOf(Card(Suit.CLUBS, Denomination.ACE), Card(Suit.CLUBS, Denomination.SIX))
        val cardDealer = CardFixture.score21OverCards()
        CardFixture.receiveCard(player, cards)
        CardFixture.receiveCard(dealer, cardDealer)
        assertThat(player.compareResult(dealer)).isEqualTo(BlackJackWinner.WIN)
    }


}
