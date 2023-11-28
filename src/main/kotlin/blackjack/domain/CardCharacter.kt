package blackjack.domain

enum class CardCharacter(val displayName: Name){
    ACE(Name("A")),
    JACK(Name("J")),
    QUEEN(Name("Q")),
    KING(Name("K")),
    ONE(Name("1")),
    TWO(Name("2")),
    THREE(Name("3")),
    FOUR(Name("4")),
    FIVE(Name("5")),
    SIX(Name("6")),
    SEVEN(Name("7")),
    EIGHT(Name("8")),
    NINE(Name("9")),
    ;
}
