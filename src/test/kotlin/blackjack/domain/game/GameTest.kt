package blackjack.domain.game

import blackjack.domain.deck.Card
import blackjack.domain.deck.Deck
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.util.*

class GameTest {

    @Test
    fun `이름에 맞게 유저를 생성한다`() {
        val game = createGameFixture()

        assertThat(game.playersInGame.size).isEqualTo(3)
        assertThat(game.playersInGame).contains(Player("van", 10000))
    }

    private fun createGameFixture(): Game {
        val cards = Stack<Card>()
        cards.push(Card(Denomination.TWO, Suit.CLOVER))
        cards.push(Card(Denomination.THREE, Suit.CLOVER))
        cards.push(Card(Denomination.FOUR, Suit.CLOVER))
        cards.push(Card(Denomination.FIVE, Suit.CLOVER))
        cards.push(Card(Denomination.SIX, Suit.CLOVER))
        cards.push(Card(Denomination.SEVEN, Suit.CLOVER))
        cards.push(Card(Denomination.EIGHT, Suit.CLOVER))
        cards.push(Card(Denomination.NINE, Suit.CLOVER))
        cards.push(Card(Denomination.TEN, Suit.CLOVER))
        cards.push(Card(Denomination.ACE, Suit.CLOVER))
        return Game(listOf(Player("van", 10000), Player("van2", 10000), Player("van3", 10000)), Deck(cards))
    }


    @Test
    fun `게임을 시작하면 각 플레이어에게 두장씩 카드를 준다`() {
        val game = createGameFixture()

        game.start()
        for (player in game.playersInGame) {
            assertThat(player.cards.size).isEqualTo(2)
        }
    }

    @Test
    fun `버스트됐으면 게임 불가`() {
        val game = Game(listOf(Player("van", 10000)), Deck.createDeck())
        val player = game.playersInGame[0]
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))

        assertThat(game.isEnableContinue()).isFalse()
    }

    @Test
    fun `dealer의 숫자가 17보다 작으면 패를 한장 추가한다`() {
        val game = createGameFixture()
        game.dealer.addCard(Card(Denomination.TEN, Suit.CLOVER))
        game.dealer.addCard(Card(Denomination.TWO, Suit.CLOVER))

        game.playDealerTurn()

        assertThat(game.dealer.score()).isGreaterThan(12)
    }

    @Test
    internal fun `플레이어 목록으로 게임을 생성할 수 있다`() {
        val players = listOf(Player("john", 10000), Player("jane", 20000))
        val game = Game(players, Deck.createDeck())

        assertThat(game.playersInGame).isEqualTo(players)
    }

    @Test
    internal fun `draw를 원한다면 진행`() {
        val game = createGameFixture()
        game.playOneStep(additionalDraw = { true }, forEachStep = { {} })

        assertThat(game.playersInGame[0].cards.size).isEqualTo(1)
    }

    @Test
    internal fun `draw가 끝나면 게임중인 인원에서 제외된다`() {
        val game = createGameFixture()
        val player = Player("van", 10000)
        game.playOneStep(additionalDraw = { false }, forEachStep = { {} })

        assertThat(game.playersInGame).doesNotContain(player)
    }

//    playersInPlay/playersOutOfGame

    @Test
    internal fun `블랙잭인 플레이어는 게임중인 인원에 포함되지 않는다`() {
        val game = createGameFixture()
        val player = Player("van", 10000)

        game.start()

        assertThat(game.playersOutOfGame).contains(player)
    }


    @Test
    internal fun `블랙잭인 플레이어는 인게임인 플레이어에서 제외되어야 한다`() {
        val game = createGameFixture()
        val player = Player("van", 10000)

        game.start()

        assertThat(game.playersInGame).doesNotContain(player)
    }

    @Test
    fun `플레이어턴이 끝날때 마지막 플레이어도 인게임 플레이어에서 제외된다`() {
        val game = createGameFixture()

        game.playOneStep(additionalDraw = { false }, forEachStep = { {} })
        game.playOneStep(additionalDraw = { false }, forEachStep = { {} })
        game.playOneStep(additionalDraw = { false }, forEachStep = { {} })

        assertThat(game.playersInGame.size).isEqualTo(0)
    }
}
