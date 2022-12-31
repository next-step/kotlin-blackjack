package blackjack.domain

class Gamers(
    val value: List<Gamer> = emptyList()
) {

    fun isFinish(): Boolean {
        value.forEach {
            if (!it.isFinish) {
                return false
            }
        }
        return true
    }

    operator fun plus(gamer: Gamer): Gamers {
        return Gamers(value + gamer)
    }
}
