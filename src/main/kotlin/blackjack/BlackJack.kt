package blackjack

import blackjack.view.InputView
import blackjack.view.OutputView

object ResultDealer {
    fun getCardNumbers(cardDeck: CardDeck): List<String> = cardDeck.cards.map { card -> card.cardNumber }
        .sortedWith(compareBy { if (it == "A") Int.MAX_VALUE else 0 })

    fun getGameScore(cardNumbers: List<String>): Int = cardNumbers.fold(0) { total, num ->
        if (num == "A") {
            val diff1 = kotlin.math.abs(GameManager.GOAL - total + GameManager.ACE_HIGH)
            val diff2 = kotlin.math.abs(GameManager.GOAL - total + GameManager.ACE_LOW)
            if (diff1 < diff2) {
                total + 11
            } else {
                total + 1
            }
        } else if (listOf("J", "Q", "K").contains(num)) {
            total + 10
        } else {
            total + (num.toIntOrNull() ?: 0)
        }
    }
}

object CardDealer { // TODO: Shuffle 외부화
    private val CARD_NUMBERS = listOf("2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A")
    private var CARD_SUIT = listOf("스페이드", "다이아몬드", "하트", "클로버")

    fun getCards(count: Int): List<Card> = (1..count).map {
        val number = CARD_NUMBERS.shuffled()[0]
        val suit = CARD_SUIT.shuffled()[0]
        Card("${number}${suit}", number)
    }
}

class GameResult(val player: Player, val resultCount: Int)

class GameManager(playerNames: List<String>) {
    val players: List<Player>

    init {
        players = playerNames.map { Player(it) }
    }

    fun conductGame() { // TODO: 리팩토링
        players.forEach {
            while (true) {
                println("${it.name}는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)")
                val answer = readln();
                require(answer == "y" || answer == "n") {
                    "대답은 y나 n으로 하십사오"
                }
                when (answer) {
                    "y" -> {
                        it.cardDeck.addCards(CardDealer.getCards(1))
                        OutputView.renderPlayer(it, ::println)
                    }
                    "n" -> break
                }
            }
        }
    }

    fun finishGame() = players.map { player ->
        val cardNumbers = ResultDealer.getCardNumbers(player.cardDeck)

        GameResult(player, ResultDealer.getGameScore(cardNumbers))
    }

    companion object {
        const val GOAL = 21
        const val ACE_HIGH = 11
        const val ACE_LOW = 1
    }
}

class Card(val name: String, val cardNumber: String)

class CardDeck {
    var cards: List<Card> = CardDealer.getCards(2)
        private set

    fun addCards(card: List<Card>) {
        val newCards = cards.toMutableList()
        newCards.addAll(card)
        cards = newCards.toList()
    }
}

class Player(val name: String, val cardDeck: CardDeck = CardDeck())

class BlackJack {
    fun start() {
        val playerNames = InputView.getPlayerName()
        val manager = GameManager(playerNames)

        OutputView.renderPlayers(manager.players)
        manager.conductGame();
        val results = manager.finishGame();
        OutputView.renderResult(results)
    }
}
