package blackjack.domain.player

import blackjack.domain.card.Card
import blackjack.domain.card.NumberShape
import blackjack.domain.card.Pattern
import blackjack.domain.deck.Deck
import blackjack.domain.game.BLACKJACK_NUMBER
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class PlayerTest : BehaviorSpec({
    Given("플레이어와 카드덱이 주어졌을때") {
        val deck = Deck.makeDeck()
        val player = Player.of("pavlo")
        When("게임이 시작하면") {
            player.drawStartHand(deck)
            Then("가지고 있는 카드의수가 2장이다.") {
                player.getHands().size shouldBe 2
            }
        }
        When("게임을 시작하고 한장더 받으면") {
            player.hit(deck.popCard())
            Then("플레이어의 카드 수가 3장이 되어야한다.") {
                player.getHands().size shouldBe 3
            }
        }
        When("플레이어의 카드의 수가 21을 넘어가면") {
            val overHandsPlayer = Player(
                "player", cantHitHands
            )
            val expect = overHandsPlayer.getHands().size
            overHandsPlayer.race(deck.popCard(), { player: Player -> player.getHandsValue() <= BLACKJACK_NUMBER }, {})
            Then("플레이어는 카드를 더 받을 수 없다.") {
                overHandsPlayer.getHands().size shouldBe expect
            }
        }
        When("플레이어의 카드의 수가 21을 넘지 않았다면") {
            val lowHandsPlayer = Player(
                "player", canHitHands
            )
            val expect = lowHandsPlayer.getHands().size
            lowHandsPlayer.race(deck.popCard(), { player: Player -> player.getHandsValue() <= BLACKJACK_NUMBER }, {})
            Then("플레이어는 카드를 더 받을 수 있다.") {
                lowHandsPlayer.getHands().size shouldNotBe expect
            }
        }
    }
})

private val cantHitHands = Hands(
    mutableSetOf(
        Card(NumberShape.KING, Pattern.SPADE),
        Card(NumberShape.KING, Pattern.CLUB),
        Card(NumberShape.EIGHT, Pattern.HEART)
    )
)

private val canHitHands = Hands(
    mutableSetOf(
        Card(NumberShape.ACE, Pattern.HEART),
        Card(NumberShape.NINE, Pattern.CLUB)
    )
)
