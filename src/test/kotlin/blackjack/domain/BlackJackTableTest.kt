package blackjack.domain

import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.Players
import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.api.Test
import blackjack.domain.player.PlayerNotify as PlayerNotify1

class BlackJackTableTest {

    @Test
    fun `게임 카드셋에 카드 중복은 없음`() {
        shouldNotThrow<IllegalStateException> {
            val playerNotify = object : PlayerNotify1 {
                override fun generatePlayers(): List<Player> {
                    return mutableListOf(Player(PlayerName("pobi"), GameMoney(10000)))
                }
            }
            BlackJackTable.of(playerNotify)
        }
    }

    @Test
    fun `게임은 입력 없이도 테스트 가능함`() {
        shouldNotThrow<IllegalStateException> {

            val playerNotify = object : PlayerNotify1 {
                override fun generatePlayers(): List<Player> {
                    return mutableListOf(Player(PlayerName("pobi"), GameMoney(10000)))
                }
            }

            val blackJackTable = BlackJackTable.of(playerNotify)

            blackJackTable.startGame(
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
