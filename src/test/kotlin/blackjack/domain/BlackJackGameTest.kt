package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class BlackJackGameTest {

    @Test
    fun `askPlayerHit() player에게 카드를 더 받을지 물어본다`() {
        val player = Player("robin")
        val game = BlackJackGame(listOf(player), DEFAULT_DECK)
        assertThat(
            game.askPlayerHit(player) { true }
        ).isTrue()
        assertThat(
            game.askPlayerHit(player) { false }
        ).isFalse()
    }

    @Test
    fun `hitPlayer() 플레이어에게 카드를 한장 준다`() {
        val player = Player("robin")
        val deck = Deck(listOf(Card.CLUB_ACE))
        val game = BlackJackGame(listOf(player), DEFAULT_DECK)
        game.hitPlayer(player, deck)

        assertThat(player.hands).isEqualTo(Hands(listOf(Card.CLUB_ACE)))
    }

    @Test
    fun `startGame() 플레이어는 최소한 1명 이상 있어야 한다`() {
        assertThatThrownBy {
            BlackJackGame(listOf(), DEFAULT_DECK)
        }.isInstanceOf(RuntimeException::class.java)
    }

    @Test
    fun `startWithTwoCards() 플레이어에게 기본으로 2장씩 카드를 나누어 준다`() {
        BlackJackGame(DEFAULT_PLAYERS, DEFAULT_DECK).startWithTwoCards()

        assertThat(DEFAULT_PLAYERS).allSatisfy {
            assertThat(it.hands.size).isEqualTo(2)
        }
    }

    @Test
    fun `startGame() 플레이어에게 번갈아 가며 카드를 받을 것인지 물어보고 카드를 받겠다고 하면 플레이어는 카드를 받는다`() {
        var switch = false
        BlackJackGame(DEFAULT_PLAYERS, DEFAULT_DECK).startGame {
            switch = switch.not()
            switch
        }

        assertThat(DEFAULT_PLAYERS).allSatisfy {
            assertThat(it.hands.size).isEqualTo(1)
        }
    }

    @Test
    fun `startGame() 만약 플레이어가 카드를 받았는데 패가 Busted되면 더 이상 카드를 주지 않는다`() {
        BlackJackGame(DEFAULT_PLAYERS, BUSTED_DECK).startGame { true }

        assertThat(DEFAULT_PLAYERS).allSatisfy {
            assertThat(it.hands.isBusted()).isTrue()
        }
    }

    @Test
    fun `startGame() 최종결과를 BlackJackResult으로 반환한다`() {
        val result = BlackJackGame(DEFAULT_PLAYERS, DEFAULT_DECK).startGame { false }

        assertThat(result).isInstanceOf(BlackJackResult::class.java)
    }

    companion object {
        private val DEFAULT_DECK get() = Deck(Card.PACK)
        private val BUSTED_DECK = Deck(
            listOf(
                Card.SPADE_10,
                Card.SPADE_10,
                Card.SPADE_10,
                Card.SPADE_10,
                Card.SPADE_10,
                Card.SPADE_10,
                Card.SPADE_10,
                Card.SPADE_10,
                Card.SPADE_10
            )
        )
        private val DEFAULT_PLAYERS = listOf(
            Player("robin"),
            Player("todd"),
            Player("kanz")
        )
    }
}
