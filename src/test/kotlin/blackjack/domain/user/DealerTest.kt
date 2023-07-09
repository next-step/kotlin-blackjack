package blackjack.domain.user

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardPattern
import blackjack.domain.card.Deck
import blackjack.domain.status.Hit
import blackjack.domain.status.Lose
import blackjack.domain.status.Stay
import blackjack.domain.status.Win
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class DealerTest : StringSpec({
    "dealer의 point가 16보다 낮을 때, draw하는 경우, Status가 HIT을 유지한다" {
        val dealer = Dealer()

        dealer.draw(Card(CardPattern.Club, CardNumber.SIX))

        dealer.status is Hit
    }

    "dealer의 point가 16보다 높을 때, draw하는 경우, Status가 Stay로 변경된다" {
        val dealer = Dealer()

        dealer.draw(Card(CardPattern.Club, CardNumber.SEVEN))
        dealer.draw(Card(CardPattern.Club, CardNumber.TEN))

        dealer.status is Stay
    }

    "dealer가 1승 1패의 상황일때, judgeResult가 올바르게 동작한다"{
        //given
        val dealer = Dealer()
        dealer.draw(Card(CardPattern.Spade, CardNumber.TEN))
        dealer.draw(Card(CardPattern.Spade, CardNumber.SEVEN))

        val playerA = Player("a")
        playerA.draw(Card(CardPattern.Spade, CardNumber.A))
        playerA.draw(Card(CardPattern.Spade, CardNumber.JACK))

        val playerB = Player("b")
        playerB.draw(Card(CardPattern.Spade, CardNumber.NINE))
        playerB.chooseHitOrStay(false, Deck.create())

        //when
        dealer.judgeResult(PlayerGroup(listOf(playerA, playerB)))

        //then
        playerA.status.shouldBeInstanceOf<Win>()
        playerB.status.shouldBeInstanceOf<Lose>()

    }

})
