package blackjack.domain.blackjack

import blackjack.domain.card.card
import blackjack.domain.card.cards
import blackjack.domain.common.Money
import blackjack.domain.player.Dealer
import blackjack.domain.player.Player
import blackjack.domain.player.PlayerStatus
import blackjack.domain.player.Players
import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class BlackJackResultTest : DescribeSpec({

    describe("result") {
        it("딜러와 플리어별로 카드를 확인할 수 있다") {
            val yohan = Player(
                name = "yohan",
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to "A" }
                },
                playerStatus = PlayerStatus.STAY,
            )

            val pang = Player(
                "pang",
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to 2 }
                },
                playerStatus = PlayerStatus.STAY,
            )
            val dealer = Dealer(
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to 9 }
                },
            )

            val blackJackResult = BlackJackResult(dealer, Players(listOf(yohan, pang)))

            assertSoftly {
                blackJackResult.playersResult()[0].cards.cards shouldContainExactly
                    listOf(
                        card { "다이아몬드" to "Q" },
                        card { "다이아몬드" to "A" }
                    )
            }

            blackJackResult.dealerResult().cards.cards shouldContainExactly
                listOf(
                    card { "다이아몬드" to "Q" },
                    card { "다이아몬드" to 9 }
                )
        }
    }

    describe("profit") {
        it("딜러와 플레이어별로 수익 금액을 확인할 수 있다") {
            val yohan = Player(
                name = "yohan",
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to "A" }
                },
                playerStatus = PlayerStatus.STAY,
                batting = Money.of(1000)
            )
            val pang = Player(
                "pang",
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to 2 }
                },
                playerStatus = PlayerStatus.STAY,
                batting = Money.of(1000)
            )
            val dealer = Dealer(
                cards = cards {
                    card { "다이아몬드" to "Q" }
                    card { "다이아몬드" to 9 }
                }
            )

            val result = BlackJackResult(dealer, Players(listOf(yohan, pang)))

            assertSoftly {
                result.playersProfit()[0] shouldBe ParticipantProfitResult(
                    "yohan",
                    Money.of(1500)
                )
                result.playersProfit()[1] shouldBe ParticipantProfitResult(
                    "pang",
                    Money.of(-1000)
                )
                result.dealerProfit() shouldBe ParticipantProfitResult(
                    "딜러",
                    Money.of(-500)
                )
            }
        }
    }
})
