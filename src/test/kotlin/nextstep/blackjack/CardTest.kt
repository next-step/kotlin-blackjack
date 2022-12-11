package nextstep.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import nextstep.blackjack.Card.CLOVER_ACE
import nextstep.blackjack.Card.CLOVER_EIGHT
import nextstep.blackjack.Card.CLOVER_FIVE
import nextstep.blackjack.Card.CLOVER_FOUR
import nextstep.blackjack.Card.CLOVER_JACK
import nextstep.blackjack.Card.CLOVER_KING
import nextstep.blackjack.Card.CLOVER_NINE
import nextstep.blackjack.Card.CLOVER_ONE
import nextstep.blackjack.Card.CLOVER_QUEEN
import nextstep.blackjack.Card.CLOVER_SEVEN
import nextstep.blackjack.Card.CLOVER_SIX
import nextstep.blackjack.Card.CLOVER_TEN
import nextstep.blackjack.Card.CLOVER_THREE
import nextstep.blackjack.Card.CLOVER_TWO
import nextstep.blackjack.Card.DIAMOND_ACE
import nextstep.blackjack.Card.DIAMOND_EIGHT
import nextstep.blackjack.Card.DIAMOND_FIVE
import nextstep.blackjack.Card.DIAMOND_FOUR
import nextstep.blackjack.Card.DIAMOND_JACK
import nextstep.blackjack.Card.DIAMOND_KING
import nextstep.blackjack.Card.DIAMOND_NINE
import nextstep.blackjack.Card.DIAMOND_ONE
import nextstep.blackjack.Card.DIAMOND_QUEEN
import nextstep.blackjack.Card.DIAMOND_SEVEN
import nextstep.blackjack.Card.DIAMOND_SIX
import nextstep.blackjack.Card.DIAMOND_TEN
import nextstep.blackjack.Card.DIAMOND_THREE
import nextstep.blackjack.Card.DIAMOND_TWO
import nextstep.blackjack.Card.HEART_ACE
import nextstep.blackjack.Card.HEART_EIGHT
import nextstep.blackjack.Card.HEART_FIVE
import nextstep.blackjack.Card.HEART_FOUR
import nextstep.blackjack.Card.HEART_JACK
import nextstep.blackjack.Card.HEART_KING
import nextstep.blackjack.Card.HEART_NINE
import nextstep.blackjack.Card.HEART_ONE
import nextstep.blackjack.Card.HEART_QUEEN
import nextstep.blackjack.Card.HEART_SEVEN
import nextstep.blackjack.Card.HEART_SIX
import nextstep.blackjack.Card.HEART_TEN
import nextstep.blackjack.Card.HEART_THREE
import nextstep.blackjack.Card.HEART_TWO
import nextstep.blackjack.Card.SPADE_ACE
import nextstep.blackjack.Card.SPADE_EIGHT
import nextstep.blackjack.Card.SPADE_FIVE
import nextstep.blackjack.Card.SPADE_FOUR
import nextstep.blackjack.Card.SPADE_JACK
import nextstep.blackjack.Card.SPADE_KING
import nextstep.blackjack.Card.SPADE_NINE
import nextstep.blackjack.Card.SPADE_ONE
import nextstep.blackjack.Card.SPADE_QUEEN
import nextstep.blackjack.Card.SPADE_SEVEN
import nextstep.blackjack.Card.SPADE_SIX
import nextstep.blackjack.Card.SPADE_TEN
import nextstep.blackjack.Card.SPADE_THREE
import nextstep.blackjack.Card.SPADE_TWO
import nextstep.blackjack.Card.values

