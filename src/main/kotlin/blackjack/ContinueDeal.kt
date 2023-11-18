package blackjack

enum class ContinueDeal(
    private val desc: String
) {
    YES("y"),
    NO("n"),
    MISS("miss")
    ;

    companion object {
        fun of(desc: String) =
            values().find { it.desc.equals(desc, true) } ?: MISS
    }
}
