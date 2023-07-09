package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.Deck
import blackjack.domain.card.NumberShape
import blackjack.domain.card.Pattern
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class Player(
    val name: String,
    private var hasAce: Boolean = false,
    val cards: MutableList<Card> = mutableListOf()
) {
    fun drawCard(deck: Deck) {
        cards.add(deck.getOneCard())
    }

    fun getCardsValue(): Int {
        val total = cards.sumOf { it.getValue() }
        if (hasAce) {
            if (total + 10 <= 21) {
                return total + 10
            }
        }
        return total
    }

    companion object {
        fun of(name: String, deck: Deck): Player {
            return Player(name, cards = mutableListOf(deck.getOneCard(), deck.getOneCard()))
        }
    }
}

class PlayerTest : BehaviorSpec({
    Given("플레이어는 게임시작시 카드를 두장씩 받는다.") {
        val deck = Deck.makeDeck()
        When("게임이 시작해서 플레이어가 생성됐다.") {
            val player = Player.of("pavlo", deck)
            Then("가지고 있는 카드의수가 2장이여야한다.")
            player.cards.size shouldBe 2
        }
    }
    Given("Ace를 포함해서 가지고 있는 수가 11이하 일때 10 을 더해주어야한다.") {
        When("가지고 있는 수가 11이하 일때") {
            val player = Player(
                "player",
                hasAce = true,
                mutableListOf(
                    Card(NumberShape.ACE, Pattern.SPADE),
                    Card(NumberShape.KING, Pattern.SPADE)
                )
            )
            Then("Ace 가 1이 아닌 11로 계산된다.") {
                player.getCardsValue() shouldBe 21
            }
        }
        When("가지고 있는 수가 11보다 클떄") {
            val player = Player(
                "player",
                hasAce = true,
                mutableListOf(
                    Card(NumberShape.ACE, Pattern.SPADE),
                    Card(NumberShape.NINE, Pattern.SPADE),
                    Card(NumberShape.KING, Pattern.SPADE)
                )
            )
            Then("Ace는 1로 계산된다.") {
                player.getCardsValue() shouldBe 20
            }
        }
    }
    Given("플레이어는 카드를 더 받을 수 있다") {
        val deck = Deck.makeDeck()
        val player = Player.of("pavlo", deck)
        When("플레이어가 2장의 카드에서 카드를 더 받을떄") {
            player.drawCard(deck)
            Then("플레이어의 카드 수가 3장이 되어야한다.") {
                player.cards.size shouldBe 3
            }
        }
    }
})
