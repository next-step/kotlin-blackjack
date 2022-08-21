package blackjack.domain.gameresult

import blackjack.domain.GameProfit.GameProfit
import blackjack.domain.bettingmoney.BettingMoney
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.Face
import blackjack.domain.card.Suit
import blackjack.domain.participant.Dealer
import blackjack.domain.participant.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import java.math.BigDecimal

class GameProfitTest : StringSpec({
    "플레이어의 게임 결과를 확인한다." {
        listOf(
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.ACE),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(1_500.0))
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.EIGHT),
                    Card(Suit.CLOVER, Face.TEN),
                    Card(Suit.CLOVER, Face.ACE)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(1_000L))
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.EIGHT),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(1_000L))
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SEVEN),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.ZERO)
            ),
            row(
                mutableListOf(
                    Card(Suit.CLOVER, Face.SIX),
                    Card(Suit.CLOVER, Face.TEN)
                ),
                BettingMoney(1_000L),
                GameProfit(BigDecimal.valueOf(-1_000L))
            )
        ).forEach { (playerCards, bettingMoney, expectedResult) ->

            val player = Player(
                "경록",
                Cards(playerCards),
                bettingMoney
            )

            val dealer = Dealer(
                Cards(
                    mutableListOf(
                        Card(Suit.CLOVER, Face.SEVEN),
                        Card(Suit.CLOVER, Face.TEN)
                    )
                )
            )

            // when
            val actual = GameProfit.valueOf(player, dealer)

            // then
            actual shouldBe expectedResult
        }
    }
})
