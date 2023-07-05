package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Dealer
import blackjack.domain.Player
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

internal class BlackJackGameTest {

    @DisplayName("플레이어는 게임 시작 시 카드 두 장을 지급받는다.")
    @Test
    fun initPlayerGetCards() {
        val dealer = Dealer()
        val players = listOf(Player(), Player())
        players.forAll { player ->
            player.cards.isEmpty() shouldBe true
        }

        val blackJackGame = BlackJackGame(dealer, players)
        blackJackGame.players.forAll { player ->
            player.cards.size shouldBe 2
        }
    }
}
