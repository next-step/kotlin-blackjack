package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.player.Gambler
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

    @DisplayName("배팅하는 플레이어(Gambler)를 게임에 참여시킬 수 있다.")
    @Test
    fun participantGambler() {
        val gamblers = listOf(
            Gambler("tim"),
            Gambler("tom")
        )

        val blackJackGame = BlackJackGame.new(gamblers)

        val participants = blackJackGame.participants

        assertThat(participants).hasSize(2)
    }

    @DisplayName("게임이 시작되면 딜러가 각 플레이어와 자신에게 카드 뭉치에서 카드를 2장씩 제공(Serving)한다.")
    @Test
    fun servingCardsWhenStartGame() {
        val gamblerTim = Gambler("tim")
        val gamblerTom = Gambler("tom")
        val gamblers = listOf(gamblerTim, gamblerTom)

        val blackJackGame = BlackJackGame.new(gamblers)

        val afterCards = blackJackGame.cardDeck.cards

        assertThat(afterCards).hasSize(52 - (2 * 3))
        assertThat(gamblerTim.hand.cards).hasSize(2)
        assertThat(gamblerTom.hand.cards).hasSize(2)
        assertThat(blackJackGame.dealer.hand.cards).hasSize(2)
    }

    @DisplayName("게임이 초기화되면 참가한 순서대로 각 플레이어에 대한 Turn 을 받을 수 있다.")
    @Test
    fun turns() {
        val gamblerTim = Gambler("tim")
        val gamblerTom = Gambler("tom")
        val gamblers = listOf(gamblerTim, gamblerTom)

        val blackJackGame = BlackJackGame.new(gamblers)

        val timsTurn = blackJackGame.turns[0]
        val tomsTurn = blackJackGame.turns[1]

        assertThat(timsTurn.gambler).isEqualTo(gamblerTim)
        assertThat(tomsTurn.gambler).isEqualTo(gamblerTom)
    }

    @DisplayName("플레이어는 카드를 더 받을 수 있다. Hit")
    @Test
    fun gamblerCanHit() {
        val gamblerTim = Gambler("tim")
        val gamblers = listOf(gamblerTim)

        val blackJackGame = BlackJackGame.new(gamblers)

        val turn = blackJackGame.turns[0]

        val beforeCards = gamblerTim.hand.cards
        turn.applyToGame(Action.HIT)

        val afterCards = gamblerTim.hand.cards
        assertThat(afterCards).hasSize(beforeCards.size + 1)
    }

    @DisplayName("플레이어는 카드를 더 받지 않을 수 있다. STAY")
    @Test
    fun gamblerCanStay() {
        val gamblerTim = Gambler("tim")
        val gamblers = listOf(gamblerTim)

        val blackJackGame = BlackJackGame.new(gamblers)

        val turn = blackJackGame.turns[0]

        val beforeCards = gamblerTim.hand.cards
        turn.applyToGame(Action.STAY)

        val afterCards = gamblerTim.hand.cards
        assertThat(afterCards).hasSize(beforeCards.size)
    }

    @DisplayName("플레이어 카드가 Bust 이면 카드를 더 받을 수 없다.")
    @Test
    fun gamblerCannotHitWhenBusted() {
        val gamblerTim = Gambler("tim")
        val gamblers = listOf(gamblerTim)

        val blackJackGame = BlackJackGame.new(gamblers)

        val turn = blackJackGame.turns[0]
        assertThat(turn.gambler).isEqualTo(gamblerTim)

        while (Score.of(gamblerTim.hand).isNotBust()) {
            turn.applyToGame(Action.HIT)
        }

        assertThrows<IllegalStateException> {
            turn.applyToGame(Action.HIT)
        }
    }

    @DisplayName("각 플레이어별 점수를 확인할 수 있다.")
    @Test
    fun gameResult() {
        val gamblerTim = Gambler("tim")
        val gamblers = listOf(gamblerTim)

        val blackJackGame = BlackJackGame.new(gamblers)

        blackJackGame.turns.forEach { it.applyToGame(Action.STAY) }

        val result = blackJackGame.result()

        assertThat(result.gamblersScore).hasSize(1)

        val timsScore = Score.of(gamblerTim.hand)
        assertThat(result.gamblersScore.find { it.gambler == gamblerTim }!!.score).isEqualTo(timsScore)
    }
}
