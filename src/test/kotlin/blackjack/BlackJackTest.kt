package blackjack

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class BlackJackTest {
    @Test
    fun `에이스가 아닌 카드의 포인트를 합산 할 수 있다`() {
        val card1 = Card(rank = Ranks.TWO, suits = Suits.SPADE)
        val card2 = Card(rank = Ranks.SIX, suits = Suits.HEART)
        val cards = Cards(listOf(card1, card2))
        cards.calculatePoints() shouldBe 8
    }

    @Test
    fun `포인트를 합산 할때 21보다 크지 않다면 에이스 카드는 11점으로 계산한다`() {
        val card1 = Card(rank = Ranks.ACE, suits = Suits.SPADE)
        val card2 = Card(rank = Ranks.TEN, suits = Suits.HEART)
        val cards = Cards(listOf(card1, card2))
        cards.calculatePoints() shouldBe 21
    }

    @Test
    fun `포인트를 합산할때 21점을 초과하면 에이스 카드는 1점으로 계산한다`() {
        val card1 = Card(rank = Ranks.ACE, suits = Suits.SPADE)
        val card2 = Card(rank = Ranks.EIGHT, suits = Suits.HEART)
        val card3 = Card(rank = Ranks.TEN, suits = Suits.HEART)
        val cards = Cards(listOf(card1, card2, card3))
        cards.calculatePoints() shouldBe 19
    }

    @Test
    fun `전체 카드의 수는 52이다`() {
        Cards.create().size shouldBe 52
    }
}

data class Card(val rank: Ranks, val suits: Suits)

enum class Ranks(val points: List<Int>) {
    ACE(listOf(1, 11)),
    TWO(listOf(2)),
    THREE(listOf(3)),
    FOUR(listOf(4)),
    FIVE(listOf(5)),
    SIX(listOf(6)),
    SEVEN(listOf(7)),
    EIGHT(listOf(8)),
    NINE(listOf(9)),
    TEN(listOf(10)),
    JACK(listOf(10)),
    QUEEN(listOf(10)),
    KING(listOf(10)),;
}

enum class Suits {
    SPADE,
    HEART,
    DIAMOND,
    CLUB,
}

class Cards(private val cards: List<Card>) : Collection<Card> by cards {
    fun calculatePoints(): Int {
        val basePoints = cards.sumOf { it.rank.points[0] }
        val hasAce = cards.any { it.rank == Ranks.ACE }
        if (hasAce && basePoints + 10 <= 21) {
            return basePoints + 10
        }
        return basePoints
    }

    companion object {
        fun create(): Cards {
            val allCards = Suits.entries.flatMap { suit ->
                Ranks.entries.map { rank -> Card(rank, suit) }
            }
            return Cards(allCards.shuffled())
        }
    }
}