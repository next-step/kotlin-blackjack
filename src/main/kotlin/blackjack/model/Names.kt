package blackjack.model

@JvmInline
value class Names(private val names: List<Name>) {

    fun <T> map(transform: (Name) -> T): List<T> = names.map(transform)
}
