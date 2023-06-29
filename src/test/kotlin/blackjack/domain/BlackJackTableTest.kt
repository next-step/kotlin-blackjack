package blackjack.domain

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
            val blackJackTable = BlackJackTable()
            blackJackTable.startGame(
                Players(mutableListOf(player)),
                object : GameConditionNotify {
                    override fun giveDefaultCardsToPlayerDone(players: Players) {
                    }

                    override fun isNeedMoreCard(player: Player): Boolean {
                        return false
                    }

                    override fun giveCardToPlayerDone(player: Player) {
                    }

                    override fun finishBlackJackGame(player: Players) {
                    }
                }
            )
        }
    }
}
