package camp.nextstep.edu.step.step2.domain.player

import camp.nextstep.edu.step.step2.domain.card.CardDeck
import camp.nextstep.edu.step.step2.domain.card.Cards
import io.kotest.core.annotation.DisplayName
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.assertThrows

@DisplayName("플레이어는")
class PlayerTest : BehaviorSpec({

    Given("플레이어 이름과 카드들이 존재하고") {
        val playerName = "Joe"
        val cards = Cards(cards = listOf(CardDeck.DrawCard().draw()))

        When("플레이어를 생성하면") {
            val player = Player(name = playerName, cards = cards)

            Then("플레이어가 생성된다") {
                assertNotNull(player)
                player.name shouldBe "Joe"
                player.cards shouldBe cards
            }
        }
    }

    Given("만약 플레이어 이름이비어있고") {
        val playerName = ""
        val cards = Cards(cards = listOf(CardDeck.DrawCard().draw()))

        When("플레이어를 생성하면") {
            val emptyPlayerNameException = assertThrows<IllegalArgumentException> {
                Player(name = playerName, cards = cards)
            }

            Then("에외가 발생한다.") {
                emptyPlayerNameException.message shouldBe "플레이어의 이름은 빈 값이 될 수 없습니다."
            }
        }
    }

    Given("플레이어 도메인이 주어지고") {
        val player = Player(name = "Joe", cards = Cards(cards = listOf(CardDeck.DrawCard().draw())))

        When("플레이어가 가진 카드들을 요청하면") {
            val playerCards = player.getPlayerCards()

            Then("플레이어가 가진 카드들이 반환된다.") {
                assertNotNull(playerCards)
                playerCards shouldBe player.getPlayerCards()
            }
        }

        When("플레이어가 카드를 한장 뽑을때") {
            player.drawCard()

            Then("기존 카드에서 한장이 더 추가된다.") {
                player.getPlayerCardsSize() shouldBe 2
            }
        }

        When("플레이어가 가지고있는 카드들의 합을 요청하면") {
            val playerCardsSum = player.sumOfCards()

            Then("플레이어가 가지고있는 카드들의 합이 반환된다.") {
                assertNotNull(playerCardsSum)
                playerCardsSum shouldBeGreaterThan 0
            }
        }
    }

})
