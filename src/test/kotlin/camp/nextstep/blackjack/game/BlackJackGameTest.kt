package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

internal class BlackJackGameTest {

    @DisplayName("게임을 생성하면 카드 뭉치(Card Deck) 하나를 생성한다.")
    @Test
    fun newGame() {
        val newGame = BlackJackGame.new()

        assertThat(newGame.cardDeck.isNotEmpty).isTrue
    }

    @DisplayName("플레이어(Player)를 게임에 참여시킬 수 있다.")
    @Test
    fun participantPlayer() {
        val blackJackGame = BlackJackGame.new()
        val playerTim = Player("tim")
        val playerTom = Player("tom")

        blackJackGame.participate(playerTim)
        blackJackGame.participate(playerTom)

        val players = blackJackGame.participants

        assertThat(players.size).isEqualTo(2)
    }

    @DisplayName("게임이 시작되면 카드 뭉치를 섞는다.")
    @Test
    fun shuffleDeckWhenStartGame() {
        val blackJackGame = BlackJackGame.new()
        val beforeCards = blackJackGame.cardDeck.cards

        blackJackGame.initialize()

        val afterCards = blackJackGame.cardDeck.cards

        assertThat(afterCards).isNotEqualTo(beforeCards)
    }

    @DisplayName("게임이 시작되면 각 플레이어에게 카드 뭉치에서 카드를 2장씩 제공(Serving)한다.")
    @Test
    fun servingCardsWhenStartGame() {
        val blackJackGame = BlackJackGame.new()

        val playerTim = Player("tim")
        val playerTom = Player("tom")
        blackJackGame.participate(playerTim)
        blackJackGame.participate(playerTom)
        blackJackGame.initialize()

        val afterCards = blackJackGame.cardDeck.cards

        assertThat(afterCards.size).isEqualTo(52 - (2 * 2))
        assertThat(playerTim.cards.size).isEqualTo(2)
        assertThat(playerTom.cards.size).isEqualTo(2)
    }

    @DisplayName("게임이 초기화되면 참가한 순서대로 각 플레이어에 대한 Turn 을 받을 수 있다.")
    @Test
    fun turns() {
        val blackJackGame = BlackJackGame.new()

        val playerTim = Player("tim")
        val playerTom = Player("tom")
        blackJackGame.participate(playerTim)
        blackJackGame.participate(playerTom)
        blackJackGame.initialize()

        val timsTurn = blackJackGame.turns()[0]
        val tomsTurn = blackJackGame.turns()[1]

        assertThat(timsTurn.player).isEqualTo(playerTim)
        assertThat(tomsTurn.player).isEqualTo(playerTom)
    }

    @DisplayName("게임이 초기화되기 전에는 Turn 을 확인할 수 없다.")
    @Test
    fun cannotGetTurnsBeforeInitialize() {
        val blackJackGame = BlackJackGame.new()

        val playerTim = Player("tim")
        val playerTom = Player("tom")
        blackJackGame.participate(playerTim)
        blackJackGame.participate(playerTom)

        assertThrows<IllegalStateException> {
            blackJackGame.turns()
        }
    }

    @DisplayName("플레이어는 카드를 더 받을 수 있다. Hit")
    @Test
    fun playerCanHit() {
        val blackJackGame = BlackJackGame.new()

        val playerTim = Player("tim")
        blackJackGame.participate(playerTim)
        blackJackGame.initialize()

        val turn = blackJackGame.turns()[0]

        val beforeCards = playerTim.cards
        turn.applyToGame(Action.HIT)

        val afterCards = playerTim.cards
        assertThat(afterCards.size).isEqualTo(beforeCards.size + 1)
    }

    @DisplayName("플레이어는 카드를 더 받지 않을 수 있다. STAY")
    @Test
    fun playerCanStay() {
        val blackJackGame = BlackJackGame.new()

        val playerTim = Player("tim")
        blackJackGame.participate(playerTim)
        blackJackGame.initialize()

        val turn = blackJackGame.turns()[0]

        val beforeCards = playerTim.cards
        turn.applyToGame(Action.STAY)

        val afterCards = playerTim.cards
        assertThat(afterCards.size).isEqualTo(beforeCards.size)
    }

    @DisplayName("플레이어 카드가 Bust 이면 카드를 더 받을 수 없다.")
    @Test
    fun playerCannotHitWhenBusted() {
        val blackJackGame = BlackJackGame.new()

        val playerTim = Player("tim")
        blackJackGame.participate(playerTim)
        blackJackGame.initialize()

        val turn = blackJackGame.turns()[0]
        assertThat(turn.player).isEqualTo(playerTim)

        while (Score.of(playerTim.cards).isNotBust) {
            turn.applyToGame(Action.HIT)
        }

        assertThrows<IllegalStateException> {
            turn.applyToGame(Action.HIT)
        }
    }

    @DisplayName("각 플레이어별 점수를 확인할 수 있다.")
    @Test
    fun gameResult() {
        val blackJackGame = BlackJackGame.new()

        val playerTim = Player("tim")
        blackJackGame.participate(playerTim)
        blackJackGame.initialize()

        blackJackGame.turns().forEach { it.applyToGame(Action.STAY) }

        val result = blackJackGame.result()

        assertThat(result.playerScores.size).isEqualTo(1)

        val timsScore = Score.of(playerTim.cards)
        assertThat(result.playerScores.find { it.player == playerTim }!!.score).isEqualTo(timsScore)
    }
}
