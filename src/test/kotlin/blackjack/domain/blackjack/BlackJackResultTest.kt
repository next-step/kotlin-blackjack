package blackjack.domain.blackjack

import blackjack.domain.card.Ace
import blackjack.domain.card.Card
import blackjack.domain.card.Cards
import blackjack.domain.card.NumberCard
import blackjack.domain.card.Queen
import blackjack.domain.card.Suit
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
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, Ace()),
                    )
                ),
                playerStatus = PlayerStatus.STAY,
            )
            val pang = Player(
                "pang",
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(2)),
                    )
                ),
                playerStatus = PlayerStatus.STAY,
            )
            val dealer = Dealer(
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(9)),
                    )
                )
            )

            val blackJackResult = BlackJackResult(dealer, Players(listOf(yohan, pang)))

            assertSoftly {
                blackJackResult.playersResult()[0].cards.cards shouldContainExactly
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, Ace()),
                    )

                blackJackResult.dealerResult().cards.cards shouldContainExactly
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(9)),
                    )
            }
        }
    }

    describe("profit") {
        it("딜러와 플레이어별로 수익 금액을 확인할 수 있다") {
            val yohan = Player(
                name = "yohan",
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, Ace()),
                    )
                ),
                playerStatus = PlayerStatus.STAY,
                batting = Money.of(1000)
            )
            val pang = Player(
                "pang",
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(2)),
                    )
                ),
                playerStatus = PlayerStatus.STAY,
                batting = Money.of(1000)
            )
            val dealer = Dealer(
                cards = Cards(
                    listOf(
                        Card(Suit.DIAMOND, Queen()),
                        Card(Suit.DIAMOND, NumberCard(9)),
                    )
                )
            )

            val result = BlackJackResult(dealer, Players(listOf(yohan, pang)))

            assertSoftly {
                result.playersProfit()[0] shouldBe ParticipantProfitResult("yohan", Money.of(1500))
                result.playersProfit()[1] shouldBe ParticipantProfitResult("pang", Money.of(-1000))
                result.dealerProfit() shouldBe ParticipantProfitResult("딜러", Money.of(-500))
            }
        }
    }
})
