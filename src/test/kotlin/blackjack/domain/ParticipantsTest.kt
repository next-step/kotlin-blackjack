package blackjack.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class ParticipantsTest : FunSpec({
    context("딜러가 21을 초과하면 딜러는 패배한다.") {
        val dealer = Dealer()
        dealer.addCard(Card(Suit.HEART, Denomination.JACK))
        dealer.addCard(Card(Suit.HEART, Denomination.QUEEN))
        dealer.addCard(Card(Suit.HEART, Denomination.KING))

        val participants = Participants(listOf(dealer, Player("june")))

        participants.isDealerBust() shouldBe true
    }

    context("딜러와 2명의 플레이어가 있는 경우 딜러가 1승 1패 하는 상황") {
        val dealer = Dealer()
        dealer.addCard(Card(Suit.DIAMOND, Denomination.THREE))
        dealer.addCard(Card(Suit.CLUB, Denomination.NINE))
        dealer.addCard(Card(Suit.DIAMOND, Denomination.EIGHT))

        val player1 = Player("pobi")
        player1.addCard(Card(Suit.HEART, Denomination.TWO))
        player1.addCard(Card(Suit.SPADE, Denomination.EIGHT))
        player1.addCard(Card(Suit.CLUB, Denomination.ACE))

        val player2 = Player("jason")
        player2.addCard(Card(Suit.CLUB, Denomination.SEVEN))
        player2.addCard(Card(Suit.SPADE, Denomination.KING))

        val participants = Participants(listOf(dealer, player1, player2))
        val dealerResult = participants.calculateDealerResult()

        dealerResult shouldBe DealerResult(win = 1, lose = 1, draw = 0)
        player1.calculateResult(dealer) shouldBe GameResult.WIN
        player2.calculateResult(dealer) shouldBe GameResult.LOSE
    }

    context("딜러와 2명의 플레이어가 있는 경우 딜러가 2승 0패 하는 상황") {
        val dealer = Dealer()
        dealer.addCard(Card(Suit.DIAMOND, Denomination.THREE))
        dealer.addCard(Card(Suit.CLUB, Denomination.NINE))
        dealer.addCard(Card(Suit.DIAMOND, Denomination.EIGHT))

        val player1 = Player("pobi")
        player1.addCard(Card(Suit.HEART, Denomination.TWO))
        player1.addCard(Card(Suit.SPADE, Denomination.TWO))
        player1.addCard(Card(Suit.CLUB, Denomination.TWO))

        val player2 = Player("jason")
        player2.addCard(Card(Suit.CLUB, Denomination.SEVEN))
        player2.addCard(Card(Suit.SPADE, Denomination.KING))

        val participants = Participants(listOf(dealer, player1, player2))
        val dealerResult = participants.calculateDealerResult()

        dealerResult shouldBe DealerResult(win = 2, lose = 0, draw = 0)
        player1.calculateResult(dealer) shouldBe GameResult.LOSE
        player2.calculateResult(dealer) shouldBe GameResult.LOSE
    }
})
