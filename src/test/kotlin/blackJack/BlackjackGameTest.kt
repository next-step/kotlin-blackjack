package blackJack

import BlackjackGame
import card.CardPack
import card.deck.GameDeck
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import player.Player
import player.PlayerGroup

class BlackjackGameTest {

    private val defaultPlayers = PlayerGroup(listOf(Player("OYJ"), Player("OYJ")))

    @Test
    fun `정상적인 카드팩을 받고, 게임에 주입되었을 때, 예외 검증을 통과한다`() {
        // given : 정상적인 카드팩을 받는다.
        val cardDeck = GameDeck.create(CardPack.cards)

        // when : 게임에 카드를 주입한다.
        val actual = runCatching { BlackjackGame(cardDeck, defaultPlayers) }.exceptionOrNull()

        // then : 예외를 던지지 않는다.
        assertThat(actual).isNull()
    }

    @Test
    fun `플레이어 2명과 카드팩으로 구성되고, 게임이 시작될 때, 각 플레이어는 카드 2장을 받는다`() {
        // given : 플레이어 2명과 카드팩이 있다.
        val cardDeck = GameDeck.create(CardPack.cards)
        val playerGroup = PlayerGroup(listOf(Player("PoPo"), Player("OYJ")))

        // when : 게임이 시작된다.
        BlackjackGame(cardDeck, playerGroup)

        // then : 각 플레이어는 카드를 2장 받는다.
        playerGroup.playerList.forEach {
            val actual = it.playerDeck.cardDeckSize()
            val expect = 2
            assertThat(actual).isEqualTo(expect)
        }
    }

    @Test
    fun `게임이 시작되고, 플레이어가 카드 받기를 요청할 때, 플레이어는 카드를 받아 저장한다`() {
        // given : 게임이 시작된다. 초기화: 플레이어당 2장씩 배포
        val cardDeck = GameDeck.create(CardPack.cards)
        val playerGroup = PlayerGroup(listOf(Player("PoPo"), Player("OYJ")))
        val game = BlackjackGame(cardDeck, playerGroup)

        // when : 저장 여부 확인
        game.hit()
        val currentPlayer = playerGroup.getCurrentPlayer()

        val expectCard = CardPack.cards[0]
        val actual = currentPlayer.playerDeck.cardDeck.contains(expectCard)

        // then : 플레이어는 카드를 저장한다.
        assertThat(actual).isTrue()
    }
}
