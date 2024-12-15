package blackjack.flow

import blackjack.card.Card
import blackjack.card.CardNumber
import blackjack.card.CardSuit
import blackjack.card.Deck
import blackjack.participant.Dealer
import blackjack.participant.Player
import blackjack.participant.PlayerName
import blackjack.view.policy.StubMoreCardPolicy
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class TurnMachineTest : StringSpec({
    "플레이어 턴에 스탠드를 선택하면 카드를 더 뽑지 않는다." {
        // Arrange:
        val deck = Deck()
        val turnMachine = TurnMachine(deck = deck, moreCardPolicy = StubMoreCardPolicy())
        val player = Player(PlayerName("player1"))

        // Act:
        turnMachine.playerTurn(player)

        // Assert:
        player.cardHolder.cards.cards.size shouldBe 0
    }

    "딜러 턴에 보유한 카드가 17 이상이면 카드를 더 뽑지 않는다." {
        // Arrange:
        val deck = Deck()
        val turnMachine = TurnMachine(deck = deck, moreCardPolicy = StubMoreCardPolicy())
        val dealer = Dealer()
        dealer.take(
            listOf(
                Card(CardNumber.TEN, CardSuit.HEARTS),
                Card(CardNumber.TEN, CardSuit.SPADES),
            ),
        )

        // Act:
        turnMachine.dealerTurn(dealer)

        // Assert:
        dealer.cardHolder.cards.cards.size shouldBe 2
    }

    "딜러 턴에 보유한 카드가 16 이하이면 카드를 더 뽑는다." {
        // Arrange:
        val deck = Deck()
        val turnMachine = TurnMachine(deck = deck, moreCardPolicy = StubMoreCardPolicy())
        val dealer = Dealer()
        dealer.take(
            listOf(
                Card(CardNumber.TWO, CardSuit.HEARTS),
                Card(CardNumber.TEN, CardSuit.SPADES),
            ),
        )

        // Act:
        turnMachine.dealerTurn(dealer)

        // Assert:
        dealer.cardHolder.cards.cards.size shouldBe 3
    }
})
