package blackjack

@JvmInline
value class Names(private val names: List<Name>) {

    companion object {
        private const val DELIMITER = ","

        fun parse(text: String): Names = text.split(DELIMITER)
            .map { Name.valueOf(it) }
            .let(::Names)
    }
}
