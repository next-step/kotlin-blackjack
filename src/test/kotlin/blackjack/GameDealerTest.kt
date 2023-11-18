package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import java.lang.IllegalArgumentException

class GameDealerTest : BehaviorSpec({

    val heartCards = CardShuffleStrategy {
        listOf(
            Card(Card.Symbol.HEART, Card.Number.ACE),
            Card(Card.Symbol.HEART, Card.Number.TWO),
            Card(Card.Symbol.HEART, Card.Number.THREE),
        )
    }
    Given("딜러는 카드를 섞는 전략을 가지며") {
        val gameDealer = GameDealer(heartCards)
        Then("그 전략에 맞게 카드를 분배하며 분배된 카드는 사라진다.") {
            gameDealer.deal() shouldBe Card(Card.Symbol.HEART, Card.Number.ACE)
            gameDealer.deal() shouldBe Card(Card.Symbol.HEART, Card.Number.TWO)
            gameDealer.deal() shouldBe Card(Card.Symbol.HEART, Card.Number.THREE)
        }

        val gameDealer2 = GameDealer(heartCards)
        When("보유한 카드가 존재하지 않는다면") {
            gameDealer2.deal() shouldBe Card(Card.Symbol.HEART, Card.Number.ACE)
            gameDealer2.deal() shouldBe Card(Card.Symbol.HEART, Card.Number.TWO)
            gameDealer2.deal() shouldBe Card(Card.Symbol.HEART, Card.Number.THREE)
            Then("예외를 던진다.") {
                shouldThrow<IllegalArgumentException> {
                    gameDealer2.deal()
                }
            }
        }
    }
})
