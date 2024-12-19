package blackjack.domain.state

enum class InputState(val key: String? = null) {
    NONE(),
    HIT("y"),
    STAY("n"),
    ;

    companion object {
        fun valueOfInputState(key: String?): InputState {
            return when (key) {
                HIT.key -> HIT
                STAY.key -> STAY
                else -> NONE
            }
        }
    }
}

fun String.toInputState(): InputState = InputState.valueOfInputState(this)
