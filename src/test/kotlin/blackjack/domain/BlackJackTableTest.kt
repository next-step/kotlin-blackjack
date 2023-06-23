package blackjack.domain

import blackjack.view.ViewCallback
import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.api.Test

class BlackJackTableTest {

    @Test
    fun `게임 카드셋에 카드 중복은 없음`() {
        shouldNotThrow<IllegalStateException> {
            BlackJackTable()
        }
    }

    @Test
    fun `게임은 입력 없이도 테스트 가능함`() {
        shouldNotThrow<IllegalStateException> {
            val player = Player(PlayerName("pobi"))
            BlackJackTable().startGame(
                Players(listOf(player)),
                object : ViewCallback {
                    override fun showPlayerSet(players: Players) {
                    }

                    override fun isMoreCard(player: Player): Boolean {
                        return false
                    }

                    override fun showPlayerCards(player: Player) {
                    }

                    override fun showGameResult(player: Player) {
                    }
                }
            )
        }
    }
}
