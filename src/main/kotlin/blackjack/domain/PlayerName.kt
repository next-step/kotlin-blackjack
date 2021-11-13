package blackjack.domain

@JvmInline
value class PlayerName(val value: String) {

    companion object {

        private const val NAME_DELIMITER = ","

        fun from(names: String): List<PlayerName> {
            return names.split(NAME_DELIMITER).map { PlayerName(it.trim()) }
        }
    }
}
