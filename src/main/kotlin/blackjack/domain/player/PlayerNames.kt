package blackjack.domain.player

@JvmInline
value class PlayerNames(val value: List<PlayerName>) {

    inline fun <T> map(transform: (PlayerName) -> T): List<T> {
        return value.map(transform)
    }

    operator fun get(index: Int): PlayerName {
        return value[index]
    }
}
