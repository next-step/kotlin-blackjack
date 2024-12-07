package blackjack

import blackjack.view.InputView
import blackjack.view.ResultView

fun String.split(delimiter: String = ","): List<String> {
    if (this.isBlank()) return emptyList()

    val regex = Regex.escape(delimiter).toRegex()
    return this.split(regex)
        .map { it.trim() }
        .filter { it.isNotEmpty() }
}

data class Player(
    val name: String,
) {
    val cards: Cards = initCards()

    init {
        validateName()
    }

    private fun validateName() {
        if (!name.matches(Regex("^[A-Za-z]+$"))) throw IllegalArgumentException("이름이 잘못 입력되었습니다.")
    }

    private fun initCards(): Cards =
        Cards(setOf(Card.takeRandomCard(), Card.takeRandomCard()))

    fun takeNewCard() {
        cards.addNewCard()
    }

    fun calculateResult(): Int {
        return cards.calculateCardValues()
    }
}

class Cards(cards: Set<Card> = setOf()) {
    private val _cards = cards.toMutableSet()
    val cards: Set<Card>
        get() = _cards.toSet()

    fun addNewCard() {
        _cards.add(Card.takeRandomCard())
    }

    fun calculateCardValues(): Int {
        var total = 0
        var aceCount = 0

        // 모든 카드의 값을 합산하고, 에이스의 개수를 셈
        for (card in _cards) {
            total += card.getValue()
            if (card.isAce) {
                aceCount++
            }
        }

        // 에이스의 값을 11로 변경할 수 있는지 확인하고, 가능하면 변경
        while (aceCount > 0 && total + 10 <= 21) {
            total += 10
            aceCount--
        }

        return total
    }

    override fun toString(): String {
        return _cards.toString()
    }
}

class Card private constructor(private val suit: Suit, private val rank: Rank) {
    val isAce: Boolean = rank == Rank.ACE

    fun getValue(): Int = rank.value

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Card) return false

        return suit == other.suit && rank == other.rank
    }

    override fun hashCode(): Int {
        var result = suit.hashCode()
        result = 31 * result + rank.hashCode()
        return result
    }

    override fun toString(): String =
        when (rank) {
            Rank.JACK -> "J${suit.description}"
            Rank.QUEEN -> "Q${suit.description}"
            Rank.KING -> "K${suit.description}"
            Rank.ACE -> "A${suit.description}"
            else -> "${rank.value}${suit.description}"
        }

    companion object {
        private val allCards: MutableSet<Card> =
            Suit.entries.flatMap { suit ->
                Rank.entries.map { rank ->
                    Card(suit, rank)
                }
            }
                .toMutableSet()

        fun takeRandomCard(): Card {
            if (allCards.isEmpty()) throw IllegalStateException("남는 카드가 없습니다.")
            val card = allCards.random()
            allCards.remove(card)
            return card
        }
    }
}

enum class Suit(val description: String) {
    SPADES("스페이드"), HEARTS("하트"), DIAMONDS("다이아몬드"), CLUBS("클로버")
}

enum class Rank(val value: Int) {
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6),
    SEVEN(7), EIGHT(8), NINE(9), TEN(10),
    JACK(10), QUEEN(10), KING(10),
    ACE(1) // 에이스는 1 또는 11로 계산 필요
}

fun main() {
    val inputView = InputView()
    val resultView = ResultView()
    val playerNamesInput = inputView.getPlayerNamesInput()
    val playersNames = playerNamesInput.split()
    val players = playersNames.map { Player(name = it) }
    resultView.renderPlayerInitOutput(playersNames)
    for (player in players) {
        resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
    }

    for (player in players) {
        while (true) {
            val answerInput = inputView.getPlayerRequestInput(player.name)
            if (answerInput == "n") break
            player.takeNewCard()
            resultView.renderPlayerCardsOutput(player.name, player.cards.toString())
        }
    }

    for (player in players) {
        resultView.renderPlayerCardsResultOutput(player.name, player.cards.toString(), player.calculateResult())
    }
}