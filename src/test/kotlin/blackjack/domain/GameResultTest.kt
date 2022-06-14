package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class GameResultTest {
    @Test
    fun `참가자 배팅 10000, 딜러가 bust`() {
        val dealer = Dealer("딜러")
        val players = Player("molly", 10000)

        val gameResult = GameResult(dealer, listOf(players))

        gameResult.dealerIsBust(players)

        assertThat(dealer.earnAmount).isEqualTo(-10000)
    }

    @Test
    fun `참가자 배팅 10000 플레이어가 bust`() {
        val dealer = Dealer("딜러")
        val players = Player("molly", 10000)

        val gameResult = GameResult(dealer, listOf(players))

        gameResult.playerIsBust(players)

        assertThat(dealer.earnAmount).isEqualTo(10000)
    }

    @Test
    fun `플레이어가 10000원 배팅에 점수 12점, 딜러가 18점`() {
        val dealer = Dealer("딜러")
        val players = Player("molly", 10000)

        val blackjack =
            BlackJackGame.of(dealer, listOf(players), MockCardDeck(Card(Card.CardPattern.CLUBS, Card.Denomination.SIX)))
        blackjack.firstCardDistribution()
        blackjack.drawTo("딜러")

        val gameResult = GameResult(dealer, listOf(players))

        gameResult.decideWinner(players)

        assertThat(dealer.earnAmount).isEqualTo(10000)
    }

    @Test
    fun `플레이어 3명 모두 동점일 때`() {
        val playerNames = listOf("molly", "jayce")
        val dealer = Dealer("딜러")
        val players = playerNames.map { Player(it, 10000) }

        val blackJackGame =
            BlackJackGame.of(dealer, players, MockCardDeck(Card(Card.CardPattern.CLUBS, Card.Denomination.EIGHT)))

        blackJackGame.firstCardDistribution()

        val gameResult = GameResult(dealer, players)

        players.forEach {
            gameResult.decideWinner(it)
        }

        assertThat(dealer.earnAmount).isEqualTo(0)
        assertThat(players[0].earnAmount).isEqualTo(10000)
        assertThat(players[1].earnAmount).isEqualTo(10000)
    }

    @Test
    fun `플레이어가 블랙잭일때`() {
        val dealer = Dealer("딜러")
        val player = Player("molly", 10000)

        val blackJackGame =
            BlackJackGame.of(
                dealer,
                listOf(player),
                MockCardListDeck(
                    listOf(
                        Card(Card.CardPattern.CLUBS, Card.Denomination.JACK),
                        Card(Card.CardPattern.CLUBS, Card.Denomination.SEVEN),
                        Card(Card.CardPattern.CLUBS, Card.Denomination.ACE),
                        Card(Card.CardPattern.CLUBS, Card.Denomination.KING),
                        Card(Card.CardPattern.CLUBS, Card.Denomination.THREE),
                    )
                )
            )

        blackJackGame.firstCardDistribution()

        val gameResult = GameResult(dealer, listOf(player))

        gameResult.matchParticipantsIsBlackJack(player)

        assertThat(dealer.earnAmount).isEqualTo(-5000)
        assertThat(player.earnAmount).isEqualTo(15000)
    }

    @Test
    fun `플레이어, 딜러 모두 블랙잭일때`() {
        val dealer = Dealer("딜러")
        val player = Player("molly", 10000)

        val blackJackGame =
            BlackJackGame.of(
                dealer,
                listOf(player),
                MockCardListDeck(
                    listOf(
                        Card(Card.CardPattern.HEARTS, Card.Denomination.ACE),
                        Card(Card.CardPattern.DIAMONDS, Card.Denomination.KING),
                        Card(Card.CardPattern.CLUBS, Card.Denomination.ACE),
                        Card(Card.CardPattern.CLUBS, Card.Denomination.KING),
                        Card(Card.CardPattern.CLUBS, Card.Denomination.THREE),
                    )
                )
            )

        blackJackGame.firstCardDistribution()

        val gameResult = GameResult(dealer, listOf(player))

        gameResult.matchParticipantsIsBlackJack(player)

        assertThat(dealer.earnAmount).isEqualTo(0)
        assertThat(player.earnAmount).isEqualTo(10000)
    }
}
