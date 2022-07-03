package domain

import domain.BlackJackGame.calculateRevenue
import domain.BlackJackGame.computeWinner
import domain.BlackJackGame.endCheck
import domain.BlackJackGame.isBust
import io.kotest.matchers.collections.shouldNotContainAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @Test
    fun `플레이어가 발급받은 카드는 카드덱에서 제외되어야 한다`() {
        val player1 = Player("keira")
        val player2 = Player("tyrus")
        var players = listOf<Player>(player1, player2)

        BlackJackGame.setInitialCards(players)

        players.forEach {
            it.cards.shouldNotContainAll(CardDeck.allCards)
        }
    }

    @Test
    fun `플레이어는 카드를 안받기로 선택하면 카드를 받지 않는다`() {
        val player = Player("keira")

        isBust(player, false)

        assertThat(player.cards).isEmpty()
    }

    @Test
    fun `플레이어가 가진 카드의 합계가 21이상이면 즉시 종료한다`() {
        val player = Player("keira")

        val cards = listOf(
            Card(Card.CardSuit.CLUB, Card.CardDenomination.JACK),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.KING),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TEN)
        )
        player.offer(cards)

        val isBust = isBust(player, true)
        assertThat(endCheck(1, 1, isBust)).isFalse()
    }

    @Test
    fun `21점이 초과한 플레이어는 승자가 될 수 없다`() {
        val player1 = Player("keira")
        val player2 = Player("tyrus")

        val cardsForPlayer1 = listOf(
            Card(Card.CardSuit.CLUB, Card.CardDenomination.JACK),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.KING),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TEN)
        )
        val cardsForPlayer2 = listOf(
            Card(Card.CardSuit.CLUB, Card.CardDenomination.ACE),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TWO),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TEN)
        )

        player1.offer(cardsForPlayer1)
        player2.offer(cardsForPlayer2)

        var players = listOf(player1, player2)

        computeWinner(players)

        val winner = players.find { it.match == Match.WIN }
        val loser = players.find { it.match == Match.LOSE }

        assertThat(winner).isEqualTo(player2)
        assertThat(loser).isEqualTo(player1)
    }

    @Test
    fun `21점 이하의 점수 중 가장 큰 점수를 가진 플레이어가 승리한다`() {
        val player1 = Player("keira")
        val player2 = Player("tyrus")

        val cardsForPlayer1 = listOf(
            Card(Card.CardSuit.HEART, Card.CardDenomination.ACE),
            Card(Card.CardSuit.HEART, Card.CardDenomination.TWO),
            Card(Card.CardSuit.HEART, Card.CardDenomination.NINE)
        )
        val cardsForPlayer2 = listOf(
            Card(Card.CardSuit.CLUB, Card.CardDenomination.ACE),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TWO),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TEN)
        )

        player1.offer(cardsForPlayer1)
        player2.offer(cardsForPlayer2)

        var players = listOf(player1, player2)

        computeWinner(players)

        val winner = players.find { it.match == Match.WIN }
        val loser = players.find { it.match == Match.LOSE }

        assertThat(winner).isEqualTo(player2)
        assertThat(loser).isEqualTo(player1)
    }

    @Test
    fun `딜러가 21점을 초과하면 플레이어는 배팅금액을 돌려받는다`() {
        val player1 = Player("keira")
        val player2 = Player("tyrus")
        val dealer = Player("딜러")

        val cardsForPlayer1 = listOf(
            Card(Card.CardSuit.HEART, Card.CardDenomination.TEN),
            Card(Card.CardSuit.HEART, Card.CardDenomination.TWO),
            Card(Card.CardSuit.HEART, Card.CardDenomination.NINE)
        )
        val cardsForPlayer2 = listOf(
            Card(Card.CardSuit.CLUB, Card.CardDenomination.ACE),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TWO),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TEN)
        )
        val cardForDealer = listOf(
            Card(Card.CardSuit.SPADE, Card.CardDenomination.TEN),
            Card(Card.CardSuit.SPADE, Card.CardDenomination.THREE),
            Card(Card.CardSuit.SPADE, Card.CardDenomination.NINE)
        )

        player1.battingAmount = 1000
        player1.offer(cardsForPlayer1)

        player2.battingAmount = 2000
        player2.offer(cardsForPlayer2)

        dealer.offer(cardForDealer)

        var players = listOf(player1, player2, dealer)
        calculateRevenue(players)

        assertThat(player1.revenue).isEqualTo(1000)
        assertThat(player2.revenue).isEqualTo(2000)
        assertThat(dealer.revenue).isEqualTo(0)
    }

    @Test
    fun `처음 두 장의 카드 합이 21일 경우 블랙잭이 되면 베팅 금액의 1*5배를 딜러에게 받는다`() {
        val player1 = Player("keira")
        val player2 = Player("tyrus")
        val dealer = Player("딜러")

        val cardsForPlayer1 = listOf(
            Card(Card.CardSuit.HEART, Card.CardDenomination.TEN),
            Card(Card.CardSuit.HEART, Card.CardDenomination.ACE),
            Card(Card.CardSuit.HEART, Card.CardDenomination.NINE)
        )
        val cardsForPlayer2 = listOf(
            Card(Card.CardSuit.CLUB, Card.CardDenomination.ACE),
            Card(Card.CardSuit.CLUB, Card.CardDenomination.TEN)
        )
        val cardForDealer = listOf(
            Card(Card.CardSuit.SPADE, Card.CardDenomination.TWO),
            Card(Card.CardSuit.SPADE, Card.CardDenomination.THREE),
            Card(Card.CardSuit.SPADE, Card.CardDenomination.NINE)
        )

        player1.battingAmount = 1000
        player1.offer(cardsForPlayer1)

        player2.battingAmount = 2000
        player2.offer(cardsForPlayer2)

        dealer.offer(cardForDealer)

        var players = listOf(player1, player2, dealer)
        calculateRevenue(players)

        assertThat(player1.revenue).isEqualTo(0)
        assertThat(player2.revenue).isEqualTo(3000)
        assertThat(dealer.revenue).isEqualTo(0)
    }
}
