package blackjack

import blackjack.Card.Companion.BLACK_JACK_NUM

enum class CardNumber(val addExecute: (Int) -> Int, val score: Int) {
    ACE({ num -> if (num + ACE.score > BLACK_JACK_NUM) num + 1 else num + ACE.score }, 11),
    TWO({ num -> num + TWO.score }, 2),
    THREE({ num -> num + THREE.score }, 3),
    FOUR({ num -> num + FOUR.score }, 4),
    FIVE({ num -> num + FIVE.score }, 5),
    SIX({ num -> num + SIX.score }, 6),
    SEVEN({ num -> num + SEVEN.score }, 7),
    EIGHT({ num -> num + EIGHT.score }, 8),
    NINE({ num -> num + NINE.score }, 9),
    TEN({ num -> num + TEN.score }, 10),
    JACK({ num -> num + JACK.score }, 10),
    QUEEN({ num -> num + QUEEN.score }, 10),
    KING({ num -> num + KING.score }, 10)
}
