package blackjack.domain.state

import blackjack.domain.Card
import blackjack.domain.Hand
import blackjack.domain.Rank
import blackjack.domain.Suit
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.types.shouldBeInstanceOf

class InitialTest : StringSpec({
    "블랙잭 상태를 반환할 수 있다." {
        val hand = Hand.createInitial(listOf(Card(Rank.ACE, Suit.SPADES), Card(Rank.TEN, Suit.HEARTS)))
        val state = Initial.initialState(hand)
        state.shouldBeInstanceOf<Blackjack>()
    }

    "히트 상태를 반환할 수 있다." {
        val hand = Hand.createInitial(listOf(Card(Rank.ACE, Suit.SPADES), Card(Rank.TWO, Suit.HEARTS)))
        val state = Initial.initialState(hand)
        state.shouldBeInstanceOf<Hit>()
    }
})
