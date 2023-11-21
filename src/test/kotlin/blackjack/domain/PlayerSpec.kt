package blackjack.domain

import blackJack.model.Card
import blackJack.model.Dealer
import blackJack.model.Player
import blackJack.model.enums.Rank
import blackJack.model.enums.Suit
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class PlayerSpec : BehaviorSpec({

    given("플레이어가 2장의 카드를 받았을 때") {
        val player = Player("플레이어")
        val dealer = Dealer("딜러")
        player.addCard(Card(Suit.SPADES, Rank.ACE))
        player.addCard(Card(Suit.CLUBS, Rank.EIGHT))

        `when`("플레이어가 가진 카드의 합을 구하면") {
            val score = player.calculateScore()

            then("플레이어의 카드 합은 9이다.") {
                score shouldBe 9
            }
        }

        `when`("bust 상태가 아닌 플레이어가 딜러에게 카드를 더 달라고 요구하면") {
            val currentCardCount = player.hand.size
            player.askMoreCard(dealer)
            val newCardCount = player.hand.size

            then("플레이어는 카드를 한장 더 받는다.") {
                currentCardCount shouldBe newCardCount - 1
            }
        }
    }

    given("bust 상태인 플레이어가") {
        val player = Player("플레이어")
        val dealer = Dealer("딜러")
        player.addCard(Card(Suit.SPADES, Rank.JACK))
        player.addCard(Card(Suit.SPADES, Rank.QUEEN))
        player.addCard(Card(Suit.CLUBS, Rank.EIGHT))

        `when`("딜러에게 카드를 더 달라고 요구하면") {
            val currentCardCount = player.hand.size
            player.askMoreCard(dealer)
            val newCardCount = player.hand.size

            then("플레이어는 카드를 받지 않는다.") {
                newCardCount shouldBe currentCardCount
            }
        }
    }
})
