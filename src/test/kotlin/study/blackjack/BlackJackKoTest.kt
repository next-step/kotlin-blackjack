package study.blackjack

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import kotlin.random.Random

class BlackJackKoTest : StringSpec({
    "참여자들에게 카드를 두 장 나누어 진다" {
        val deck = Deck()
        val dealer = Dealer(deck)
        val players = arrayOf(Player("test1"), Player("test2"))
        dealer.startRound(players)
        players.forEach { it.hands.size shouldBe 2 }
    }

})

class Player(val name: String) {
    val hands = mutableListOf<PokerCard>()
}

class Dealer(val deck: Deck) {
    fun startRound(players: Array<Player>) {
        for (player in players) {
            val firstRoundCards = listOf(deck.drawCard(), deck.drawCard())
            player.hands.addAll(firstRoundCards)
        }
    }
}

class Deck {

    private val graveyard = Array(DECK_SIZE) { false }
    private val random = Random

    fun drawCard(): PokerCard {
        var drawIndex: Int
        do {
            drawIndex = random.nextInt(DECK_SIZE)
        } while (graveyard[drawIndex])

        graveyard[drawIndex] = true
        return PokerCardBuilder(drawIndex).build()
    }

    companion object {
        private const val DECK_SIZE = 52
    }
}

class PokerCard(val symbol: PokerSymbol, val value: Int)

class PokerCardBuilder(drawIndex: Int) {

    private val symbol: PokerSymbol
    private var value: Int

    init {
        symbol = PokerSymbol.getSymbolByDrawNumber(drawIndex)
        value = drawIndex + REALIZATION_NUMBER
    }

    fun build(): PokerCard {
        return PokerCard(symbol, value)
    }

    companion object {
        private const val REALIZATION_NUMBER = 1
    }
}

enum class PokerSymbol(
    val value: Int,
    val symbolName: String
) {
    HEARTS(0, "하트"),
    DIAMONDS(1, "다이아몬드"),
    CLUBS(2, "클로버"),
    SPADES(3, "스페이드");

    companion object {
        fun getSymbolByDrawNumber(drawNumber: Int): PokerSymbol {
            return when (drawNumber / 13) {
                HEARTS.value -> HEARTS
                DIAMONDS.value -> DIAMONDS
                CLUBS.value -> CLUBS
                SPADES.value -> SPADES
                else -> {throw IllegalArgumentException("${drawNumber}는 0과 52사이 값이어야 합니다.")}
            }
        }
    }
}
