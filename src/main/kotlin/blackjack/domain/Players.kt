package blackjack.domain

@JvmInline
value class Players(
    val values: List<Player>
) {
    init {
        require(values.isNotEmpty()) { "플레이어는 최소 1명이 되어야한다." }
    }

    companion object {
        fun from(names: List<String>) = Players(names.map { Player(it) })
    }
}
