package blackjack.model

fun interface PlayerNamesProvider {
    fun names(): Collection<String>
}