class CardTest : StringSpec({

    "카드는 각 하트, 클로버, 다이아몬드, 스페이드 모양의 ACE, 2~10, King, Queen, Jack을 갖는다." {
        values() shouldContainExactly arrayOf(
            HEART_ACE,
            HEART_ONE,
            HEART_TWO,
            HEART_THREE,
            HEART_FOUR,
            HEART_FIVE,
            HEART_SIX,
            HEART_SEVEN,
            HEART_EIGHT,
            HEART_NINE,
            HEART_TEN,
            HEART_JACK,
            HEART_QUEEN,
            HEART_KING,

            DIAMOND_ACE,
            DIAMOND_ONE,
            DIAMOND_TWO,
            DIAMOND_THREE,
            DIAMOND_FOUR,
            DIAMOND_FIVE,
            DIAMOND_SIX,
            DIAMOND_SEVEN,
            DIAMOND_EIGHT,
            DIAMOND_NINE,
            DIAMOND_TEN,
            DIAMOND_JACK,
            DIAMOND_QUEEN,
            DIAMOND_KING,

            SPADE_ACE,
            SPADE_ONE,
            SPADE_TWO,
            SPADE_THREE,
            SPADE_FOUR,
            SPADE_FIVE,
            SPADE_SIX,
            SPADE_SEVEN,
            SPADE_EIGHT,
            SPADE_NINE,
            SPADE_TEN,
            SPADE_JACK,
            SPADE_QUEEN,
            SPADE_KING,

            CLOVER_ACE,
            CLOVER_ONE,
            CLOVER_TWO,
            CLOVER_THREE,
            CLOVER_FOUR,
            CLOVER_FIVE,
            CLOVER_SIX,
            CLOVER_SEVEN,
            CLOVER_EIGHT,
            CLOVER_NINE,
            CLOVER_TEN,
            CLOVER_JACK,
            CLOVER_QUEEN,
            CLOVER_KING,
        )
    }

    "카드 모양상관없이 2~10은 번호만큼의 점수, King, Queen, Jack은 10점이 될 수 있다." {
        listOf(
            HEART_ONE to 1,
            HEART_TWO to 2,
            HEART_THREE to 3,
            HEART_FOUR to 4,
            HEART_FIVE to 5,
            HEART_SIX to 6,
            HEART_SEVEN to 7,
            HEART_EIGHT to 8,
            HEART_NINE to 9,
            HEART_TEN to 10,
            HEART_JACK to 10,
            HEART_QUEEN to 10,
            HEART_KING to 10,

            DIAMOND_ONE to 1,
            DIAMOND_TWO to 2,
            DIAMOND_THREE to 3,
            DIAMOND_FOUR to 4,
            DIAMOND_FIVE to 5,
            DIAMOND_SIX to 6,
            DIAMOND_SEVEN to 7,
            DIAMOND_EIGHT to 8,
            DIAMOND_NINE to 9,
            DIAMOND_TEN to 10,
            DIAMOND_JACK to 10,
            DIAMOND_QUEEN to 10,
            DIAMOND_KING to 10,

            SPADE_ONE to 1,
            SPADE_TWO to 2,
            SPADE_THREE to 3,
            SPADE_FOUR to 4,
            SPADE_FIVE to 5,
            SPADE_SIX to 6,
            SPADE_SEVEN to 7,
            SPADE_EIGHT to 8,
            SPADE_NINE to 9,
            SPADE_TEN to 10,
            SPADE_JACK to 10,
            SPADE_QUEEN to 10,
            SPADE_KING to 10,

            CLOVER_ONE to 1,
            CLOVER_TWO to 2,
            CLOVER_THREE to 3,
            CLOVER_FOUR to 4,
            CLOVER_FIVE to 5,
            CLOVER_SIX to 6,
            CLOVER_SEVEN to 7,
            CLOVER_EIGHT to 8,
            CLOVER_NINE to 9,
            CLOVER_TEN to 10,
            CLOVER_JACK to 10,
            CLOVER_QUEEN to 10,
            CLOVER_KING to 10,
        ).forAll { (card: Card, point: Int) ->
            card.getPoint() shouldBe point
        }
    }
})
