package blackjack.domain.gamer

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.dto.GeneratePlayerRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class PlayerTest {

    private lateinit var player: Player

    @BeforeEach
    fun setUp() {
        val bettingMoney = 1
        val playerName = "name"
        player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()
    }

    @Test
    fun `플레이어는 이름을 가진다`() {
        Assertions.assertThat(player.name).isEqualTo("name")
    }

    @Test
    fun `플레이어는 뽑은 카드를 한번에 한장씩 추가할 수 있다`() {
        val drawCard = Card(shape = CardShape.CLOVER, number = CardNumber.A)

        player.addCard(drawCard)
        val answer = listOf(Card(shape = CardShape.CLOVER, number = CardNumber.A))

        Assertions.assertThat(player.getCards()).isEqualTo(answer)
    }

    @Test
    fun `플레이어는 뽑은 카드를 한번에 여러장 씩 추가할 수 있다`() {
        val drawCards = mutableListOf<Card>()
        drawCards.add(Card(shape = CardShape.DIAMOND, number = CardNumber.TWO))
        drawCards.add(Card(shape = CardShape.HEART, number = CardNumber.THREE))
        drawCards.add(Card(shape = CardShape.CLOVER, number = CardNumber.A))

        player.addCards(drawCards)

        val answer = listOf(
            Card(shape = CardShape.DIAMOND, number = CardNumber.TWO),
            Card(shape = CardShape.HEART, number = CardNumber.THREE),
            Card(shape = CardShape.CLOVER, number = CardNumber.A)
        )

        Assertions.assertThat(player.getCards()).isEqualTo(answer)
    }

    @Test
    fun `플레이어의 전적이 결정되기전에 조회할 경우 IllegalArgumentException을 throw 한다`() {
        assertThrows<IllegalArgumentException> { player.getGameRecord() }
    }

    @Test
    fun `플레이어가 게임에서 패배했을때, 돈을 잃는다`() {
        // given
        val bettingMoney = 10000
        val playerName = "name"
        player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        // when
        player.proceedGameRecord(GameRecordType.LOSE)
        player.loseMoney()

        // then
        Assertions.assertThat(player.money).isEqualTo(-bettingMoney)
    }

    @Test
    fun `플레이어가 게임에서 승리하면 베팅한 금액을 받는다`() {
        // given
        val bettingMoney = 10000
        val playerName = "name"
        player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        // when
        player.proceedGameRecord(GameRecordType.WIN)
        player.winMoney()

        // then
        Assertions.assertThat(player.money).isEqualTo(bettingMoney)
    }

    @Test
    fun `플레이어가 블랙잭으로 승리하면 베팅한 금액의 일점오배를 받는다`() {
        // given
        val bettingMoney = 10000
        val playerName = "name"
        player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        // when
        player.proceedGameRecord(GameRecordType.WIN)
        player.blackJackMoney()

        // then
        Assertions.assertThat(player.money).isEqualTo(15000)
    }

    @Test
    fun `승부가 결정나기전에 승리 금액을 정산하면 IllegalArgumentException을 throw 한다`() {
        // given
        val bettingMoney = 10000
        val playerName = "name"
        player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        // then
        assertThrows<IllegalArgumentException> { player.winMoney() }
    }

    @Test
    fun `승부가 결정나기전에 패배 금액을 정산하면 IllegalArgumentException을 throw 한다`() {
        // given
        val bettingMoney = 10000
        val playerName = "name"
        player = GeneratePlayerRequest(playerName, bettingMoney)
            .generatePlayer()

        // then
        assertThrows<IllegalArgumentException> { player.loseMoney() }
    }
}
