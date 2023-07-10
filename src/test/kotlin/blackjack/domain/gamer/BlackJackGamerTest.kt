package blackjack.domain.gamer

import blackjack.domain.card.Card
import blackjack.domain.card.CardNumber
import blackjack.domain.card.CardShape
import blackjack.dto.GeneratePlayerRequest
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class BlackJackGamerTest {

    @Test
    fun `BlackJackGamer는 money를 가진다`() {
        // given
        val money = 0
        val blackJackGamer = BlackJackGamer("name", money)

        // then
        Assertions.assertThat(blackJackGamer.money).isEqualTo(money)
    }

    @Test
    fun `외부에서 돈을 입력받아서 가지고 있는 돈에 더한다`() {
        // given
        val money = 0
        val blackJackGamer = BlackJackGamer("name", money)

        // when
        val addMoney = 10000
        blackJackGamer.takeMoney(addMoney)

        // then
        Assertions.assertThat(blackJackGamer.money).isEqualTo(money + addMoney)
    }

    @Test
    fun `외부에서 돈을 입력받아서 그만큼 가지고 있는 돈에서 뺀다`() {
        // given
        val money = 0
        val blackJackGamer = BlackJackGamer("name", money)

        // when
        val takenMoney = 10000
        blackJackGamer.takeMoneyOut(takenMoney)

        // then
        Assertions.assertThat(blackJackGamer.money).isEqualTo(money - takenMoney)
    }


    @Test
    fun `BlackJackGamer는 뽑은 카드를 한번에 한장씩 추가할 수 있다`() {
        // given
        val blackJackGamer = BlackJackGamer(name = "name", money = 1)

        val drawCard = Card(shape = CardShape.CLOVER, number = CardNumber.A)

        // when
        blackJackGamer.addCard(drawCard)
        val answer = listOf(Card(shape = CardShape.CLOVER, number = CardNumber.A))

        // then
        Assertions.assertThat(blackJackGamer.getCards()).isEqualTo(answer)
    }

//    @Test
//    fun `블랙잭 플레이어는 뽑은 카드를 한번에 여러장 씩 추가할 수 있다`() {
//        val drawCards = mutableListOf<Card>()
//        drawCards.add(Card(shape = CardShape.DIAMOND, number = CardNumber.TWO))
//        drawCards.add(Card(shape = CardShape.HEART, number = CardNumber.THREE))
//        drawCards.add(Card(shape = CardShape.CLOVER, number = CardNumber.A))
//
//        player.addCards(drawCards)
//
//        val answer = listOf(
//            Card(shape = CardShape.DIAMOND, number = CardNumber.TWO),
//            Card(shape = CardShape.HEART, number = CardNumber.THREE),
//            Card(shape = CardShape.CLOVER, number = CardNumber.A)
//        )
//
//        Assertions.assertThat(player.getCards()).isEqualTo(answer)
//    }
}
