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
            val players = game.makePlayers(names)

            players.values.forEach {
                it.cards.values.size shouldBe 2
            }
        }

        "딜러가 게임을 시작할 때 2장의 카드를 지급한다." {
            val dealer = game.makeDealer()
            val initialCardSize = dealer.cards.values.size

            initialCardSize shouldBe 2
        }

        "딜러가 16점 이하라면 카드 한 장을 뽑는다." {
            val players = Players(listOf(bustPlayer))
            val dealer = Dealer(
                Card(Suite.SPADE, Denomination.SIX),
                Card(Suite.HEART, Denomination.SIX)
            )

            game.play(players, dealer)

            dealer.cards.values.size shouldBe 3
        }

        "딜러가 17점 이상이면 스테이한다." {
            val players = Players(listOf(bustPlayer))
            val dealer = Dealer(
                Card(Suite.SPADE, Denomination.SEVEN),
                Card(Suite.HEART, Denomination.KING)
            )

            game.play(players, dealer)

            dealer.cards.values.size shouldBe 2
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
