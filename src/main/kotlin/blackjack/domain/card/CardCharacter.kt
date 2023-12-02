package blackjack.domain.card

import blackjack.domain.player.Name

enum class CardCharacter(val displayName: Name, val score: Int){
    ACE(Name("A"), 1),
    JACK(Name("J"), 10),
    QUEEN(Name("Q"), 10),
    KING(Name("K"), 10),
    TWO(Name("2"), 2),
    THREE(Name("3"), 3),
    FOUR(Name("4"), 4),
    FIVE(Name("5"), 5),
    SIX(Name("6"), 6),
    SEVEN(Name("7"), 7),
    EIGHT(Name("8"), 8),
    NINE(Name("9"), 9),
    TEN(Name("10"), 10),
    ;
}
