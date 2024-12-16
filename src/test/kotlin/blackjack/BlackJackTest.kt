package blackjack

import blackjack.domain.BlackJackGame
import blackjack.domain.Card
import blackjack.domain.GameCards
import blackjack.domain.Player
import blackjack.domain.Players
import blackjack.domain.Ranks
import blackjack.domain.Suits
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

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
        val player = Player("lee")
        val gameCards = GameCards.create()
        val card1 = gameCards.drawCard()
        player.receiveCard(card1)
        val card2 = gameCards.drawCard()
        player.receiveCard(card2)
        player.cardSize() shouldBe 2
    }

    @Test
    fun `플레이어의 카드 점수를 합산 할 수 있다`() {
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
        players.getPlayerNames() shouldBe listOf("kim", "lee", "hong")
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
