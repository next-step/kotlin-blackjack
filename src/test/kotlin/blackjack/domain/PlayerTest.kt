package blackjack.domain

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class PlayerTest {

    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        player = Player("이름")
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        Assertions.assertThat(player.name).isEqualTo("이름")
    }

    @Test
    fun `플레이어는 뽑은 카드를 한번에 한장 씩 추가할 수 있다`() {
        val drawCard = Card(shape = CardShape.CLOVER, number = CardNumber.A)

        player.addCard(drawCard)
        val answer = "A클로버"

        Assertions.assertThat(player.getCards()).isEqualTo(answer)
    }

    @Test
    fun `플레이어는 뽑은 카드를 한번에 여러장 씩 추가할 수 있다`() {
        val drawCards = mutableListOf<Card>()
        drawCards.add(Card(shape = CardShape.DIAMOND, number = CardNumber.TWO))
        drawCards.add(Card(shape = CardShape.HEART, number = CardNumber.THREE))
        drawCards.add(Card(shape = CardShape.CLOVER, number = CardNumber.A))

        player.addCards(drawCards)
        val answer = "2다이아, 3하트, A클로버"

        Assertions.assertThat(player.getCards()).isEqualTo(answer)
    }

    @Test
    fun `이름 리스트를 입력받으면 이름을 가지는 플레이어 리스트를 생성한다 `() {
        val playerNameList = listOf<String>("pobi", "jason")

        val playerList = Player.generatePlayers(playerNameList)
        val actual = mutableListOf<String>()

        playerList.forEach {
            actual.add(it.name)
        }

        Assertions.assertThat(actual).isEqualTo(playerNameList)
    }
}
