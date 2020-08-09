package blackjack.domain.deck

enum class Pip(name: String) {
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K"),
    ACE("A");

    companion object {

        fun scoreOf(pip: Pip) = when {
            pip == ACE -> 11
            pip.name.toIntOrNull() == null -> 10
            else -> pip.name.toInt()
        }
    }
}
