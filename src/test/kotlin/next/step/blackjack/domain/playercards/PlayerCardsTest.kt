package next.step.blackjack.domain.playercards

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import next.step.blackjack.domain.card.Card
import next.step.blackjack.domain.card.CardFace
import next.step.blackjack.domain.card.CardSymbol
import next.step.blackjack.domain.card.Cards
import next.step.blackjack.domain.game.GameResult

class PlayerCardsTest : BehaviorSpec({

    Given("PlayerCards") {
        val cards = PlayerCards(Card.of(CardFace.FIVE, CardSymbol.DIAMOND))

        When("hit하면") {
            cards.hit(Card.of(CardFace.ACE, CardSymbol.CLUB))

            Then("가지고 있는 카드를 추가함") {
                cards.cards() shouldBe listOf(
                    Card.of(CardFace.FIVE, CardSymbol.DIAMOND),
                    Card.of(CardFace.ACE, CardSymbol.CLUB)
                )
            }
            Then("점수는 11점") {
                cards.point() shouldBe 16
            }
        }
        When("desc하면") {
            Then("설명 셋 제공") {
                PlayerCards(Card.of(CardFace.FIVE, CardSymbol.DIAMOND)).descs() shouldBe setOf("5다이아몬드")
            }
        }
        When("descFirst하면") {
            Then("첫번째 카드 설명 제공") {
                cards.descFirst() shouldBe "5다이아몬드"
            }
        }
        When("Stay 상태이면") {
            Then("true") {
                cards.isStay() shouldBe true
            }
        }
        When("블랙잭 상태이면") {
            Then("true") {
                val cards = PlayerCards(
                    Card.of(CardFace.ACE, CardSymbol.DIAMOND),
                    Card.of(CardFace.KING, CardSymbol.DIAMOND)
                )

                cards.isBlackjack() shouldBe true
            }
        }
    }

    Given("Player fight") {
        val cards = PlayerCards(
            Card.of(CardFace.SEVEN, CardSymbol.CLUB),
            Card.of(CardFace.TEN, CardSymbol.HEART)
        )

        When("state로 게임 결과가 결정되면") {
            val otherCards = PlayerCards(
                Card.of(CardFace.ACE, CardSymbol.CLUB),
                Card.of(CardFace.TEN, CardSymbol.HEART)
            )
            Then("그대로 게임결과 제공") {
                cards.fight(otherCards) shouldBe GameResult.LOSE
            }
        }
        When("state로 게임 결과가 결정되지 않으면") {
            val otherCards = PlayerCards(
                Card.of(CardFace.SIX, CardSymbol.CLUB),
                Card.of(CardFace.TEN, CardSymbol.HEART)
            )
            Then("점수 비교로 게임 결과 제공") {
                cards.fight(otherCards) shouldBe GameResult.WIN
            }
        }
    }
})

fun PlayerCards(vararg cards: Card): PlayerCards = PlayerCards.of(Cards.of(cards.toList()))
