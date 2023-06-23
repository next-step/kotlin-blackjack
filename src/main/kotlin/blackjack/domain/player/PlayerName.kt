package blackjack.domain.player

@JvmInline
value class PlayerName(val value: String) {

    fun unWrapping(): String {
        return value
    }
}

fun List<PlayerName>.unWrappings(): List<String> {
    return map { it.unWrapping() }
}
