package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import study.blackjack.model.BlackjackPlayer
import study.blackjack.model.Card
import study.blackjack.model.Cards
import study.blackjack.model.Match
import study.blackjack.model.Suit

/**
 * @author 이상준
 */
class BlackjackGameTest : StringSpec({
    "플레이어 승 테스트" {
        val playerCards =
            Cards(
                mutableListOf(
                    Card(Suit.DIAMOND, 9),
                    Card(Suit.DIAMOND, 10),
                ),
            )
        val dealerCards =
            Cards(
                mutableListOf(
                    Card(Suit.DIAMOND, 8),
                    Card(Suit.DIAMOND, 10),
                ),
            )

        val player = BlackjackPlayer("player").apply { addAllCards(playerCards) }
        val dealer = BlackjackPlayer("dealer").apply { addAllCards(dealerCards) }
        player.match(dealer)

        player.result() shouldBe Match.WIN.text
    }
    "플레이어 패배 테스트" {
        val playerCards =
            Cards(
                mutableListOf(
                    Card(Suit.DIAMOND, 7),
                    Card(Suit.DIAMOND, 10),
                ),
            )
        val dealerCards =
            Cards(
                mutableListOf(
                    Card(Suit.DIAMOND, 8),
                    Card(Suit.DIAMOND, 10),
                ),
            )

        val player = BlackjackPlayer("player").apply { addAllCards(playerCards) }
        val dealer = BlackjackPlayer("dealer").apply { addAllCards(dealerCards) }
        player.match(dealer)

        player.result() shouldBe Match.LOSE.text
    }
})
