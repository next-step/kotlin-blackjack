package blackjack.domain.game

import blackjack.domain.deck.Card
import blackjack.domain.deck.Denomination
import blackjack.domain.deck.Suit
import blackjack.domain.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameTest {

    @Test
    fun `이름에 맞게 유저를 생성한다`() {
        val playersName: List<String> = listOf("van", "van2")
        val game = Game(playersName)

        assertThat(game.players.size).isEqualTo(2)
        assertThat(game.players).contains(Player("van"))
    }

    @Test
    fun `게임을 시작하면 각 플레이어에게 두장씩 카드를 준다`() {
        val playersName: List<String> = listOf("van", "van2")
        val game = Game(playersName)

        game.start()
        for (player in game.players) {
            assertThat(player.cards.size).isEqualTo(2)
        }
    }

    @Test
    fun `블랙잭이 있는지 확인한다`() {
        val playersName: List<String> = listOf("van")
        val game = Game(playersName)
        val player = game.players[0]
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.ACE, Suit.CLOVER))

        assertThat(game.existBlackJack()).isTrue()
    }

    @Test
    fun `버스트됐으면 게임 불가`() {
        val playersName: List<String> = listOf("van")
        val game = Game(playersName)
        val player = game.players[0]
        player.addCard(Card(Denomination.TEN, Suit.CLOVER))
        player.addCard(Card(Denomination.TEN, Suit.HEART))
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))

        assertThat(game.isEnableContinue()).isFalse()
    }

    @Test
    fun `현재 플레이어에게 카드를 한장 추가`() {
        val playersName: List<String> = listOf("van")
        val game = Game(playersName)
        val player = game.players[0]
        player.addCard(Card(Denomination.TWO, Suit.CLOVER))

        game.draw()

        assertThat(player.cards.size).isEqualTo(2)
    }

    @Test
    fun `카드를 뽑을 추가 플레이어가 존재하는지 확인`() {
        val playersName: List<String> = listOf("van")
        val game = Game(playersName)


        assertThat(game.existNextPlayer()).isFalse()
    }

    @Test
    fun `플레이어를 변경`() {
        val playersName: List<String> = listOf("van", "van2")
        val game = Game(playersName)

        game.changeNextPlayer()

        assertThat(game.turnPlayer.name).isEqualTo("van2")
    }

    @Test
    fun `다음 선수가없는데 플레이어 변경 시 플레이어 턴 종료`() {
        val playersName: List<String> = listOf("van")
        val game = Game(playersName)

        game.changeNextPlayer()

        assertThat(game.isEndPlayerTurn).isTrue()
    }

    @Test
    fun `마지막 선수인지 확인한다`() {
        val playersName: List<String> = listOf("van")
        val game = Game(playersName)

        assertThat(game.isLastPlayer()).isTrue()
    }

    @Test
    fun `게임을 종료한다`() {
        val playersName: List<String> = listOf("van")
        val game = Game(playersName)

        game.endPlayerTurn()

        assertThat(game.isEndPlayerTurn).isTrue()
    }

    @Test
    fun `dealer의 숫자가 17보다 작으면 패를 한장 추가한다`() {
        val playersName: List<String> = listOf("van")
        val game = Game(playersName)
        game.dealer.addCard(Card(Denomination.TEN, Suit.CLOVER))
        game.dealer.addCard(Card(Denomination.TWO, Suit.CLOVER))

        game.playDealerTurn()

        assertThat(game.dealer.score()).isGreaterThan(12)
    }
}
