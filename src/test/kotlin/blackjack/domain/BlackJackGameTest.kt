package blackjack.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class BlackJackGameTest : StringSpec() {
    private lateinit var game: BlackJackGame

    init {
        beforeEach {
            game = BlackJackGame()
        }

        "2명의 참가자가 게임을 시작할 때 카드를 2장씩 지급한다." {
            val names = listOf("플레이어2", "플레이어2")
            game.setInitPlayers(names)

            game.players.forEach {
                it.cards.values.size shouldBe 2
            }
        }

        "딜러가 게임을 시작할 때 2장의 카드를 지급한다." {
            game.setInitDealer()
            val initialCardSize = game.dealer.cards.values.size

            initialCardSize shouldBe 2
        }

        "딜러가 16점 이하라면 카드 한 장을 뽑는다." {
            val players = listOf(bustPlayer)
            val dealer = Dealer(
                Card(Suite.SPADE, Denomination.SIX),
                Card(Suite.HEART, Denomination.SIX)
            )
            val game = BlackJackGame(
                dealer = dealer,
                players = players
            )

            game.play()

            game.dealer.cards.values.size shouldBe 3
        }

        "딜러가 17점 이상이면 스테이한다." {
            val players = listOf(bustPlayer)
            val dealer = Dealer(
                Card(Suite.SPADE, Denomination.SEVEN),
                Card(Suite.HEART, Denomination.KING)
            )
            val game = BlackJackGame(
                dealer = dealer,
                players = players
            )

            game.play()

            game.dealer.cards.values.size shouldBe 2
        }

        "플레이어 수만큼의 플레이어 결과를 반환한다." {
            val players = listOf(
                Player(), Player(), Player()
            )
            val dealer = Dealer()
            val game = BlackJackGame(
                dealer = dealer,
                players = players
            )

            game.calculateResult()

            game.playerResults.size shouldBe 3
        }
    }

    companion object {
        val bustPlayer = Player(
            Card(Suite.CLOVER, Denomination.KING),
            Card(Suite.CLOVER, Denomination.JACK),
            Card(Suite.HEART, Denomination.TWO)
        )
    }
}
