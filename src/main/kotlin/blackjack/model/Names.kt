package blackjack.model

@JvmInline
value class Names(private val names: List<Name>) {

    fun <T> map(transform: (Name) -> T): List<T> = names.map(transform)

    companion object {
        private const val DELIMITER = ","

        fun parse(text: String): Names {
            if (text.isEmpty()) return Names(emptyList())

            return text.split(DELIMITER)
                .map { Name.valueOf(it.trim()) }
                .let(::Names)
        }
    }
}
