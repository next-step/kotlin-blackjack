package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Rank
import blackjack.domain.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.types.shouldBeInstanceOf

class HitTest : StringSpec({
    "히트 상태에서 카드를 추가했을 때, 21을 초과하면 버스트 상태를 반환한다." {
        forAll(
            row(
                listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.TWO, Suit.HEARTS)),
                Card(Rank.TEN, Suit.DIAMONDS),
            ),
            row(
                listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.TEN, Suit.HEARTS)),
                Card(Rank.TWO, Suit.DIAMONDS),
            ),
        ) { initialCards, drawCard ->
            val hand = Hand.createInitial(initialCards)
            val state = Hit(hand)
            val afterState = state.draw(drawCard)

            afterState.shouldBeInstanceOf<Bust>()
        }
    }

    "히트 상태에서 카드를 추가했을 때, 21을 넘지 않으면 히트 상태를 반환한다." {
        forAll(
            row(
                listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.TWO, Suit.HEARTS)),
                Card(Rank.EIGHT, Suit.DIAMONDS),
            ),
            row(
                listOf(Card(Rank.TEN, Suit.SPADES), Card(Rank.TEN, Suit.HEARTS)),
                Card(Rank.ACE, Suit.DIAMONDS),
            ),
            row(
                listOf(Card(Rank.ACE, Suit.SPADES), Card(Rank.NINE, Suit.HEARTS)),
                Card(Rank.ACE, Suit.DIAMONDS),
            ),
        ) { initialCards, drawCard ->
            val hand = Hand.createInitial(initialCards)
            val state = Hit(hand)
            val afterState = state.draw(drawCard)

            afterState.shouldBeInstanceOf<Hit>()
        }
    }
})
