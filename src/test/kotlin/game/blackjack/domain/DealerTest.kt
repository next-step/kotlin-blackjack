package game.blackjack.domain

import game.blackjack.domain.CardNumber.ACE
import game.blackjack.domain.CardNumber.EIGHT
import game.blackjack.domain.CardNumber.FIVE
import game.blackjack.domain.CardNumber.FOUR
import game.blackjack.domain.CardNumber.JACK
import game.blackjack.domain.CardNumber.KING
import game.blackjack.domain.CardNumber.NINE
import game.blackjack.domain.CardNumber.QUEEN
import game.blackjack.domain.CardNumber.SEVEN
import game.blackjack.domain.CardNumber.SIX
import game.blackjack.domain.CardNumber.TEN
import game.blackjack.domain.CardNumber.THREE
import game.blackjack.domain.CardNumber.TWO
import game.blackjack.domain.CardShape.DIAMOND
import game.blackjack.domain.CardShape.SPADE
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
            val deck = Deck()
            val dealer = Dealer()
            dealer.drawCards(cards)
            val originalHandSize = dealer.getHandSize()

            dealer.drawCardIfRequired(deck)

            dealer.getHandSize() shouldBe originalHandSize + 1
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
            val deck = Deck()
            val dealer = Dealer()
            dealer.drawCards(cards)
            val originalHandSize = dealer.getHandSize()

            dealer.drawCardIfRequired(deck)

            dealer.getHandSize() shouldBe originalHandSize
        }
    }
})
