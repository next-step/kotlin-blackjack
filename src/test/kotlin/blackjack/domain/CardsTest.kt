package blackjack.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.math.abs

class Cards(private val value: ArrayList<Card>) {
    private var sum: Int = 0

    fun drawCard() {
        value.add(CardDeck.drawCard())
    }

    fun calculateCards(): Int {
        sortByAce()
        sum = 0
        for (card in value) {
            val convertResult = convertNumberToInt(card) ?: convertMagicNumber(card)
            sum += convertResult
        }
        return sum
    }

    fun toList(): List<Card> {
        return value.toList()
    }

    private fun sortByAce() {
        value.sortBy { card -> card.value.first == "A" }
    }

    private fun convertNumberToInt(card: Card): Int? {
        return card.value.first.toIntOrNull()
    }

    private fun convertMagicNumber(card: Card): Int {
        if (card.value.first == "A") return selectBestWayOfAce()
        return 10
    }

    private fun selectBestWayOfAce(): Int {
        val whenAceIs11 = abs(WINNING_NUMBER - (sum + 11))
        val whenAceIs1 = abs(WINNING_NUMBER - (sum + 1))

        if (whenAceIs11 < whenAceIs1) {
            return 11
        }
        return 1
    }

    companion object {
        const val WINNING_NUMBER = 21
    }
}

class Player(val name: String) {
    private val cards: Cards = Cards(arrayListOf<Card>())

    init {
        repeat(INIT_CARD_NUMBER) {
            cards.drawCard()
        }
    }

    fun drawCard() {
        cards.drawCard()
    }

    fun calculateCards(): Int {
        return cards.calculateCards()
    }

    fun getCardList(): List<Card> {
        return cards.toList()
    }

    companion object {
        private const val INIT_CARD_NUMBER = 2
    }
}

class CardsTest {

    val cards = Cards(arrayListOf(Card.makeCard("A", Pattern.HEART), Card.makeCard("A", Pattern.HEART)))
    val cards2 = Cards(arrayListOf(Card.makeCard("A", Pattern.HEART), Card.makeCard("J", Pattern.HEART)))
    val cards3 = Cards(
        arrayListOf(
            Card.makeCard("A", Pattern.HEART),
            Card.makeCard("J", Pattern.HEART),
            Card.makeCard("A", Pattern.SPADE)
        )
    )

    @Test
    fun `카드패에 Ace가 있는경우 가장 마지막으로 정렬되는지 확인`() {
        cards2.calculateCards()
        assertThat(cards2.toList().last()).isEqualTo(Card.makeCard("A", Pattern.HEART))
    }

    @Test
    fun `주어진 카드에서 21과 가장 가깝게 출력하는지 확인`() {
        assertThat(cards.calculateCards()).isEqualTo(22)
        assertThat(cards2.calculateCards()).isEqualTo(21)
        assertThat(cards3.calculateCards()).isEqualTo(22)
    }
}