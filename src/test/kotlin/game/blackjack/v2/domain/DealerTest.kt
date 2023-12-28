package game.blackjack.v2.domain

import game.blackjack.v2.domain.card.Card
import game.blackjack.v2.domain.card.CardNumber.ACE
import game.blackjack.v2.domain.card.CardNumber.EIGHT
import game.blackjack.v2.domain.card.CardNumber.FIVE
import game.blackjack.v2.domain.card.CardNumber.FOUR
import game.blackjack.v2.domain.card.CardNumber.JACK
import game.blackjack.v2.domain.card.CardNumber.KING
import game.blackjack.v2.domain.card.CardNumber.NINE
import game.blackjack.v2.domain.card.CardNumber.QUEEN
import game.blackjack.v2.domain.card.CardNumber.SEVEN
import game.blackjack.v2.domain.card.CardNumber.SIX
import game.blackjack.v2.domain.card.CardNumber.TEN
import game.blackjack.v2.domain.card.CardNumber.THREE
import game.blackjack.v2.domain.card.CardNumber.TWO
import game.blackjack.v2.domain.card.CardShape.DIAMOND
import game.blackjack.v2.domain.card.CardShape.SPADE
import game.blackjack.v2.domain.participant.Dealer
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class DealerTest : StringSpec({

    "딜러는 현재 카드 패 총 점수가 16점 이하이면 카드를 한장 더 뽑는다." {
        forAll(
            row(listOf(Card(TEN, SPADE), Card(TWO, SPADE))),
            row(listOf(Card(TEN, SPADE), Card(THREE, SPADE))),
            row(listOf(Card(TEN, SPADE), Card(FOUR, SPADE))),
            row(listOf(Card(TEN, SPADE), Card(FIVE, SPADE))),
            row(listOf(Card(TEN, SPADE), Card(SIX, SPADE))),
            row(listOf(Card(ACE, SPADE), Card(FOUR, SPADE))),
            row(listOf(Card(ACE, SPADE), Card(FIVE, SPADE))),
            row(listOf(Card(ACE, SPADE), Card(ACE, DIAMOND)))
        ) { cards: List<Card> ->
            val dealer = Dealer()
            dealer.drawCards(cards)
            val originalHandSize = dealer.getHandCards().size

            dealer.drawCardIfRequired()

            dealer.getHandCards().size shouldBe originalHandSize + 1
        }
    }

    "딜러의 현재 카드 패 총 점수가 17점 이상이면 한장을 더 뽑아야 되는지에 대한 여부에 false로 반환한다." {
        forAll(
            row(listOf(Card(TEN, SPADE), Card(SEVEN, SPADE))),
            row(listOf(Card(TEN, SPADE), Card(EIGHT, SPADE))),
            row(listOf(Card(TEN, SPADE), Card(NINE, SPADE))),
            row(listOf(Card(TEN, SPADE), Card(TEN, DIAMOND))),
            row(listOf(Card(TEN, SPADE), Card(JACK, SPADE))),
            row(listOf(Card(ACE, SPADE), Card(QUEEN, SPADE))),
            row(listOf(Card(ACE, SPADE), Card(KING, SPADE))),
            row(listOf(Card(ACE, SPADE), Card(SIX, SPADE)))
        ) { cards: List<Card> ->
            val dealer = Dealer()
            dealer.drawCards(cards)
            val originalHandSize = dealer.getHandCards().size

            dealer.drawCardIfRequired()

            dealer.getHandCards().size shouldBe originalHandSize
        }
    }
})
