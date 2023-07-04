package blackjack.domain.status

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.user.Dealer
import blackjack.domain.user.Player
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class ResultStatusTest : StringSpec({
    "dealer가 BURST인 경우, DEALER_BURST 상태 조건이 true로 반환된다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.JACK))
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.QUEEN))
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.KING))
        dealer.updateStatus()

        val player = Player("test")
        ResultStatus.DEALER_BURST.isMatch(player, dealer) shouldBe true
    }

    "dealer와 player 모두 BURST인 경우, DEALER_BURST 상태 조건이 true로 반환된다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.JACK))
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.QUEEN))
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.KING))
        dealer.updateStatus()

        val player = Player("test")
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.JACK))
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.QUEEN))
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.KING))
        player.updateStatus()

        ResultStatus.DEALER_BURST.isMatch(player, dealer) shouldBe true
    }

    "player만 BURST인 경우, PLAYER_BURST 상태 조건이 true로 반환된다" {
        val dealer = Dealer()

        val player = Player("test")
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.JACK))
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.QUEEN))
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.KING))
        player.updateStatus()

        ResultStatus.PLAYER_BURST.isMatch(player, dealer) shouldBe true
    }

    "dealer가 player에게 점수비교로 이긴 경우, COMPARE_POINT_DEALER_WIN 상태 조건이 true로 반환된다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.JACK))
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.TEN))
        dealer.updateStatus()

        val player = Player("test")
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.NINE))
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.TEN))
        player.updateStatus()

        ResultStatus.COMPARE_POINT_DEALER_WIN.isMatch(player, dealer) shouldBe true
    }

    "player가 dealer에게 점수비교로 이긴 경우, COMPARE_POINT_DEALER_WIN 상태 조건이 true로 반환된다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.EIGHT))
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.TEN))
        dealer.updateStatus()

        val player = Player("test")
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.NINE))
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.TEN))
        player.updateStatus()

        ResultStatus.COMPARE_POINT_PLAYER_WIN.isMatch(player, dealer) shouldBe true
    }

    "player와 dealer가 비긴 경우, COMPARE_POINT_DRAW 상태 조건이 true로 반환된다" {
        val dealer = Dealer()
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.NINE))
        dealer.cards.addCard(Card(CardPattern.Spade, CardNumber.TEN))
        dealer.updateStatus()

        val player = Player("test")
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.NINE))
        player.cards.addCard(Card(CardPattern.Spade, CardNumber.TEN))
        player.updateStatus()

        ResultStatus.COMPARE_POINT_DRAW.isMatch(player, dealer) shouldBe true
    }

})
