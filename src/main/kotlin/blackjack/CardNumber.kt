package blackjack

import blackjack.Card.Companion.BLACK_JACK_NUM

enum class CardNumber(val showName: String, val addExecute: (Int) -> Int, val score: Int) {
    ACE("A", { num -> if (num + ACE.score > BLACK_JACK_NUM) num + 1 else num + ACE.score }, 11),
    TWO("2", { num -> num + TWO.score }, 2), THREE("3", { num -> num + THREE.score }, 3),
    FOUR("4", { num -> num + FOUR.score }, 4), FIVE("5", { num -> num + FIVE.score }, 5),
    SIX("6", { num -> num + SIX.score }, 6), SEVEN("7", { num -> num + SEVEN.score }, 7),
    EIGHT("8", { num -> num + EIGHT.score }, 8), NINE("9", { num -> num + NINE.score }, 9),
    TEN("10", { num -> num + TEN.score }, 10), JACK("J", { num -> num + JACK.score }, 10),
    QUEEN("Q", { num -> num + QUEEN.score }, 10), KING("K", { num -> num + KING.score }, 10)
}
