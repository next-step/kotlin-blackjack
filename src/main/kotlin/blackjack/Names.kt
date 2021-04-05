package blackjack

data class Names(private val names: List<String>): List<String> by names {
    constructor(names: String): this(names.split(NAME_SPLITTER))

    companion object {
        private const val NAME_SPLITTER = ","
    }
}
