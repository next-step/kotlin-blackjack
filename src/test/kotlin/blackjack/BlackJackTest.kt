package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.util.*

class BlackJackTest {
    @Test
    fun `에이스가 아닌 카드의 포인트를 합산 할 수 있다`() {
        val card1 = Card(rank = Ranks.TWO, suits = Suits.SPADE)
        val card2 = Card(rank = Ranks.SIX, suits = Suits.HEART)
        val userCards = UserCards(mutableListOf(card1, card2))
        userCards.calculatePoints() shouldBe 8
    }

    @Test
    fun `포인트를 합산 할때 21보다 크지 않다면 에이스 카드는 11점으로 계산한다`() {
        val card1 = Card(rank = Ranks.ACE, suits = Suits.SPADE)
        val card2 = Card(rank = Ranks.TEN, suits = Suits.HEART)
        val userCards = UserCards(mutableListOf(card1, card2))
        userCards.calculatePoints() shouldBe 21
    }

    @Test
    fun `포인트를 합산할때 21점을 초과하면 에이스 카드는 1점으로 계산한다`() {
        val card1 = Card(rank = Ranks.ACE, suits = Suits.SPADE)
        val card2 = Card(rank = Ranks.EIGHT, suits = Suits.HEART)
        val card3 = Card(rank = Ranks.TEN, suits = Suits.HEART)
        val userCards = UserCards(mutableListOf(card1, card2, card3))
        userCards.calculatePoints() shouldBe 19
    }

    @Test
    fun `전체 카드의 수는 52이다`() {
        val gameCards = GameCards.create()
        gameCards.size() shouldBe 52
    }

    @Test
    fun `게임 카드를 모두 드로우 하면 전체 덱의 사이즈는 0이다`() {
        val gameCards = GameCards.create()
        repeat(gameCards.size()) {
            gameCards.drawCard()
        }
        gameCards.size() shouldBe 0
    }

    @Test
    fun `플레이어는 드로우 된 카드를 가질 수 있다`() {
        val gameCards = GameCards.create()
        val card = gameCards.drawCard()
        val player = Player("lee")
        player.receiveCard(Card(rank = Ranks.TEN, suits = Suits.CLUB))
        player.receiveCard(Card(rank = Ranks.TWO, suits = Suits.DIAMOND))
        player.receiveCard(Card(rank = Ranks.FIVE, suits = Suits.HEART))
        player.cardSize() shouldBe 3
    }

    @Test
    fun `플레이어의 카드 점수를 합산 할 수 있다`() {
        val gameCards = GameCards.create()
        val card = gameCards.drawCard()
        val player = Player("lee")
        player.receiveCard(Card(rank = Ranks.TEN, suits = Suits.CLUB))
        player.receiveCard(Card(rank = Ranks.TWO, suits = Suits.DIAMOND))
        player.receiveCard(Card(rank = Ranks.FIVE, suits = Suits.HEART))
        player.calculateCardPoints() shouldBe 17
    }

    @Test
    fun `카드 드로우 중지 요청하면 플레이어는 카드를 받을수 없다`() {
        val player = Player("lee")
        player.stopCardDraw()
        shouldThrow<IllegalArgumentException> {
            player.receiveCard(Card(rank = Ranks.SIX, suits = Suits.CLUB))
        }.also {
            it.message shouldBe "카드를 받을 수 없습니다"
        }
    }

    @Test
    fun `게임에 참여할 사람들의 이름을 입력 받을 수 있다`() {
        val players = Players.create("kim,lee,hong")
        players.size shouldBe 3
    }

    @Test
    fun `게임을 시작하면 각 플레이어들은 2장씩 카드를 지급 받는다`() {
        val players = Players.create("kim,lee,hong")
        val gameCards = GameCards.create()
        val game = BlackJackGame(players, gameCards)
        game.startGame()
        players.size shouldBe 3
        players.sumOf { player -> player.cardSize() } shouldBe 6
        players.forEach {
            it.cardSize() shouldBe 2
        }
    }
}

class BlackJackGame(
    private val players: Players,
    private val gameCards: GameCards
) {
    fun startGame() {
        repeat(FIRST_ROUND_HAND_SIZE) {
            giveOutCards()
        }
    }

    private fun giveOutCards() {
        players.forEach { players ->
            players.receiveCard(gameCards.drawCard())
        }
    }

    companion object {
        private const val FIRST_ROUND_HAND_SIZE = 2
    }

}

object UserInput {
    fun enterParticipatingPlayers(): Players {
        println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)")
        val playerNames = readln()
        return Players.create(playerNames)
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

class UserCards(private val cards: MutableList<Card>) : Collection<Card> by cards {
    fun calculatePoints(): Int {
        val basePoints = cards.sumOf { it.rank.points[0] }
        val hasAce = cards.any { it.rank == Ranks.ACE }
        if (hasAce && basePoints + 10 <= 21) {
            return basePoints + 10
        }
        return basePoints
    }

    fun addCard(card: Card) {
        cards.add(card)
    }
}

class GameCards private constructor(private val deck: Queue<Card>) {

    fun drawCard(): Card {
        return deck.poll()
    }

    fun size(): Int {
        return deck.size
    }

    companion object {
        fun create(): GameCards {
            val allCards = Suits.entries.flatMap { suit ->
                Ranks.entries.map { rank -> Card(rank, suit) }
            }
            return GameCards(LinkedList(allCards.shuffled()))
        }
    }
}

class Player(val name: String, private var isDrawContinue: Boolean = true) {
    private var userCards = UserCards(mutableListOf())

    fun receiveCard(card: Card) {
        require(isDrawContinue) { "카드를 받을 수 없습니다" }
            userCards.addCard(card)
    }

    fun cardSize(): Int {
        return userCards.size
    }

    fun calculateCardPoints(): Int {
        return userCards.calculatePoints()
    }

    fun stopCardDraw() {
        isDrawContinue = false
    }
}

data class Players(private val players: List<Player>) : Collection<Player> by players {
    companion object {
        private const val DELIMITER = ","

        fun create(playerNames: String): Players {
            val players = playerNames.split(DELIMITER).map { playerName -> Player(playerName, true) }
            return Players(players)
        }
    }
}


