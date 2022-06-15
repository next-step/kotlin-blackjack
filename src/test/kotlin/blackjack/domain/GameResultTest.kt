package blackjack.domain

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class GameResultTest : FreeSpec({

    "딜러가 승리" - {
        "참가자가 bust 이고 딜러는 아닌 경우" {
            val dealer = createScore(Denomination.KING, Denomination.ACE)
            val player = createScore(Denomination.KING, Denomination.QUEEN, Denomination.TWO)

            GameResult.from(dealer, player) shouldBe GameResult.DEALER_WON
        }

        "둘 다 bust 는 아니고 딜러의 점수가 21에 가까운 경우" {
            val dealer = createScore(Denomination.KING, Denomination.JACK)
            val player = createScore(Denomination.KING, Denomination.NINE)

            GameResult.from(dealer, player) shouldBe GameResult.DEALER_WON
        }
    }

    "참가자가 승리" - {
        "둘 다 bust 인 경우" {
            val dealer = createScore(Denomination.KING, Denomination.TEN, Denomination.TWO)
            val player = createScore(Denomination.KING, Denomination.QUEEN, Denomination.SIX)

            GameResult.from(dealer, player) shouldBe GameResult.PLAYER_WON
        }

        "참가자만 블랙잭인 경우" {
            val dealer = createScore(Denomination.KING, Denomination.NINE, Denomination.ACE)
            val player = createScore(Denomination.KING, Denomination.ACE)

            GameResult.from(dealer, player) shouldBe GameResult.PLAYER_BLACKJACK_WON
        }

        "둘 다 bust 는 아니고 참가자의 점수가 21에 가까운 경우" {
            val dealer = createScore(Denomination.KING, Denomination.NINE)
            val player = createScore(Denomination.KING, Denomination.JACK)

            GameResult.from(dealer, player) shouldBe GameResult.PLAYER_WON
        }
    }

    "무승부" - {
        "둘 다 블랙잭인 경우" {
            val dealer = createScore(Denomination.KING, Denomination.ACE)
            val player = createScore(Denomination.KING, Denomination.ACE)

            GameResult.from(dealer, player) shouldBe GameResult.DRAW
        }

        "둘 다 bust 가 아니고 점수가 같은경우" {
            val dealer = createScore(Denomination.KING, Denomination.NINE)
            val player = createScore(Denomination.KING, Denomination.NINE)

            GameResult.from(dealer, player) shouldBe GameResult.DRAW
        }
    }
})

fun createScore(vararg denomination: Denomination) = Score(denomination.map { Card(Suite.HEARTS, it) })
