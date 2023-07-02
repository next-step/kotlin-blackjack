package blackjack.domain

import blackjack.blackjackTable
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerName
import blackjack.domain.player.PlayerNotify
import blackjack.domain.player.Players
import io.kotest.assertions.throwables.shouldNotThrow
import org.junit.jupiter.api.Test

class BlackJackTableTest {

    @Test
    fun `게임 카드셋에 카드 중복은 없음`() {
        shouldNotThrow<IllegalStateException> {
            val playerNotify = object : PlayerNotify {
                override fun inputPlayer(): MutableList<Player> {
                    return mutableListOf(Player(PlayerName("pobi")))
                }

                override fun inputBettingMoney(players: MutableList<Player>) {
                    players.forEach {
                        it.setBettingMoney("10000")
                    }
                }
            }

            blackjackTable {
                cardDeck = makeCardDeck()
                notify = playerNotify
                joinPlayers {
                    setBettingMoney()
                }
            }
        }
    }

    @Test
    fun `게임은 입력 없이도 테스트 가능함`() {
        shouldNotThrow<IllegalStateException> {

            val playerNotify = object : PlayerNotify {
                override fun inputPlayer(): MutableList<Player> {
                    return mutableListOf(Player(PlayerName("pobi")))
                }

                override fun inputBettingMoney(players: MutableList<Player>) {
                    players.forEach {
                        it.setBettingMoney("10000")
                    }
                }
            }

            val blackJackTable = blackjackTable {
                cardDeck = makeCardDeck()
                notify = playerNotify
                joinPlayers {
                    setBettingMoney()
                }
            }
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
