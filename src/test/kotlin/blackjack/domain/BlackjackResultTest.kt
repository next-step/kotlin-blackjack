package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.Character
import blackjack.domain.card.Suit
import blackjack.domain.cards.HandCards
import blackjack.domain.player.Hand
import blackjack.domain.player.Player
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

private fun randomCard() = Card(Suit.values().random(), Character.values().random())

class BlackjackResultTest : StringSpec({
    "blackjackResult 테스트" {
        val player1 = Player("a", Hand(HandCards(mutableListOf(randomCard(), randomCard()))))
        val player2 = Player("b", Hand(HandCards(mutableListOf(randomCard(), randomCard()))))
        val player3 = Player("c", Hand(HandCards(mutableListOf(randomCard(), randomCard()))))
        val player4 = Player("d", Hand(HandCards(mutableListOf(randomCard(), randomCard()))))
        val player5 = Player("e", Hand(HandCards(mutableListOf(randomCard(), randomCard()))))

        val playerResults = listOf(
            PlayerResult(
                player1, true
            ),
            PlayerResult(
                player2, false
            ),
            PlayerResult(
                player3, true
            ),
            PlayerResult(
                player4, false
            ),
            PlayerResult(
                player5, false
            ),
        )

        val blackjackResult = BlackjackResult(playerResults)
        blackjackResult.dealerWin shouldBe 3
        blackjackResult.dealerLose shouldBe 2
    }
})
