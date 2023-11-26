package blackjack.model

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class JudgeTest : BehaviorSpec({
    Given("Judge") {
        When("딜러가 A A 8카드이고 플레이어가 4 2 5 카드 일때") {
            val dealer = CardHolder.GameDealer(
                1,
                CardHand(
                    listOf(
                        Card(CardValue.A, CardSuit.SPADES),
                        Card(CardValue.A, CardSuit.SPADES),
                        Card(CardValue.EIGHT, CardSuit.SPADES)
                    )
                )
            )
            val player = CardHolder.Player(
                2,
                CardHand(
                    listOf(
                        Card(CardValue.FOUR, CardSuit.SPADES),
                        Card(CardValue.TWO, CardSuit.SPADES),
                        Card(CardValue.FIVE, CardSuit.SPADES)
                    )
                ),
                "player"
            )
            Then("딜러 점수는 20, 플레이어 점수는 11이고 딜러승") {
                Judge.resolve(dealer, player)
                dealer.cardHand.totalScore shouldBe 20
                player.cardHand.totalScore shouldBe 11
                dealer.winLoseDraw.win shouldBe 1
                player.winLoseDraw.lose shouldBe 1
            }
        }

        When("딜러가 K K 8카드이고 플레이어가 4 2 5 카드 일때") {
            val dealer = CardHolder.GameDealer(
                1,
                CardHand(
                    listOf(
                        Card(CardValue.K, CardSuit.SPADES),
                        Card(CardValue.K, CardSuit.SPADES),
                        Card(CardValue.EIGHT, CardSuit.SPADES)
                    )
                )
            )
            val player = CardHolder.Player(
                2,
                CardHand(
                    listOf(
                        Card(CardValue.FOUR, CardSuit.SPADES),
                        Card(CardValue.TWO, CardSuit.SPADES),
                        Card(CardValue.FIVE, CardSuit.SPADES)
                    )
                ),
                "player"
            )
            Then("딜러 점수는 28, 플레이어 점수는 11이고 플레이어 승") {
                Judge.resolve(dealer, player)
                dealer.cardHand.totalScore shouldBe 28
                player.cardHand.totalScore shouldBe 11
                dealer.winLoseDraw.lose shouldBe 1
                player.winLoseDraw.win shouldBe 1
            }
        }
        When("딜러가 K K 8카드이고 플레이어가 K K 8 카드 일때") {
            val dealer = CardHolder.GameDealer(
                1,
                CardHand(
                    listOf(
                        Card(CardValue.K, CardSuit.SPADES),
                        Card(CardValue.K, CardSuit.SPADES),
                        Card(CardValue.EIGHT, CardSuit.SPADES)
                    )
                )
            )
            val player = CardHolder.Player(
                2,
                CardHand(
                    listOf(
                        Card(CardValue.K, CardSuit.SPADES),
                        Card(CardValue.K, CardSuit.SPADES),
                        Card(CardValue.EIGHT, CardSuit.SPADES)
                    )
                ),
                "player"
            )
            Then("딜러 점수는 28, 플레이어 점수는 28이고 딜러 승") {
                Judge.resolve(dealer, player)
                dealer.cardHand.totalScore shouldBe 28
                player.cardHand.totalScore shouldBe 28
                dealer.winLoseDraw.win shouldBe 1
                player.winLoseDraw.lose shouldBe 1
            }
        }
    }
})
