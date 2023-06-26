package next.step.blackjack.domain.player

import io.kotest.assertions.assertSoftly
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

class PlayerTest : BehaviorSpec({

    Given("Player") {
        When("hit하면") {
            val player = Player.of(PlayerName.of("dj"), Cards.of(emptyList()))
            val card = Card.of(CardFace.ACE, CardSymbol.CLUB)

            player.hit(card)

            Then("가지고 있는 카드를 추가함") {
                player shouldBe Player.of(PlayerName.of("dj"), Cards.of(mutableListOf(card)))
            }
            Then("점수는 11점") {
                player.point() shouldBe 11
            }
        }

        When("가지고 있는 카드 총 점수가 21점이 넘지 않으면") {
            val player = Player.of(
                PlayerName.of("dj"),
                Cards.of(
                    listOf(
                        Card.of(CardFace.KING, CardSymbol.CLUB),
                        Card.of(CardFace.KING, CardSymbol.HEART)
                    )
                )
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
            val player = Player.of(
                PlayerName.of("dj"),
                Cards.of(listOf(Card.of(CardFace.ACE, CardSymbol.CLUB), Card.of(CardFace.KING, CardSymbol.HEART)))
            )

            Then("더 hit할 수 없음") {
                player.canHit() shouldBe false
            }
            Then("점수는 21점") {
                player.point() shouldBe 21
            }
        }
        When("카드를 더 받아서 총 점수가 21점이 넘으면") {
            val player = Player.of(
                PlayerName.of("dj"),
                Cards.of(
                    listOf(
                        Card.of(CardFace.KING, CardSymbol.CLUB),
                        Card.of(CardFace.KING, CardSymbol.HEART)
                    )
                )
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
        val player = Player.of(
            PlayerName.of("unfinished17"),
            Cards.of(
                listOf(
                    Card.of(CardFace.SEVEN, CardSymbol.CLUB),
                    Card.of(CardFace.TEN, CardSymbol.HEART)
                )
            )
        )
        When("Player state로 게임 결과가 결정되면") {
            val other = Player.of(
                PlayerName.of("blackjack"),
                Cards.of(
                    listOf(
                        Card.of(CardFace.ACE, CardSymbol.CLUB),
                        Card.of(CardFace.TEN, CardSymbol.HEART)
                    )
                )
            )
            Then("그대로 게임결과 제공") {
                player.fight(other) shouldBe GameResult.LOSE
            }
        }
        When("Player state로 게임 결과가 결정되지 않으면") {
            val other = Player.of(
                PlayerName.of("unfinished16"),
                Cards.of(
                    listOf(
                        Card.of(CardFace.SIX, CardSymbol.CLUB),
                        Card.of(CardFace.TEN, CardSymbol.HEART)
                    )
                )
            )
            Then("점수 비교로 게임 결과 제공") {
                player.fight(other) shouldBe GameResult.WIN
            }
        }
    }
})
