package camp.nextstep.blackjack.game

import camp.nextstep.blackjack.player.Player
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

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

        blackJackGame.start()

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
        blackJackGame.start()

        val afterCards = blackJackGame.cardDeck.cards

        assertThat(afterCards.size).isEqualTo(52 - (2 * 2))
        assertThat(playerTim.cards.size).isEqualTo(2)
        assertThat(playerTom.cards.size).isEqualTo(2)
    }
}
