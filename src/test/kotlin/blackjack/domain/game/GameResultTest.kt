package blackjack.domain.game

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameResultTest {
    @Test
    fun `딜러의 수익금은 모든 플레이어 수익금 -하면 됨`() {
        val player = Player("van", 20000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        player.addCard(Card(Denomination.FIVE, Suit.CLOVER))
        val player2 = Player("van2", 10000)
        player2.addCard(Card(Denomination.TEN, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.HEART))
        player2.addCard(Card(Denomination.FIVE, Suit.CLOVER))
        val game = Game(listOf(player, player2), Deck.createDeck())
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        game.dealer.addCard(Card(Denomination.FOUR, Suit.CLOVER))
        game.dealer.addCard(Card(Denomination.FIVE, Suit.CLOVER))
        game.playOneStep({ false }, {})
        game.playOneStep({ false }, {})

        assertThat(GameResult(game).getDealerProfit()).isEqualTo(10000.0, Offset.offset(0.01))
    }

    @Test
    internal fun `딜러가 블랙잭이 아닌경우 블랙잭인 플레이어는 베팅금액의 150%를 수익으로 갖는다`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.ACE, Suit.CLOVER))
        val game = Game(listOf(player), Deck.createDeck())

        assertThat(GameResult(game).calculatePlayerProfitFor(player)).isEqualTo(15000.0, Offset.offset(0.01))
    }

    @Test
    internal fun `딜러가 블랙잭인 경우 블랙잭인 플레이어는 베팅금액의 100%를 수익으로 갖는다`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.ACE, Suit.CLOVER))
        val game = Game(listOf(player), Deck.createDeck())
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        game.dealer.addCard(Card(Denomination.ACE, Suit.CLOVER))

        assertThat(GameResult(game).calculatePlayerProfitFor(player)).isEqualTo(10000.0, Offset.offset(0.01))
    }

    @Test
    internal fun `플레이어가 버스트인 경우 플레이어는 베팅금액의 -100%를 수익으로 갖는다`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        val game = Game(listOf(player), Deck.createDeck())
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        game.dealer.addCard(Card(Denomination.ACE, Suit.CLOVER))

        assertThat(GameResult(game).calculatePlayerProfitFor(player)).isEqualTo(-10000.0, Offset.offset(0.01))
    }

    @Test
    internal fun `딜러가 버스트이고 플레이어가 버스트가 아닌 경우 플레이어는 베팅금액의 100%를 수익으로 갖는다`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.NINE, Suit.HEART))
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        val game = Game(listOf(player), Deck.createDeck())
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))

        assertThat(GameResult(game).calculatePlayerProfitFor(player)).isEqualTo(10000.0, Offset.offset(0.01))
    }

    @Test
    internal fun `딜러와 플레이어 둘 다 버스트가 아닌 경우, 플레이어의 점수가 더 높으면 플레이어가 베팅금액의 100%를 수익으로 갖는다`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.SEVEN, Suit.CLOVER))
        val game = Game(listOf(player), Deck.createDeck())
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        game.dealer.addCard(Card(Denomination.TWO, Suit.CLOVER))

        assertThat(GameResult(game).calculatePlayerProfitFor(player)).isEqualTo(10000.0, Offset.offset(0.01))
    }

    @Test
    internal fun `딜러와 플레이어 둘 다 버스트가 아닌 경우, 딜러의 점수가 더 높으면 플레이어가 베팅금액의 -100%를 수익으로 갖는다`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        val game = Game(listOf(player), Deck.createDeck())
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        game.dealer.addCard(Card(Denomination.SEVEN, Suit.CLOVER))

        assertThat(GameResult(game).calculatePlayerProfitFor(player)).isEqualTo(-10000.0, Offset.offset(0.01))
    }

    @Test
    internal fun `참가자가 아닌 사람이 수익금확인시 에러`() {
        val player = Player("van", 10000)
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))
        val game = Game(listOf(player), Deck.createDeck())
        game.dealer.addCard(Card(Denomination.TEN, Suit.HEART))
        game.dealer.addCard(Card(Denomination.SEVEN, Suit.CLOVER))
        val playerNotInGame = Player("jinwoo", 5000)
        game.playOneStep({ false }, {})

        assertThrows<IllegalArgumentException> {
            GameResult(game).calculatePlayerProfitFor(playerNotInGame)
        }
    }

}
