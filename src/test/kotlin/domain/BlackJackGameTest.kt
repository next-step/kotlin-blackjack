package domain

import domain.BlackJackGame.issue
import io.kotest.matchers.collections.shouldNotContainAll
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @Test
    fun `플레이어가 발급받은 카드는 카드덱에서 제외되어야 한다`() {
        val player1 = Player("keira")
        val player2 = Player("tyrus")
        var players = listOf<Player>(player1, player2)

        val offered = BlackJackGame.setInitialCards(players)

        offered.forEach {
            it.cards.shouldNotContainAll(CardDeck.allCards)
        }
    }

    @Test
    fun `플레이어는 카드를 안받기로 선택하면 카드를 받지 않는다`() {
        val player = Player("keira")

        issue(player, false)

        assertThat(player.cards).isEmpty()
    }
}
