package blackjack.domain

open class Player(
    private val name: String = "",
    override val cards: Cards = Cards(setOf())
) : Participant {
    var matchResult: String = TEXT_LOSE
        private set

    override fun draw(newCard: Card?): Cards? {
        newCard ?: return null
        return Cards(cards.add(newCard))
    }

    fun winMatch() {
        matchResult = TEXT_WIN
    }

    fun isWon() = matchResult == TEXT_WIN

    override fun toString(): String = name

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Player) return false

        if (this.name != other.name) return false
        if (this.cards != other.cards) return false
        if (this.matchResult != other.matchResult) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name.hashCode()
        result += cards.hashCode()
        return 31 * result + matchResult.hashCode()
    }

    companion object {
        private const val TEXT_WIN = "승"
        private const val TEXT_LOSE = "패"
    }
}
