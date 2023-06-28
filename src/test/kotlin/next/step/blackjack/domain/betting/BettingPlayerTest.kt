package next.step.blackjack.domain.betting

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.dealer.Dealer
import next.step.blackjack.domain.player.Player
import next.step.blackjack.domain.player.PlayerName

class BettingPlayerTest : BehaviorSpec({

    Given("Player") {
        When("name method") {
            Then("player name 제공") {
                BettingPlayer("dj", 100).name() shouldBe "dj"
            }
        }
        When("hit method") {
            val player = BettingPlayer("dj", 100)

            player.hit(Card.of(CardFace.ACE, CardSymbol.CLUB))

            Then("가지고 있는 카드를 추가함") {
                player.cards() shouldBe listOf(Card.of(CardFace.ACE, CardSymbol.CLUB))
            }
            Then("점수는 11점") {
                player.point() shouldBe 11
            }
        }

        When("가지고 있는 카드 총 점수가 21점이 넘지 않으면") {
            val player = BettingPlayer(
                "dj", 100,
                Card.of(CardFace.KING, CardSymbol.CLUB), Card.of(CardFace.KING, CardSymbol.HEART)
            )

            Then("더 hit할 수 있음") {
                assertSoftly {
                    player.canHit() shouldBe true
                }
            }
            Then("점수는 20점") {
                player.point() shouldBe 20
            }
        }
        When("가지고 있는 카드 총 점수가 21점이면") {
            val player = BettingPlayer(
                "dj", 100,
                Card.of(CardFace.ACE, CardSymbol.CLUB), Card.of(CardFace.KING, CardSymbol.HEART)
            )

            Then("더 hit할 수 없음") {
                player.canHit() shouldBe false
            }
            Then("점수는 21점") {
                player.point() shouldBe 21
            }
        }
        When("카드를 더 받아서 총 점수가 21점이 넘으면") {
            val player = BettingPlayer(
                "dj", 100,
                Card.of(CardFace.KING, CardSymbol.CLUB), Card.of(CardFace.KING, CardSymbol.HEART)
            )

            player.hit(Card.of(CardFace.TWO, CardSymbol.DIAMOND))

            Then("더 hit할 수 없음") {
                player.canHit() shouldBe false
            }
            Then("점수는 22점") {
                player.point() shouldBe 22
            }
            Then("카드 설명 제공") {
                player.cardDescs() shouldBe listOf("K클로버", "K하트", "2다이아몬드")
            }
        }
    }

    Given("Player fight") {
        val dealer = Dealer(
            Card.of(CardFace.SEVEN, CardSymbol.CLUB),
            Card.of(CardFace.TEN, CardSymbol.DIAMOND)
        )
        When("플레이어가 블랙잭으로 이기면") {
            val player = BettingPlayer(
                "blackjack",
                1000,
                Card.of(CardFace.ACE, CardSymbol.CLUB),
                Card.of(CardFace.TEN, CardSymbol.HEART)
            )
            Then("베팅 금액의 1.5배를 제공") {
                player.fight(dealer) shouldBe 1000 * 1.5
            }
        }
        When("플레이어가 이기면") {
            val player = BettingPlayer(
                "win",
                1000,
                Card.of(CardFace.TEN, CardSymbol.CLUB),
                Card.of(CardFace.TEN, CardSymbol.HEART)
            )
            Then("베팅 금액의 1배를 제공") {
                player.fight(dealer) shouldBe 1000 * 1
            }
        }
        When("플레이어가 비기면") {
            val player = BettingPlayer(
                "win",
                1000,
                Card.of(CardFace.SEVEN, CardSymbol.CLUB),
                Card.of(CardFace.TEN, CardSymbol.HEART)
            )
            Then("베팅 금액의 0배를 제공") {
                player.fight(dealer) shouldBe 1000 * 0
            }
        }

        When("플레이어가 지면") {
            val player = BettingPlayer(
                "win",
                1000,
                Card.of(CardFace.FIVE, CardSymbol.CLUB),
                Card.of(CardFace.TEN, CardSymbol.HEART)
            )
            Then("베팅 금액의 0배를 제공") {
                player.fight(dealer) shouldBe 1000 * -1
            }
        }
    }
})

private fun BettingPlayer(name: String, amount: Int, vararg cards: Card): BettingPlayer =
    BettingPlayer.of(Player.of(PlayerName.of(name), Cards.of(cards.toList())), BettingAmount.of(amount))

private fun Dealer(vararg cards: Card): Dealer = Dealer.of(Cards.of(cards.toList()))
