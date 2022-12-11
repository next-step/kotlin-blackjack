package nextstep.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainExactly
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

})
