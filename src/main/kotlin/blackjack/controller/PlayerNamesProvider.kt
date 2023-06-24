package blackjack.controller

fun interface PlayerNamesProvider {
    fun names(): Collection<String>
}
